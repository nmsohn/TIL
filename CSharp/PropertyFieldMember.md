# 개념

## 필드

- 클래스의 변수 멤버
- private으로 선언함
- 그리고 property를 사용해 이 변수를 get, set함
- 직접적으로 영향을 주지 않고 캡슐화
- The private members of a class that contain values specific to the object

```csharp
public class Student
   {
       private int _id;
       private string _name;
   }
```

## 프로퍼티

- accessors 라고 불리는 `함수`
- 프로퍼티로 get, set하여 필드값을 조작할 수 있음

```csharp
public class Student
    {
        private int _id;
        private string _name;
        public int Id { get { return _id; } set { _id = value; } }
        public string Name { get { return _name; } set { _name = value; } }
    }
```

- 여기서 value는 키워드임
  - assigned value를 나타냄
- readonly, writeonly 설정이 가능함
- A property is a member that provides a flexible mechanism to read, write or compute the data of a private field.

## 애트리뷰트

[Attributes (C#)](https://docs.microsoft.com/en-us/dotnet/csharp/programming-guide/concepts/attributes/)

- metadata를 코드와 연결시켜주는 함수
- reflection을 이용해 runtime시에 쿼리화된다
- 괄호로 보통 사용됨

```csharp
[Serializable]
public class SampleClass
{
    // Objects of this type can be serialized.
}
```

## 멤버

[Members - C# Programming Guide](https://docs.microsoft.com/en-us/dotnet/csharp/programming-guide/classes-and-structs/members)

- 클래스와 구조체는 멤버를 가짐
- 멤버는 data와 behaviour
- 모든 클래스 멤버는 클래스 내에서 선언됨
  - constructor나 finalizers는 제외
