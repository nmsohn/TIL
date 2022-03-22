# 개념

- 클라우드 인프라 환경 배포 자동화 툴

# Quickstart

## 테라폼 설치

[Install Terraform](https://learn.hashicorp.com/tutorials/terraform/install-cli)

- 리눅스

```powershell
sudo apt-get update
sudo apt-get install wget unzip

sudo wget https://releases.hashicorp.com/terraform/0.12.18/terraform_0.12.18_linux_amd64.zip

unzip terraform_0.12.18_linux_amd64.zip

rm terraform_0.12.18_linux_amd64.zip

mv terraform /usr/bin && sudo chmod +x

terraform -v
```

- Azure AD 서비스 사용자 만들기

[Use Azure service principals with Azure CLI](https://docs.microsoft.com/ko-kr/cli/azure/create-an-azure-service-principal-azure-cli)

- 구독 목록 확인 및 디폴트 지정

```powershell
az account list --query "[].{name:name, subscriptionId:id, tenantId:tenantId}"

az account set --subscription="2e5d848e-xxxx-xxxx-xxxx-fd25ae915bcd"
```

- 서비스 사용자 만들기

```powershell
az ad sp create-for-rbac --role="Contributor" --scopes="/subscriptions/2e5d848e-xxxx-xxxx-xxxx-fd25ae915bcd"
```

- appId 및 password를 기록해두기

## 환경 변수 구성

```powershell
ARM_SUBSCRIPTION_ID
ARM_CLIENT_ID
ARM_CLIENT_SECRET
ARM_TENANT_ID
ARM_ENVIRONMENT
```

- [`terraform-var.sh`](http://terraform-var.sh/) 만들기

```bash
#!/bin/sh
echo "Setting environment variables for Terraform"
export ARM_SUBSCRIPTION_ID=2e5d848e-xxxx-xxxx-xxxx-fd25ae915bcd
export ARM_CLIENT_ID=3381f28b-cbb3-4235-848b-f0225616d90a
export ARM_CLIENT_SECRET=34e94183-xxxx-xxxx-xxxx-1e2fd6f7d160
export ARM_TENANT_ID=54a472fb-1510-4554-922c-fe808f9e6e64

# Not needed for public, required for usgovernment, german, china
export ARM_ENVIRONMENT=public
```

- 만들어 놓은 변수 bash 파일을 terraform에 사용

```bash
sh terraform-env.sh
source terraform-env.sh
echo $ARM_TENANT_ID
```

## 샘플 스크립트 실행

- 빈 디렉토리에 [`test.tf`](http://test.tf) 파일 만들기

```bash
provider "azurerm" {
}
resource "azurerm_resource_group" "rg" {
        name = "testResourceGroup"
        location = "koreacentral"
}
```

- 파일 저장 후 테라폼 배포를 초기화

```bash
terraform init
```

- terraform 스크립트에서 완료할 작업 미리보기

```bash
terraform plan
```

- 계획 적용

```bash
terraform apply
```

- Azure portal에서 배포 상태 확인. 리소스 그룹이 생성된것을 확인
