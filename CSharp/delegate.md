# 개념

```
한정자 delegate 반환형 델리게이트명(매개변수..); // delegate 키워드
```

- 메소드의 대리인
    - 메소드를 객체처럼 주고 받을 수 있음
- 타입이 method임. ***메소드를 참조***
    - Delegate는 메소드 레퍼런스의 OOP적인 Wrapper
    - 특정 함수 혹은 함수들의 주소를 담아두는 포인터와 비슷한 개념
- C++ 의 함수 포인터와 비슷
- 람다와 개념이 같음
- 함수형 프로그래밍의 일급 함수와 비슷
- 콜백 메서드 구현을 위해 사용
    - c++ boost::bind()
    - 함수에서 다른 함수를 호출
- **System.Delegate** 클래스에서 파생되는 클래스 타입
- 내부적으로 Count 만큼 List를 순회하는 연결리스트구조를 생성
- 내부적으로 **MulticastDelegate** 클래스에서 기능을 상속

## 예시
```
delegate void Type1(void);  // void func1(void)
delegate int Type2(int,int); // int func2(int, int)
delegate string Type3(double); // string func3(double)
delegate float Type4(int); // float func4(int)

Type1 F1Delegate;
F1Delegate = new Type1(func1);
Type2 F2Delegate;
F2Delegate = new Type2(func2);
Type3 F3Delegate = func3; // C# 2.0부터 사용가능
int a = 5;
```

## Delegate 체인
- 여러개의 메소드 참조 가능
- +=로 추가, -=로 제거
    - Delegate.Combine(), Delegate.Remove()
    - combine()을 사용하면 remove()로 제거해야함
- 멀티 캐스트

```
PDelegate pd = (PDelegate)Delegate.Combine(new PDelegate(Plus), new PDelegate(Minus), new PDelegate(Division), new PDelegate(Multiplication));
```

# Event와 차이점
## Event

```
한정자 event 델리게이트_이름;
```

- delegate에 구독 개념을 추가하기 위한 한정자
- 이벤트는 특정 조건이 발생할 때 메소드를 호출하는 용도
- 이벤트 변수는 private같은 느낌
    - 이벤트 변수는 내부에서만 사용 가능
    - event를 사용함으로써 해당 event를 가지고 있는 클래스 이외의 외부 클래스에서는 delegate를 실행시킬수 없다는 제약이 생김
    - 외부 클래스는 event 체인에 자신의 함수를 추가, 삭제만 할 수 있게되고 이것을 개념적으로 '구독'
- 이벤트 선언시 빈 익명 메소드로 초기화
- 이벤트는 public으로 선언되어도 외부에서 호출이 안됨

## 대리자
- delegate는 public
- delegate 변수는 자신이 속한 클래스 외부에서도 호출이 가능
- 델리게이트는 public이나 internal 키워드로 구현되면 클래스 외부에서도 호출 가능

# 익명 메소드
- 익명 메소드를 사용하기 위해서는 이를 참조할 수 있는 delegate 변수가 있어야함
- 델리게이트 변수를 생성하기에 앞서 delegate 타입을 선언해야 함
- 익명 메소드마다 그 타입에 맞는 delegate 타입과 변수를 따로 따로 선언해야 할 것 → 비효율적

```
delegate instance = delegate ( 매개변수 )
					{
                    	// 코드
                    }
```

# 미리 정의된 delegate
- Func와 Action은 미리 선언된 델리게이트 변수로써 별도의 선언없이 사용 가능

## Func
- 반환값이 있음
- 닷넷에는 총 17가지의 Func 델리게이트가 존재
    - Action delegate는 System 네임스페이스에서 제공되는데, 파라미터의 수에 따라 0개부터 16개의 파라미터까지 받아들이는 delegate
- Func<T, TResult> 이름;
- 마지막 매개변수 TResult가 반환값의 형태
- 하나의 반환값을 가지기 때문에 여태까지 예시로 다룬 Observer 패턴 보다 람다식을 활용해 빠르고 간단하게 특정 값을 얻어낼 때 주로 사용
- Func는 반드시 리턴 타입이 - Generic 폼 내에, 즉 템플릿 파라미터 안에 - 존재
- Func<T>의 T는 리턴값의 타입을 가리키며, 이 경우 입력파라미터는 없음

```
public class PrintManager : MonoBehaviour
{
    public Func<string, string> PrintName;
    
    void Start()
    {
        PrintName = (s) => { return "my name is "+ s; };
        Debug.Log(PrintName("john"));
    }
}
```

## Action
- 반환값이 없음
- 닷넷에는 총 17가지의 Action 델리게이트
- Action<T> 이름

```csharp
public Action<string> PrintAllEvent;
```

- 일종의 '동작'이므로 반환 값 없이 연결 된 일련의 함수들을 실행
- 네임스페이스에 using System;를 넣어주어야 쓸 수 있음
- Action은 이미 정의 된 delegate를 가져와 사용하는 것이기 때문에 매개변수 명이 기본값으로 고정

## Predicate
- Predicate<T>
- 리턴값이 반드시 bool이고 입력값이 T 타입인 delegate
- 입력 파라미터는 1개
- .NET의 Array나 List 클래스의 메서드들에서 자주 사용
- Predicate<T>은 Func<T, bool>와 같이 표현

```
Predicate<int> p = delegate(int n)
{
   return n >= 0;
};
bool res = p(-1);

Predicate<string> p2 = s => s.StartsWith("A");
res = p2("Apple");
```