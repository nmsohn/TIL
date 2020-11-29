# 개념

- 반복자(iterator)에 대한 C#에서 제공하는 인터페이스

## IEnumerable

- foreach를 사용하기 위한 공통 인터페이스를 제공
- GetEnumerator는 IEnumerator를 리턴값으로 가짐

    ```csharp
    public interface IEnumerable {
       IEnumerator GetEnumerator( );
    }
    ```

- 컬렉션 객체를 사용할 때 포함된 원소들에 대해 반복 작업을 편하게 할 수 있게 해줌
- IEnumerator 인터페이스 를 반환하는 하나의 메서드 GetEnumerator 를 정의 하는 인터페이스
- 예제

    ```csharp
    // Implementing the enumerable pattern
    public System.Collections.IEnumerable SampleIterator(int start, int end)
    {
        for (int i = start; i <= end; i++)
        {
            yield return i;
        }
    }
    ```

- yield 문법으로 리턴

    ```csharp
    public System.Collections.IEnumerator GetEnumerator()
    {
    	yield return "With an iterator";
    	yield return "more than one";
    	yield return "value can be returned";
    	yield return ".";
    }
    ```

# IEnumerator

- 내부적으로 몇번째 원소까지 데이터를 읽었는지 기억하는 cursor와 원소 집합에 대한 레퍼런스를 가짐
- 구현해야하는 메소드

    ```csharp
    public interface IEnumerator {
       object Current { get; }
       bool MoveNext( );
       void Reset( );
    }
    ```

# 예제

[IEnumerator 인터페이스 (System.Collections)](https://docs.microsoft.com/ko-kr/dotnet/api/system.collections.ienumerator?redirectedfrom=MSDN&view=netcore-3.1)

## Person 클래스

```csharp
using System;
using System.Collections;

// Simple business object.
public class Person
{
    public Person(string fName, string lName)
    {
        this.firstName = fName;
        this.lastName = lName;
    }

    public string firstName;
    public string lastName;
}
```

## People 클래스

- IEnumerator<T> GetEnumerator()와 IEnumerator IEnumerable.GetEnumerator()를 구현해야 함
    - 제네릭의 유무 차이

```csharp
public class People : IEnumerable
{
    private Person[] _people;
    public People(Person[] pArray)
    {
        _people = new Person[pArray.Length];

        for (int i = 0; i < pArray.Length; i++)
        {
            _people[i] = pArray[i];
        }
    }

// Implementation for the GetEnumerator method.
    IEnumerator IEnumerable.GetEnumerator()
    {
       return (IEnumerator) GetEnumerator();
    }

    public PeopleEnum GetEnumerator()
    {
        return new PeopleEnum(_people);
    }
}
```

## PeopleEnum 클래스

```csharp
// When you implement IEnumerable, you must also implement IEnumerator.
public class PeopleEnum : IEnumerator
{
    public Person[] _people;

    // Enumerators are positioned before the first element
    // until the first MoveNext() call.
    int position = -1;

    public PeopleEnum(Person[] list)
    {
        _people = list;
    }

    public bool MoveNext()
    {
        position++;
        return (position < _people.Length);
    }

    public void Reset()
    {
        position = -1;
    }

    object IEnumerator.Current
    {
        get
        {
            return Current;
        }
    }

    public Person Current
    {
        get
        {
            try
            {
                return _people[position];
            }
            catch (IndexOutOfRangeException)
            {
                throw new InvalidOperationException();
            }
        }
    }
}
```

# 구현을 해보자

[제네릭과 IEnumerable, IEnumerator](https://smujihoon.tistory.com/177)

```csharp
using System;
using System.Collections.Generic;

namespace Example
{
    public class Example
    {
        public IEnumerator<T> GetEnumerator()
        {
            return new MyListEnumerator(this);
        }

        IEnumerator IEnumerable.GetEnumerator()
        {
            return this.GetEnumerator();
        }

        private class MyListEnumerator : IEnumerator<T>
        {
            private MyList<T> _list;
            private T _current;
            private int _index;

            public MyListEnumerator(MyList<T> list)
            {
                this._list = list;
                this.index = 0;
            }

            public T Current
            {
                get { return _current; }
            }

            object IEnumerator.Current {
                get { return this.Current; }
            }

            public bool MoveNext()
            {
                if(_index < _list.Count)
                {
                    _current = _list[_index];
                    _index++;
                    return true;
                }
                return false;
            }

            public void Reset()
            {
                _index = 0;
            }
        }
    }
}
```

foreach를 사용할 때 제일 먼저 GetEnumerator()를 호출한다. 그러면 var a = GetEnumerator()로 MyListEnumerator 객체를 뽑아 내고 while(a.MoveNext)가 실행된다. 읽을 데이터가 있다면 true를 반환하기 때문에 데이터가 없어질 때 까지 루프는 계속 돌 것이며 body에는 while(a.MoveNext){ a.Current() }를 호출해서 데이터를 뽑아내는 것