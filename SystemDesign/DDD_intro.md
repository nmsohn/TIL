# 개념

- 도메인이 중심이 되는 개발 방식
- 비즈니스 Domain별로 나누어 설계
- 현업과 IT의 쌍방향 커뮤니케이션 중시
- Strategic Design은 개념 설계이고 Tactical Design은 프로그래밍하기 위한 구체적 설계

## 도메인

- 사용자가 사용하는 것을 도메인(Domain)
- 어플리케이션 내의 로직들이 관여하는 정보와 활동의 영역
- 도메인은 사용자에 따라 또는 사용자가 바라보는 관점에 따라 지속적으로 변화
- 도메인의 변경으로 발생되는 수많은 소프트웨어 변경에 어떻게 대응해야 할까?

### 도메인 vs 객체

- 객체는 추상화 또는 구체화할 수 있는 특정 요소만을 표현
- 도메인은 사용자가 사용하는 모든 것을 설명

# Domain Model Pattern

## Domain Model

- 구체적인 설계
- Entity와 value object의 개념
- 도메인 모델에 set 메서드 넣지 않기

## Entity

- 속성이 아닌 identity 를 기준으로 정의되는 도메인 객체
- Entity는 Value Object로 구성?
- 기존의 attributes 를 기준으로 정의되었던 전통적인 객체와 달리, 연속성의 일관된 스레드에 의해 식별되는 객체
- 모든 것에 식별성을 부여하고 Entity로 관리한다면 복잡성 증가
- Entity는 identified id 를 가지고 business logic을 구현
- 엔티티Entity는 간단히 말해서 식별번호(ID)를 부여하는 레코드
- ex) DB : ERD ( Entity - Relationship Model), J2EE : Entity Bean

### 식별자 생성

식별자는 다음 중 한가지 방식으로 생성

- 특정 규칙에 따라 생성 - 주문번호, 카드번호
- UUID 사용 - 개발 언어에서 지원하는 UUID 생성기 사용
- 값을 직접 입력 - 회원 아이디, 이메일
- 일련번호 사용 - 시퀀스나 DB의 자동 증가 칼럼 사용(auto increment)

## VO

- 식별성이 아닌 속성을 이용해 정의되는 immutable 객체
- 과거 Java의 DTO(Data Transfer Object) 패턴의 Value Object과 관계없음
- 식별성을 가지면 Entity 그렇지 않으면 Value Object
- 밸류타입은 코드의 의미를 더 잘 이해할 수 있도록한다.
  - 받는 사람 이름(String), 받는 사람 폰번호(String) → 받는 사람(Receiver Class)(Receiver Class는 받는 사람이라는 도메인 개념을 표현)
  - 주소1(String), 주소2(String), 우편번호(String) → 주소(Address Class)(Address Class는 주소라는 도메인 개념을 표현)

VO vs MAP논쟁: VO의 장점은 속성의 type이나 validation rule같은것이 변경 되었을 때 유연하다는 것입니다. 단점은 잘못쓰면 복잡성이 증가되고, DB스키마 변경 시 관련된 VO도 수정해야 한다는 것입니다. 현실적으로 SQL에 많이 의존하거나(DAO사용), DB스키마의 변경이 빈번하게 예상된다면 VO class보다 구조체(Map)가 더 효율적일 수 있습니다.

## Service

- Domain Object에서 위치시키기 어려운 operation을 가지는 객체
- 여러 Domain Object 다루는 연산 Service의 오퍼레이션은 일반적으로 stateless

## Module

- 유사 작업 및 개념을 그룹화 하여 복잡도를 감소시키는 기법
- 응집도가 높은 모듈은 모듈간의 관계는 약 결합

## Aggregate

- aggregates는 entities의 집합
- 연관된 Entity와 Value Object의 묶음. 일관성과 트랜잭션, 분산의 단위, 캡슐화를 통한 복잡성 관리
- 예를 들어 쇼핑몰 사이트에서 주문 Entity 내에 배송주소 정보를 우편번호, 주소1, 주소2, 상세주소, 이런식으로 각 칼럼으로 정의하는 것이 아니라, 주소라는 Value Object를 별도로 작성하고 주문 Entity는 주소 Value Object을 포함하는 방식으로 관계 일관성 및 단순화를 유지

## Domain Event

주문 업무의 특징 중 하나는 주문 당시 데이터(상품, 쿠폰, 결제, 회원, 배송지 등 - 이하 스냅샷Snapshot)를 기준으로 주문 처리를 한다는 것이다. 이러한 스냅샷 데이터를 모두 엔터티로 설계한다면 도메인 모델은 매우 복잡해질 것이다.

- 모델의 특정 행동과 관련된 이벤트
- Aggregate 사이의 일관성을 유지하는데에 사용

## Factory

- 복잡한 Entity의 생성 절차에 캡슐화 할 수 있는 개념
- 복잡한 entity 혹은 aggregate를 생성하는 것을 담당
- 생성하기 복잡한 Aggregate내의 여러 객체를 동시에 생성
- 생성시 Aggregate의 일관성 유지

## Repository

- 도메인 영역과 데이터 인프라스트럭쳐 계층의 분리하여 데이터 계층에 대한 결합도를 낮추기 위한 방안
- aggregate의 논리적 저장소
- data access 처리
- CRUD 담당
- ex) ORM
- 일반적인 저장소와 달리 특정 aggregate에 보다 신속하게 접근하고 aggregate 단위로 데이터를 처리할 수 있게 해 준다
- 생성된 Aggregate에 대한 영속성 관리, 조회,등록,수정,삭제시 Aggregate의 일관성 유지
- DB및 데이터 저장소의 데이터를 조회하고 저장하는 경우 Repository 를 활용
- 리포지터리의 조회 - 검색 조건을 위한 스펙 - 정렬, 페이징

## Bounded Context

- 업무는 분한된 컨텍스트(Bounded Context)로 나눌 수 있으며 각 Context에 사용되는 모델은 서로 분리되어야 하며, 각 하나의 context는 하나의 팀에 할당되어 관리되는 것이 좋다
- Bounded Context 는 Module과 다른 영역이며,도메인 모델에 집중하는 영역
- 잘 구성된 Bounded Context내의 어플리케이션은 독립적 구성이 가능
- 다른 Context간의 모델 및 데이터 참조는 정확히 정의된 인터페이스(API) 만으로 통신
- 특정 모델은 어떤 bounded context에 놓이는가에 따라 다르게 이해됨
- Bounded context 는 독립적으로 서비스 될 때 문제없는
  업무 범위

## Context Map

- 각 bounded context들 사이의 관계

# 목적

- 소프트웨어의 복잡성을 최소화
- Ubiquitous Language 제공
- Loose Coupling, High Cohesion

# Principles

- 핵심 도메인과 그 기능에 집중
- 도메인의 모델의 정교하게 구축
- 어플리케이션 모델을 발전시키고 새롭게 생기는 도메인 관련 이슈를 해결하기 위해 도메인 전문가와 끊임없이 협력

# 설계

[DDD 핵심만 빠르게 이해하기](https://happycloud-lee.tistory.com/94)

## Strategic Design

- Business Domain의 상황(Context: 대상사용자, 상황)에 맞게 설계
- Business Domain의 상황(Context)을 Event storming으로 공유하고, 비즈니스 목적별로 서비스들을 그룹핑
- Strategic Design의 결과물: Domain Model

## Tactical Design

- 개발을 위한 구체적인 설계도
- Model Driven Design
- 결과물
  - 유저 스토리
  - Sequence 다이어그램
  - data 다이어그램 (ERD)
  - 스토리보드
  - API 설계서
  - Message 설계서
    - 마이크로서비스 간 통신 방법

### Layered Architecture

- 목적별 계층으로 나누어 설계
- Presentation
  - UI layer
- Service
  - Domain Model과 Data Access Layer의 class들간의 제어(Control) 또는 연결(Interface) Layer
  - 비즈니스 로직을 여기에 구현하지 않음
- Domain Model
  - Business Logic처리를 담당
- Data Access
  - Database와의 CRUD처리 Layer

# 문서화

- UML 다이어그램
