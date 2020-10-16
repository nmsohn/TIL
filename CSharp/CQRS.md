# 개념

- Command and Query Responsibility Segregation(명령과 조회의 책임 분리)
- 시스템에서 명령을 처리하는 책임과 조회를 처리하는 책임을 분리하는 것이 CQRS의 핵심
- 명령 (command)
  - CQRS에서 명령은 시스템의 상태를 변경하는 작업을 의미
  - CRUD
  - Commands should be task based, rather than data centric
  - Commands may be placed on a queue for asynchronous processing
- 조회 (Query)
  - 조회는 시스템의 상태를 반환하는 작업
  - Select
  - Queries never modify the database. A query returns a DTO that does not encapsulate any domain knowledge

> 모든 연산이 명령과 조회로 쉽게 양분되지는 않습니다. 개념적으로 어려운 경우도 있고 동시성 등 기술적인 문제도 있습니다. Martin Fowler는 스택 자료구조의 pop() 연산을 예로 들었습니다.

## 전통적인 CRUD 시스템

- 애플리케이션 개발 시 컨텐츠를 위한 데이터 모델은 계속해서 복잡도가 올라감
- 복잡도가 증가할 수록 API 기능 책임이 어떤 데이터 모델에 있는지 불분명

## CQRS 적용

### 일반 regular

- 단일 Data Store에 Command와 Query Model을 분리된 계층으로 나누는 방식
  - 같은 Schema 디자인을 가진 DB를 사용
- Command, Query 시 데이터데 대한 변환을 거친 후 DB에 CRUD 작업 수행
- Model Layer 부분만 Command와 Query Model로 분리하는 수준으로 간단하게 적용
- 명령 스택은 데이터, 비즈니스, 보안 규칙만 고려해서 개발
- 쿼리 스택은 가장 간단하게는 DB 커넥션에 SQL 쿼리문만 작성

### 프리미엄 premium

- Command용 Database와 Query용 Database를 분리
  - separate read and write data
  - 데이터의 정합성을 위한 RDB를 Command 용 DB로 분리하고 Query 가 간편한 NoSQL 을 Query 용 DB로 주로 사용
- 별도의 Broker를 통해서 이 둘 간의 Data를 동기화 처리 하는 방식
- Polyglot 저장소
  - 다수의 db 혼용하여 사용
- 책임의 소재나 로깅 등에 있어 신뢰도 확보를 위한 작업이 필요

### 디럭스 deluxe

- Event Sourcing 모델
  - Application내의 모든 Activity를 이벤트로 전환. 이벤트 스트림(Event Stream)을 별도의 Database에 저장하는 방식
    - Event Sourcing 이란 Application 내의 설계를 컨텐츠 기반이 아닌 기능 기반으로 하면서 이러한 "이벤트(Event)" 자체를 DB에 저장하는 방식
  - 이벤트에서 사용하는 도메인 모델은 컨텐츠를 위한 DB에 Write 되고 Query 시에는 이벤트를 저장한 DB로부터 해당 컨텐츠를 바탕으로 데이터를 만들어서 가져온다
    - the store of events is the write model, and is the official source of information
  - 도메인 모델에 대한 Command 가 따로 저장되고, Query 를 위한 도메인 모델은 Event DB로부터 불러오는 방식
  - 이벤트 스트림을 저장하는 Database에는 오직 데이터 추가만 가능하고 계속적으로 쌓인 데이터를 구체화(Materialized) 시키는 시점에서 그때 까지 구축된 데이터를 바탕으로 조회대상 데이터를 작성하는 방법
    - The read model of a CQRS-based system provides materialized views of the data, typically as highly denormalized views.
  - CQRS패턴에 이벤트 소싱은 필수가 아니지만 이벤트 소싱에 CQRS는 필수임
