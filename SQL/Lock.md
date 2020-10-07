# 개념
## lock

- 데이터베이스의 consistency 와 integrity를 유지하려면 트랜잭션의 순차적 진행을 보장할 수 있는 [Serialization 직렬화 - C#](https://www.notion.so/Serialization-C-19b9aa13ec764d5484a44463693be72f) 장치가 필요
- 하나의 트랜잭션이 실행하는 동안 특정 데이터 항목에 다른 트랜잭션이 동시에 접근하지 못하도록 상호배제 기능 제공

### 공유 s-lock

- 데이터를 read할 때 사용
- 하나의 데이터 항목에 여러개 추가 가능
- 배타적 lock과는 호환되지 않음
- 읽을 수는 있어도 업데이트는 불가능
    - 변경중인 리소스는 읽을 수 없음
- 다음 레코드가 읽히면 곧바로 해제됨 (read committed 일 경우)
- 트랜잭션 내에 유지하려면 holdlock을 지정

```sql
begin tran select 적립포인트, 방문횟수, 최근방문일시, 구매실적 from 고객 with (holdlock) where 고객번호 = :cust_num -- 새로운 적립포인트 계산 update 고객 set 적립포인트 = :적립포인트 where 고객번호 = :cust_num commit
```

### 배타 x-lock

- lock이 해제될 때까지 다른 트랜잭션을 해당 리소스에 접근 못함
- 동시에 여러개 배타잠금할 수 없음
    - 하나의 데이터에 하나의 배타잠금
- 다른 트랙잭션은 읽는 것, 변경 불가능
    - data를 읽으려면

```sql
SELECT * FROM TEST_TAB WITH (READUNCOMMITTED) WHERE ID=3 
//(WITH (READUNCOMMITTED) 라는 잠금 힌트는 Select할 때 S-Lock을 걸지 말라는 의미
```

## Nolock

- ADO에서 자동 트랜잭션 처리할 수 있으므로 no lock 필요
- 수 만개의 트랜잭션을 처리할 때 그 처리를 하나하나 기다리면 효율성이 떨어짐
- 커밋되지 않은 읽기 허용

```sql
SELECT EmployeeID,LastName FROM dbo.Employees WITH(NOLOCK)
```

- 데이터 일관성은 떨어짐

# Deadlock

- 병목현상. 교착 상태
- 한정된 자원을 여러 곳에서 사용할 때 발생
- deadlock 발생 조건
    - 상호배제
        - 한 번에 한 프로세스
    - 점유 대기
    - 비선점
    - 순환 대기

# Deadlock 해결

## Timeout

- 일정 시간 기다리다가 반응이 없으면 롤백시킴

```sql
SET LOCK_TIMEOUT 10000

SELECT @@LOCK_TIMEOUT
```

## 프로시저 우위 설정

- 우위가 낮은 프로시저 차례로 취소

```sql
SET DEADLOCK_PRIORITY LOW
```

## 강제 종료

```sql
KILL
```

# Deadlock 예방

- 락의 유지 시간을 짧게 해주기
- 트랜잭션 구문의 처리 순서를 일치시킴

```sql
트랜잭션1=> A작업->B작업->C작업

트랜잭션2=> C작업->A작업->B작업 //deadlock 발생
```

- 트랜잭션 처리 속도 짧게 하기
- 트랜잭션 격리 수준을 최대한 낮게 설정