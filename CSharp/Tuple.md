# Tuple 클래스

- Systems
- Object 상속
- 참조 타입. managed heap에 할당됨. GC로 관리
- read-only
- 재사용성
- Return multiple values
- immutable
- a specific number and sequence of values

```csharp
public static class Tuple
```

## 선언 및 초기화

```csharp
List<Tuple<int, string>> list = new List<Typle<int, string>>();

new Typle<int, string>(1, "cat");
```

```csharp
static void Main(string[] args)
        {
            // 일반적인 튜플
            Tuple<int, string, bool> tuple =
               new Tuple<int, string, bool>(10, "csharpstar", true);

            // Access tuple properties.
            if (tuple.Item1 == 10)
            {
                Console.WriteLine(tuple.Item1);
            }
            if (tuple.Item2 == "easywcf")
            {
                Console.WriteLine(tuple.Item2);
            }
            if (tuple.Item3)
            {
                Console.WriteLine(tuple.Item3);
            }

            System.Console.ReadLine();
        }
```

```csharp
var primes = Tuple.Create(2, 3, 5, 7, 11, 13, 17, 19);
Console.WriteLine("Prime numbers less than 20: " +
                  "{0}, {1}, {2}, {3}, {4}, {5}, {6}, and {7}",
                  primes.Item1, primes.Item2, primes.Item3,
                  primes.Item4, primes.Item5, primes.Item6,
                  primes.Item7, primes.Rest.Item1);
// The example displays the following output:
//    Prime numbers less than 20: 2, 3, 5, 7, 11, 13, 17, and 19
```

## 정렬

- 정렬 기능 구현이 어렵고, 비교 기능을 가진 delegate가 필요

```csharp
using System;

using System.Collections.Generic;

class Program
{
    static void Main()
    {

	    List<Tuple<int, string>> list = new List<Tuple<int, string>>();

	    list.Add(new Tuple<int, string>(1, "cat"));
	    list.Add(new Tuple<int, string>(100, "apple"));
	    list.Add(new Tuple<int, string>(2, "zebra"));

	    // Use Sort method with Comparison delegate.
	    // ... Has two parameters; return comparison of Item2 on each.

	    list.Sort((a, b) => a.Item2.CompareTo(b.Item2));

	    foreach (var element in list)
	    {
	        Console.WriteLine(element);
	    }
    }

}

==

(100, apple)
(1, cat)
(2, zebra)
```

## Dictionary와 비교

- tuple은 dictionary에 비해 메모리 할당만 늦음
- dictionary는 struct → 값 타입
- tuple은 class → 참조 타입
- 객체를 많이 생성할 땐 dictionary
- 함수 호출의 경우 tuple

  - tuple을 인자로 넘길 때 4 byte (32비트 환경) 씩 복사하지만 dictionary는 더 많은 byte 복사

    8.23 ns -- Allocate Tuple
    0.32 ns -- Allocate KeyValuePair (25x faster!)

    1.93 ns -- Pass Tuple as argument
    2.57 ns -- Pass KeyValuePair as argument

    1.91 ns -- Return Tuple
    6.09 ns -- Return KeyValuePair

    2.79 ns -- Load Tuple from List
    4.18 ns -- Load KeyValuePair from List

# Tuple 타입

- C# 7.0부터 지원
- tuples provide a simple way to store a set of values
- Tuples do almost everything that anonymous types do and more. Their one disadvantage—as you’ll see soon—is runtime type erasure with named elements
- to safely return multiple values from a method without resorting to out parameters (something you cannot do with anonymous types)
- 간단하게 정보의 열거를 보고 싶을 때 사용이 되면 편리

# 튜플 사용하려면

```csharp
dotnet cli 에서는 아래와 같이 명령을 실행하여 추가한다.
dotnet add package System.ValueTuple --version 4.5.0

package manager 에서는 아래와 같이 명령을 실행항 추가한다.
Install-Package System.ValueTuple -Version 4.5.0
```

- csproj 에 추가

```csharp
`아래와 같이 latest 로 언어 버전을 최신으로 해야 C# 7.1 에 대한 사용이 가능함을 참고하자`
    ...
    <LangVersion>latest</LangVersion>
  </PropertyGroup>

  `ValueTuple 을 4.4.0 버전을 추가한 결과 아래와 같이 나타난다`
  <ItemGroup>
    <PackageReference Include="System.ValueTuple" Version="4.4.0" />
  </ItemGroup>
```

## 선언 및 초기화

```csharp
var bob = ("Bob", 23);    // Allow compiler to infer the element types

Console.WriteLine (bob.Item1);   // Bob
Console.WriteLine (bob.Item2);   // 23
```

```csharp
(int X, int Y) point = (10, 5);
Console.WriteLine($"X: {point.X}, Y: {point.Y}");
```

```csharp
var point = (X: 10, Y: 5);
Console.WriteLine($"X: {point.X}, Y: {point.Y}");
```

```csharp
int x = 10, y = 5;
var point = (x, y);
Console.WriteLine($"X: {point.x}, Y: {point.y}");
```

```csharp
(int, int) point = (10, 5);
Console.WriteLine($"X: {point.Item1}, Y: {point.Item2}");
```

```csharp
(int X, int Y) point = (46, 3);
(int Width, int Height) dimensions = point;
(int Age, int NumberOfChildren) person = point;
```

```csharp
var t = (Sum: 4.5, Count: 3);
Console.WriteLine($"Sum of {t.Count} elements is {t.Sum}.");

(double Sum, int Count) d = (4.5, 3);
Console.WriteLine($"Sum of {d.Count} elements is {d.Sum}.");
```

## out 키워드 파라미터로 사용

```csharp
var limitsLookup = new Dictionary<int, (int Min, int Max)>()
{
    [2] = (4, 10),
    [4] = (10, 20),
    [6] = (0, 23)
};

if (limitsLookup.TryGetValue(4, out (int Min, int Max) limits))
{
    Console.WriteLine($"Found limits: min is {limits.Min}, max is {limits.Max}");
}
// Output:
// Found limits: min is 10, max is 20
```

# Tuples vs System.Tuple

[Tuple types - C# reference](https://docs.microsoft.com/en-us/dotnet/csharp/language-reference/builtin-types/value-tuples)

- C# tuples, which are backed by `System.ValueTuple` types, are different from tuples that are represented by System.Tuple types.
- `ValueTuple` 타입은 값 타입. mutable
  - 데이터 멤버는 필드
- `Tuple` 타입은 참조 타입. immutable
  - 데이터 멤버는 프로퍼티
