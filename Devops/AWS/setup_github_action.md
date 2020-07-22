# Setup
- Set your secrets in Github repository settings
```
AWSACCESSKEY_ID
AWSECRREPOSITORY
AWSSECRETACCESS_KEY
AWSECRREPOSITORY is your ECR name (REPO_NAME)
```

- Add build.yml under .github/workflows
```
name: ECS Deploy

on:
  push:
    tags:
      - v*

jobs:
  build-and-push:
    name: Building and pushing image to AWS ECR
    runs-on: ubuntu-latest
    timeout-minutes: 300

    steps:
    - name: Checkout
      uses: actions/checkout@master

    - name: Configure AWS credentials
      uses: aws-actions/configure-aws-credentials@v1
      with:
            aws-access-key-id: ${{ secrets.AWS_ACCESS_KEY_ID }}
            aws-secret-access-key: ${{ secrets.AWS_SECRET_ACCESS_KEY }}
            aws-region: ap-southeast-2

    - name: Login to ECR
      id: login-ecr
      uses: aws-actions/amazon-ecr-login@v1

    - name: Build, tag and push the image
      id: build-image
      env:
        ECR_REGISTRY: ${{ steps.login-ecr.outputs.registry }}
        ECR_REPOSITORY: ${{ secrets.AWS_ECR_REPOSITORY }}
      run: |
        IMAGE_TAG=$(echo ${{ github.ref }} | sed -e "s#refs/tags/##g")
        docker build -t $ECR_REGISTRY/$ECR_REPOSITORY:$IMAGE_TAG .
        docker push $ECR_REGISTRY/$ECR_REPOSITORY:$IMAGE_TAG
        echo "::set-output name=image::$ECR_REGISTRY/$ECR_REPOSITORY:$IMAGE_TAG"

    - name: Fill in the new image ID in AWS ECS task definition
      id: task-def
      uses: aws-actions/amazon-ecs-render-task-definition@v1
      with:
        task-definition: task-definition.json
        container-name: ${{ secrets.AWS_ECR_REPOSITORY }}
        image: ${{ steps.build-image.outputs.image }}

    - name: Deploy AWS ECS task definition for resetCases
      uses: aws-actions/amazon-ecs-deploy-task-definition@v1
      with:
        task-definition: ${{ steps.task-def.outputs.task-definition }}
        service: SERVICE_NAME
        cluster: resetCases
        wait-for-service-stability: true

    - name: Deploy AWS ECS task definition for parseInvalidCharacters
      uses: aws-actions/amazon-ecs-deploy-task-definition@v1
      with:
        task-definition: ${{ steps.task-def.outputs.task-definition }}
        services: SERVICE_NAME
        cluster: parseInvalidCharacters
        wait-for-service-stability: true

    - name: Deploy AWS ECS task definition for parseFootnotes
      uses: aws-actions/amazon-ecs-deploy-task-definition@v1
      with:
        task-definition: ${{ steps.task-def.outputs.task-definition }}
        services: SERVICE_NAME
        cluster: parseFootnotes
        wait-for-service-stability: true
```

- Add DockerFile in the root folder
```
FROM node:13
MAINTAINER openlawnz

# Create app directory
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
COPY package.json ./

RUN yarn install
# Bundle app source
COPY . .

# Label docker container
LABEL name="openlawnz"

EXPOSE 80
```

- Add task-definition.json in the root folder
```
{
    "ipcMode": "task",
    "executionRoleArn": "arn:aws:iam::<ACCOUNT_ID>:role/ROLE_NAME",
    "containerDefinitions": [
        {
            "name": "<REPO_NAME>",
            "image": "<ACCOUNT_ID>.dkr.ecr.ap-southeast-2.amazonaws.com/<REPO_NAME>:latest",
            "dnsSearchDomains": [],
            "logConfiguration": {
                "logDriver": "awslogs",
                "secretOptions": [],
                "options": {
                    "awslogs-group": "/ecs/openlawnz",
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
    "family": "openlawnz",
    "pidMode": "task",
    "requiresCompatibilities": ["EC2"],
    "networkMode": "bridge",
    "inferenceAccelerators": [],
    "volumes": [],
    "memory": "512",
    "cpu": "512"
}
```

## How to trigger github action with tag
```
git add .
git commit -m "Set up github action to build ECR image and deploy it to ECS"
git tag v1.0.0
git push
git push --tag
```