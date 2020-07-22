This is just for the records how I set up ECR/ECS/RDS pipeline and then execute Step Function to run the script in EC2.

# First Setup
## Create security groups
- PublicSecurity
    - HTTP
- DockerSecurity
    - Add ssh sources under inbound
    - Custom TCP rule 8080
    - postgreSQL 5432
- ClusterSecurity
    - Add TCP and specify the source with DockerSecurity group id
- ALBSecurity
    - HTTP

## Create a key pair
- Create a key pair

## Create a ECR
```
aws ecr describe-repositories --repository-names <REPO_NAME> || aws ecr create-repository --repository-name <REPO_NAME>
```

## Create a ECS cluster
```
ecs-cli configure profile --profile-name <PROFILE_NAME> \
--access-key $AWS_ACCESS_KEY_ID --secret-key $AWS_SECRET_ACCESS_KEY

ecs-cli configure --cluster <CLUSTER_NAME> --default-launch-type EC2 \
--region ap-southeast-2 --config-name <CONFIG_NAME>

ecs-cli up --keypair openlawnz-key \
--cluster resetCases \
--capability-iam \
--size 2 \
--instance-type <INSTANCE_TYPE> \
--security-group ClusterSecurity
--subnets <SUBNET_1_ID>, <SUBNET_2_ID>
--vpc <VPC_ID> 
--extra-user-data resetCases.sh
--cluster-config <CONFIG_NAME>
--ecs-profile <ECS_PROFILE>
--instance-role <IAM_ROLE>
```

## Add a script in EC2
```
#!/bin/bash
echo ECS_CLUSTER=resetCases >> /etc/ecs/ecs.config;
echo ECS_BACKEND_HOST= >> /etc/ecs/ecs.config;
echo ECS_CONTAINER_INSTANCE_TAGS={"Name": "resetCases"} >> /etc/ecs/ecs.config;

sudo yum -y update

cd /home/ec2-user
touch resetCases.sh

echo "#!/bin/bash" >> resetCases.sh
echo "yarn resetCases" >> resetCases.sh
```

## Create a ALB
```
aws elbv2 help

# Create a load balancer
aws elbv2 create-load-balancer --name openlawnz-elb  \
--subnets <SUBNET_1_ID> <SUBNET_2_ID> --security-groups ALBSecurity

aws elbv2 create-target-group --name openlawNZRoute --protocol HTTP --port 80 \
--vpc-id <VPC_ID>

aws elbv2 register-targets --target-group-arn <TARGETGROUP_ARN>  \
--targets Id=<INSTANCE_ID1> Id=<INSTANCE_ID2>

aws elbv2 create-listener --load-balancer-arn <LOADBALANCER_ARN> \
--protocol HTTP --port 80  \
--default-actions Type=forward,TargetGroupArn=<TARGETGROUP_ARN>

aws elbv2 describe-target-health --target-group-arn <TARGETGROUP_ARN>
```

## Set parameters using AWS Systems Manager
- Go to `parameter store` under `application management`
- Create parameters
```
DB_HOST - your RDS endpoint
DB_NAME - RDS DB id
DB_USER - username
DB_PASS - password
PORT - 5432
```

## Create a task definition
```
{
    "ipcMode": "task",
    "executionRoleArn": "arn:aws:iam::<ACCOUNT_ID>:role/EC2Role",
    "containerDefinitions": [
        {
            "name": "<REPO_NAME>",
            "image": "<ACCOUNT_ID>.dkr.ecr.ap-southeast-2.amazonaws.com/<REPO_NAME>:latest",
            "dnsSearchDomains": [],
            "logConfiguration": {
                "logDriver": "awslogs",
                "secretOptions": [],
                "options": {
                    "awslogs-group": "/ecs/openlawnz-ecr",
                    "awslogs-region": "ap-southeast-2",
                    "awslogs-stream-prefix": "ecs"
                }
            },
            "entryPoint": [],
            "portMappings": [{
                "hostPort": 0,
                "protocol": "tcp",
                "containerPort": 80
            }],
            "command": [],
            "linuxParameters": [],
            "environment": [],
            "secrets": [
                {
                    "name": "DB_NAME",
                    "valueFrom": "arn:aws:ssm:ap-southeast-2:<ACCOUNT_ID>:parameter/DB_NAME"
                },
                {
                    "name": "DB_HOST",
                    "valueFrom": "arn:aws:ssm:ap-southeast-2:<ACCOUNT_ID>:parameter/DB_HOST"
                },
                {
                    "name": "DB_USER",
                    "valueFrom": "arn:aws:ssm:ap-southeast-2:<ACCOUNT_ID>:parameter/DB_USER"
                },
                {
                    "name": "DB_PASS",
                    "valueFrom": "arn:aws:ssm:ap-southeast-2:<ACCOUNT_ID>:parameter/DB_PASS"
                },
                {
                    "name": "PORT",
                    "valueFrom": "arn:aws:ssm:ap-southeast-2:<ACCOUNT_ID>:parameter/PORT"
                }
            ],
            "resourceRequirements": [],
            "ulimits": [],
            "dnsServers": [],
            "mountPoints": [],
            "dockerSecurityOptions": [],
            "memoryReservation": 256,
            "volumesFrom": [],
            "stopTimeout": 2,
            "startTimeout": 2,
            "dependsOn": [],
            "workingDirectory": "/usr/src/app",
            "interactive": true,
            "essential": true,
            "links": [],
            "pseudoTerminal": true,
            "cpu": 256,
            "memory": 256
        }
    ],
    "placementConstraints": [],
    "family": "openlawnz-ecr",
    "pidMode": "task",
    "requiresCompatibilities": ["EC2"],
    "networkMode": "bridge",
    "inferenceAccelerators": [],
    "volumes": [],
    "memory": "512",
    "cpu": "512"
}
```

## Create a service in the ECS cluster
```
aws ecs create-service \
    --cluster <CLUSTER_NAME> \
    --service-name <SERVICE_NAME> \
    --task-definition <TASK_DEFINITION_NAME>:1 \
    --desired-count 1
    --launch-type EC2 \
    --platform-version LATEST \
    --network-configuration "awsvpcConfiguration={subnets=[subnet-12344321],securityGroups=[sg-12344321],assignPublicIp=ENABLED}" \
    --tags Name=resetCases \
    --deploymentController type=ECS \
    --scheduling-strategy REPLICA \
    --load-balancers targetGroupArn=<TARGET_GROUP_ARN>,containerName=<CONTAINER_NAME>,containerPort=80 \
    --role <IAM_ROLE>
```

## Troubleshooting
ECS agent tries to retain the desired count (which is 1 here) when the task has been updated. I ended up adding an extra EC2 instance so the new task definition can be run on the extra instance. ECS agent will drain the connection to the instance with the old task definition and replace the old task definition with the new one.