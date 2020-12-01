# 개념

- 형식 매개 변수
    - 다중 형식 매개변수
- **System.Collections.Generic**

```java
interface IGenericInterface<T> {
    T Add(T num);
    T Sub(T num);
    T Mul(T num);
    T Div(T num);
}
```

- **코드의 재사용**, 알고리즘의 재사용을 촉진하기 위해서 사용하는 기능
- 하나의 클래스나 인스턴스에서 자신이 원하는 형식으로 선언하여 클래스나 인스턴스를 사용할 수 있음
- 패턴 구현 코드를 작성하고 패턴이 나타나면 구현 코드를 재사용 하는 것에 있다.
    - 즉, 형식 패턴이 바뀐다고 여러번 코드를 작성할 필요를 줄여줌
- 제네릭 형식 매개변수는 자식 클래스로 상속되지 않음
    - 상속받는 하위 클래스도 상위 클래스가 가지고 있는 제약 조건을 가지고 있어야함

## 장점

- 형식 안전성 증대
- 클래스 내에서 명시적으로 의도한 것을 제외한 모든 데이터 형식을 막을 수 있음
- 에러 감소 (InvalidCastException)
- Object 사용으로 인한 Boxing 변환 차단
- 코드 부풀리기 감소
- Boxing을 피하고 메모리를 덜 소모
    - 캐스팅 검사 불필요
    - 코드의 가독성 증가

## 이름 짓기

## 생성자 초기화

- 생성자를 통해 초기 값을 할당해야 하는 제네릭 구조체를 사용하는 경우
    - 모든 필드를 초기화 해야 함

```java
struct Generic<T> : IGeneric<T>
{
  public Generic()
  {
    this.One = default(T);
    this.Two = default(T);
  }

  ...
}
```

## 중첩 제네릭 형식

```java
class Monster<T,U>
{
  // 위의 T,U 와 동일한 형식매개변수를 사용하면 안된다.
  class EasyMonster<V>
  {
    void SetName(T name, U value, V key)
    {
      ...
    }
  }
}
```

# 종류

## 클래스

```java
public class Chocolate<T>
{
    private T _choco;
    public Chocolate(T choco)
    {
        _choco = choco;
    }
    ...
}

...

public static void main()
{
    Chocolate<string> chocoPie = new Chocolate<string>("초코파이");
}
```

## 메소드

```java
public void MixedSomething(T choco, T pie)
{
    Console.WriteLine($"Mixed: {Choco}{pie}");
    Console.WriteLine($"Added: {Choco} + {pie} = {choco + pie}");
}
```

- method를 작성해야 할 때, int, long, double, float 등 모든 타입에 대해 메서드를 오버로딩 형태로 만들지 않고 하나의 메서드에서 모두 처리가 가능
- 공변
    - 자신과 자식 객체를 허용
- 반공변
    - 자신과 부모 객체 허용

# 제약조건

- where 키워드를 써서 제약을 둘 수 있음

    ```java
    public class Chocolate<T> where T : class
    {
        private T _choco;
        public Chocolate(T choco)
        {
            _choco = choco;
        }
        ...
    }

    public class Milk
    {
        ...
    }

    ...

    public static void main()
    {
        Chocolate<string> chocoPie = new Chocolate<string>(1234); // 컴파일 에러발생
        Chocolate<string> chocoPie = new Chocolate<string>(new Milk()); // 컴파일 O
    }
    ```

    - Chocolate 클래스의 T는 class만 받음
        - 참조 형태인 파라미터일 때만 동작

    ```java
    public void MixedSomething(T choco, Q pie)
        where T : class 
        where Q : Milk, new()
    {
        ...
    }
    ```

- 연산자 제약 조건은 허용하지 않음
    - T 형식을 모르기 때문에
- OR 조건이 지원되지 않음
    - 컴파일러 미지원
- 대리자와 열거형 형식의 제약 조건은 유효하지 않음
    - 이벤트 발생의 시그니처는 해당 형식의 데이터 형식을 알 수 없음
- 기본 생성자에 대해서만 제약 조건 허용

    ```java
    public class MonsterBook<TKey>
      where TKey : Monster, new()
    {

      private TKey key;

      public void CreateKey()
      {
        TKey key = new TKey();
      }

    }
    ```

## Class

- 클래스의 제네릭 인수에 클래스, 인터페이스, 대리자, 배열 형식이 올 수 있음.
- 참조 형식이면 됨

```java
where T : class
```

## Struct

- 구조체는 제네릭 인수가 값 형식이어야함.
- Nullable은 제외

```java
where T : struct
```

## 인터페이스

- 특정 인터페이스만 가능
- 다중 제약 조건 사용 가능 AND

```java
where T : 특정_클래스_이름
```

## new() 생성자

- T가 매개변수(parameter) 없는 생성자를 가지고 있어야함
- 제일 마지막에 나열해야함

```java
where T : new()
```

```java
class Vanilla<T, Q>
    where T : class
    where Q : Chocolate, new()
{
    ...
}
```

## unmanaged

- 관리되지 않는 형식
- 모든 중첩 수준에서 참조 형식 필드를 포함하지 않는 형식.
- 주로 메모리 블록 조작하는 경우에 사용
- sizeof 사용 가능

```java
where T : unmanaged
```

# Delegate

# Java vs C#

[C# - 제네릭(Generic)을 알아봅시다](https://centbin-dev.tistory.com/4)

*Java와 C# 제네릭의 가장 큰 차이점은 중간언어(JVM, .NET)에 제네릭 타입 정보의 존재 유무의 차이가 있다. 그래서 **.NET의 제네릭은 타입 안정성이 있지만** JVM에서는 타입 안정성이 없다*

*JVM에서는 타입 정보를 저장하지 않기 때문에 .NET에서 가지고 있는 Boxing, UnBoxing에 대한 성능 이득을 챙기지 못한다.*