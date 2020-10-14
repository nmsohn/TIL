# 개념

[HashSet Class (System.Collections.Generic)](https://docs.microsoft.com/en-us/dotnet/api/system.collections.generic.hashset-1?view=netcore-3.1)

- A set is a collection that contains no duplicate elements, and whose elements are in no particular order (순서 없음)
- 중복 허용하지 않음
- null 허용
- 동기화 처리되지 않음

  - 동기화하려면 따로 처리해야함

  ```csharp
  Set s = Collections.synchronizedSet(new HashSet(...));
  ```

- set 인터페이스를 구현한 hash table
- 시간복잡도 `O(1)`

```csharp
HashSet<string> hset1 = new HashSet<string>();
hset1.Add("Captain America");
hset1.Add("Ironman");
hset1.Add("Hulk");
hset1.Add("Thor");

HashSet<string> hset2 = new HashSet<string>();
hset2.Add("Black Widow");
hset2.Add("Hawk Eye");
hset2.Add("Spiderman");
hset2.Add("Antman");

//UnionWith 메서드는 다른 컨테이너의 데이터를 합칠 때 사용하는 메서드
hset1.UnionWith(hset2);
foreach (var val in hset1)
{
    System.Console.WriteLine($"{val}");
}
```

- 합칠 대상은 동일한 HashSet/SortedSet 뿐 아니라 동일한 Generic 타입을 가진 IEnumerable 객체이면 다 가능
- HashSet<int>, List<double> 같이 타입이 다른 컨테이너는 통합이 불가능
