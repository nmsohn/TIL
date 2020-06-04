# Create a Lambda to execute the script
## Example 1: resetCases.js
```
'use strict'

const AWS = require('aws-sdk');
const ssm = new AWS.SSM();

exports.handler = function(event, context) {
    console.log("\n\nLoading handler\n\n");
    console.log(event);
    const instanceId =event['detail']['ec2InstanceId'];
    
    console.log("sending command to ssm");
    ssm.sendCommand(
      {
        DocumentName: "AWS-RunShellScript",
        InstanceIds: [instanceId],
        TimeoutSeconds: 3600,
        Parameters: {
            "commands": [
                "#!/bin/bash",
                "containerID=$(docker ps -qf label=name=openlawnz | xargs)",
                'if [ -z "$containerID" ]; then',
                "echo $containerID",
                'echo "container found"',
                'sudo docker exec -i ${containerID} bash < /home/ec2-user/test.sh',
                'fi',
                'echo "terminating"'
            ]
         }
      },
      function(err, data) {
        if (err) {
          console.log(err, err.stack); // an error occurred
        } else {
          console.log(data);
          console.log("successful")
          context.done(null, 'Function Finished!');
        }
    })
     
};
```

# Create a lambda to execute step functions
- Set a CloudWatch event rule to trigger this lambda function
- I used ec2 instance change trigger under ecs to test the worflow but it may fire multiple requests to the function
- Another way to do it is using SNS or API call

```
const AWS = require('aws-sdk');

const stepFunctions = new AWS.StepFunctions({
region: 'ap-southeast-2'
});

let index = function index(event, context, callback) {
const params = {
    stateMachineArn: 'arn:aws:states:ap-southeast-2:<ACCOUNT_ID>:stateMachine:db-processor',
    input: JSON.stringify(event)
    //JSON.stringify({}), Optional if your statemachine requires an application/json input, make sure its stringified 
};

stepFunctions.startExecution(params, (err, data) => {
    if (err) {
    console.log(err.stack);
    const response = {
        statusCode: 500,
        body: JSON.stringify({
        message: 'There was an error'
        })
    };
    console.log(response);
    } else {
    console.log(data);
    const response = {
        statusCode: 200,
        body: JSON.stringify({
        message: 'Step function worked'
        }),
    };
    console.log(response);
    }
});
};

module.exports.init = (event, context, callback) => {
};
exports.handler = index;
```

# Set up step functions
##Example: state-machine.json 
```
{
    "Comment": "DB Processing",
    "StartAt": "resetCases",
    "States": {
        "resetCases": {
            "Type": "Task",
            "Resource": "arn:aws:lambda:ap-southeast-2:667969577435:function:resetCases",
            "Next": "parseInvalidCharacters"
        },
        "parseInvalidCharacters": {
            "Type": "Task",
            "Resource": "arn:aws:lambda:ap-southeast-2:667969577435:function:parseInvalidCharacters",
            "Next": "parallelGroup1"
        },
        "parallelGroup1": {
            "Type": "Parallel",
            "Next": "parallelGroup2",
            "Branches": [{
                    "StartAt": "parseFootnotes",
                    "States": {
                        "parseFootnotes": {
                            "Type": "Task",
                            "Resource": "arn:aws:lambda:ap-southeast-2:667969577435:function:parseFootnotes",
                            "End": true
                        }
                    }
                },
                {
                    "StartAt": "parseEmptyCitations",
                    "States": {
                        "parseEmptyCitations": {
                            "Type": "Task",
                            "Resource": "arn:aws:lambda:ap-southeast-2:667969577435:function:parseEmptyCitations",
                            "End": true
                        }
                    }
                }
            ]
        },
        "parallelGroup2": {
            "Type": "Parallel",
            "Next": "PROCESSED",
            "Branches": [{
                    "StartAt": "parseCourts",
                    "States": {
                        "parseCourts": {
                            "Type": "Task",
                            "Resource": "arn:aws:lambda:ap-southeast-2:667969577435:function:parseCourts",
                            "End": true
                        }
                    }
                },
                {
                    "StartAt": "parseCaseToCase",
                    "States": {
                        "parseCaseToCase": {
                            "Type": "Task",
                            "Resource": "arn:aws:lambda:ap-southeast-2:667969577435:function:parseCaseToCase",
                            "End": true
                        }
                    }
                },
                {
                    "StartAt": "parseLegislationToCases",
                    "States": {
                        "parseLegislationToCases": {
                            "Type": "Task",
                            "Resource": "arn:aws:lambda:ap-southeast-2:667969577435:function:parseLegislationToCases",
                            "End": true
                        }
                    }
                }
            ]
        }
    }
}
```