# 개념

## boxing

- Value Type의 값을 캐스팅을 통해 Reference Type으로 변경할 경우, Reference Type의 객체는 새 객체를 Managed Heap에 만들고, 스택의 값을 Heap에 복사
- 값 형식 → 참조형식
- 값(Value) 형식을 Object형식 또는 이 값 형식에서 구현된 임의의 인터페이스 형식으로 변환하는 프로세스
- 스택 → 힙 으로 복사
    - 메모리의 Stack 영역에서 Heap 영역으로 데이터가 복사되고, 그 복사된 데이터를 object가 참조
    - gc 관리 힙
    - boxing이 빈번할 수록 성능 나빠짐
- implicit 형변환

## unboxing

- 참조 → 값
- 힙 → 스택으로 복사
- explicit 형변환
- boxing한 것만 unboxing 가능
- boxing된 변수 공간과 별도 메모리 공간에 값을 저장

# 어떤 영향?

- 박싱/언박싱을 대량의 데이타 구조에서 자주 발생시키면, 성능을 크게 저하시키는 요인

## 비용이 큰데 왜 사용하는가?

- 편의성
- C#의 모든 자료형은 system.object로부터 상속받음
- system.object로 데이터를 처리할 경우 특정 타입으로 인한 제약사항에서 자유로워짐
- 배열과 같은 복합자료를 다룰 경우 유용
    - System.collections.arraylist
    - object 타입의 매개변수를 취함
    - 모든 자료형 저장 가능