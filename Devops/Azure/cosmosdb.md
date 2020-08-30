# 코스모스 DB 시작하기

# Azure cli 시작하기

## 설치

[Install the Azure CLI for Windows](https://docs.microsoft.com/en-us/cli/azure/install-azure-cli-windows?view=azure-cli-latest&tabs=azure-powershell)

- 파워쉘 명령어

```python
Invoke-WebRequest -Uri https://aka.ms/installazurecliwindows -OutFile .\AzureCLI.msi; Start-Process msiexec.exe -Wait -ArgumentList '/I AzureCLI.msi /quiet'; rm .\AzureCLI.msi
```

## Azure 로그인

```python
az login
az account list //구독 리스트
az account set --subscription //구독 설정
```

## Region 확인

```python
az account list-locations
```

## Resource 그룹

```python
az group list --out table
az group create --name <name> --location <location>
```

## 코스모스DB

```python
az cosmosdb create --name $NAME --kind GlobalDocumentDB --resource-group $RESOURCE_GROUP
```