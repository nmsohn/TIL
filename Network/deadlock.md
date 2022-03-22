# 개념

- 프로세스가 자원을 얻지 못해 다음 처리를 하지 못하는 상태

## 데드락 발생 조건

### Mutual exclusion

- 한 번에 한 프로세스가 자원을 사용

### Hold and wait

- 최소한 하나의 자원을 점유하고 있고 다른 프로세스 자원을 점유하기 위해 대기하는 프로세스가 있음

### no preemption

- 자원은 사용이 끝날 때까지 빼앗을 수 없음

### circular wait

- 연쇄적으로 자원을 기다림

# 데드락 처리

## Prevention

- 예방법
- mutual exclusion : 동시에 여러 프로세스의 접근을 가능하게 함 →  불가능
- hold and wait :
    - 한번에 모든 자원을 가지거나 아무것도 가지지 못하게 함 (hold 방지)
    - 실행 전에 자원을 미리 할당 (wait 방지)
    - 자원 활용도가 떨어지고 starvation 발생 가능
- no preemption : 한개의 작업이라도 실패하면 모든 자원을 release
- circular wait: 자원 형태에 따라 순서를 매겨 프로세스가 리소스를 요청하면 해당 순서에 따라 자원을 할당

## Ostrich 알고리즘

- 시스템 재시작
- 가장 빠르고 비용 최소화
- 데드락 발생 빈도가 높을 수록 비효율적

## Avoidance

- 회피
- Banker 알고리즘
- 자원의 양과 사용자수가 일정해야함
- 각 프로세스가 OS에게 필요한 최대 자원을 알림
    - os는 safe한 상태의 프로세스만 자원 할당
        - safe하다는 것은 받은 자원으로 작업을 수행하고 반납할 수 있는 상태
- 보수적이라 safe 상태를 위해 병렬성을 감소

## Deadlock Detection

- 감지
- 데드락을 허용하되
- wait for graph 또는 detection 알고리즘을 통해 데드락 원인을  찾기

## Recovery

- 매 time slice 마다 프로세스 제거하고 회수. 데드락 발생하지 않을 때까지.
- 체크 포인트를 만들어 데드락이 발생하면 체크 포인트로 rollback
    - starvation 발생 가능성 (무한 rollback)