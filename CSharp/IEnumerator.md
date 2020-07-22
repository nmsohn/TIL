# Java vs C# 시리즈
## IEnumerator vs Iterator
### 차이점

- Iterator는 생성될 때 collection의 첫번째 요소를 가리킨다
    - 빈 collection에서 hasNext는 false를 반환
- IEnumerator는 **첫번째 요소 전을 가리키고 있음**
    - 빈 collection에서 MoveNext는 false를 반환
- Iterator는 hasNext로, IEnumerator는 *MoveNext의 결과*로
- Iterator의 **next** = IEnumberator의 **MoveNext**
- Iterator의 next는 다음 요소를 반환
    - IEnumerator의 MoveNext는 **Current** 프로퍼티를 사용해야함
- Iterator는 remove 사용가능함

```java
it = labels.iterator();
while (it.hasNext())
{
    elem = it.next();
}
```

```csharp
en = labels.GetEnumerator();
while (en.MoveNext())
{
    elem = en.Current;
}
```