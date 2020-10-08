# 개념

>> POJO는 마틴파울러가 처음 언급한 용어. 프레임워크에 종속적이지 않은 순수한 자바 오브젝트를 의미

- Plain Old CLR Ojbects
- 단순히 테이터를 저장하고 담아서 전달되는 모델
- Domain의 entity. 데이터에 접근하는 기능 없음
- 관심의 분리 (Separation of concern)
- Entity Framework를 쓰면 data 클래스를 수정할 필요없이 커스텀 data 클래스와 data model을 함께 쓸 수 있음
- POCO를 data model과 함께 사용.
- POCO data class (persistence ignorant objects) 는 data model에 정의된 entity에 mapping되어 있음.
  - 대부분의 query를 지원.

# DTO vs POCO

- DTO는 메소드가 없음
  - public member만 가짐
