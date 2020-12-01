# 개념

- 어떤 클래스가 **최초 한번만 메모리를 할당(static)**하고 그 메모리에 인스턴스를 만들어 사용하는 디자인 패턴
- 생성자가 여러번 호출되도 생성되는 객체는 하나.
    - 이후 생성자 호출시 최초로 생성된 객체를 반환함
- Java에서는 private으로 선언해서 생성 불가능하게 하고 getInstance()로 씀

# 왜 쓰는가?

- 고정된 메모리 영역을 얻으면서 한 번의 new로 인스턴스를 사용하여 *메모리 낭비를 방지*
- 싱글톤으로 만들어진 클래스의 인스턴스는 global 인스턴스.
    - 데이터 공유가 쉬움
- DBCP(DataBase Connection Pool) 에서 주로 쓰임

# 예제

[자바 싱글톤 패턴 (Singleton Pattern)](https://velog.io/@kyle/%EC%9E%90%EB%B0%94-%EC%8B%B1%EA%B8%80%ED%86%A4-%ED%8C%A8%ED%84%B4-Singleton-Pattern)

```java
package kail.study.java.design.single;

public class Printer {
	private static Printer printer = null;
	
	private Printer(){}
	
	public static Printer getInstance() {
		if(printer == null) {
			printer = new Printer();
		}
		return printer;
	}
	
	public void print(String input) {
		System.out.println(input);
	}
}
```

- thread가 여러개면 조건문이 동시에 두번 돌 수 있음

```java
public class Printer {
    	private static Printer printer = null;
  	private int count = 0;
  
  	private Printer(){}
  
  	public static Printer getInstance() {
  		if(printer == null) {
  			printer = new Printer();
  		}
  		return printer;
  	}
  
  	public void print(String input) {
  		count++;
  		System.out.println(input + "count : "+ count);
  	}
  }
```

# 문제점

- 클래스 인스턴스 간에 결합도가 높아져 **OCP** 위배
- 수정과 테스트가 어려움
- multi thread 환경에서 동기화처리를 안하면 인스턴스가 두개 생성될 수 있음

# Multi thread 에서 thread safe한 싱글톤 클래스를 만드는 방법 (Java)

## Eager Initialization

- 가장 기본적인 방식
- 객체를 미리 생성해 놓음
    - 클래스가 로딩될 때 싱글톤 객체가 생성
    - thread safe
- 사용유무와 관계없이 클래스가 로딩되는 시점에 항상 싱글톤 객체가 생성되고, 메모리를 잡고있기 때문에 비효율적

```java
package SingleTonExample;

public class EagerInitialization {
    private static EagerInitialization instance = new EagerInitialization(); // 전역 변수로 생성

    private EagerInitialization() {} //새로운 인스턴스 생성 방지

    public static EagerInitialization getInstance() { //인스턴스에 접근할 수 있는 유일한 방법
        return instance;
    }

}
```

```java
package kail.study.java.design.single;
  
public class Printer {
	private static Printer printer = new Printer();
	private static int count = 0;

	private Printer(){}

	public static Printer getInstance() {
		return printer;
	}

	public synchronized static void print(String input) {
		count++;
		System.out.println(input + "count : "+ count);
	}
}
```

## Lazy initialization

- static 메소드가 호출되면 instance가 만들어짐
- 인스턴스가 사용되는 시점에 인스턴스 생성
    - instance가 null 인 경우에만 new ThreadSafeLazyInitialization();
    - 필요할 때 인스턴스 생성
        - 메모리 누수 방지
- multi thread 환경에서 동시에 getInstance() 호출 시 두 번 생성됨
    - thread safe하지 않음
- getInstance()에 lock을 하는 방식이라 느림

```java
package SingleTonExample;

public class LazyInitialization {
    private static LazyInitialization instance;
    private LazyInitialization(){}

    public static LazyInitialization getInstance(){

        if(instance == null){
            instance = new LazyInitialization();
        }
        return instance;
    }
}
```

```java
public class Printer {
   // 외부에 제공할 자기 자신의 인스턴스
   private static Printer printer = null;
   private int counter = 0;
   private Printer() { }
   // 인스턴스를 만드는 메서드 동기화 (임계 구역)
   public synchronized static Printer getPrinter(){
     if (printer == null) {
       printer = new Printer(); // Printer 인스턴스 생성
     }
     return printer;
   }
   public void print(String str) {
     // 오직 하나의 스레드만 접근을 허용함 (임계 구역)
     // 성능을 위해 필요한 부분만을 임계 구역으로 설정한다.
     synchronized(this) {
       counter++;
       System.out.println(str + counter);
     }
   }
}
```

## Thread safe lazy initialization

- thread safe
- synchronized 키워드
    - lock, unlock 처리하기 때문에 내부적으로 많은 비용 발생
    - 성능저하 발생

```java
public class ThreadSafeLazyInitialization{
    private static ThreadSafeLazyInitialization instance;
    private ThreadSafeLazyInitialization(){}
    
    public static synchronized ThreadSafeLazyInitialization getInstance(){
        if(instance == null){
            instance = new ThreadSafeLazyInitialization();
        }
        return instance;
    }
}
```

## Lazy initialization + Double checked locking

- 게으른 초기화의 성능 저하를 완화

```java
public class ThreadSafeLazyInitialization {
    private volatile static ThreadSafeLazyInitialization instance;
    private ThreadSafeLazyInitialization(){}
    
    public static ThreadSafeLazyInitialization getInstance(){
        
        if(instance == null){
            synchronized (ThreadSafeLazyInitialization.class) {
                if(instance == null)
                    instance = new ThreadSafeLazyInitialization();
            }
 
        }
        return instance;
    }
}
```

## Demand Holder Idiom

- 클래스 안에 클래스 (holder)를 두어 JVM의 class loader 매커니즘과 class 가 로드되는 시점을 이용
- holder는 getInstance() 메소드가 호출되지 전에는 참조되지 않음
- 가장 많이 사용

```java
public class Something {
    private Something() {
    }
 
    //중첩 클래스
    private static class LazyHolder {
	//한번 만 호출됨
        public static final Something INSTANCE = new Something();
    }
 
    public static Something getInstance() {
        return LazyHolder.INSTANCE;
    }
}
```

## Enum initilization

- enum 타입은 프로그램 내에서 한번 초기화됨

```java
package SingleTonExample;

public enum EnumSingleTon {

        INSTANCE;

        public void excute(String arg){

        }

}
```

# C#의 싱글톤

[C# and beforefieldinit](https://csharpindepth.com/articles/BeforeFieldInit)

## 가장 기본적인 형태

```csharp
public sealed class Singleton {
    private static readonly Singleton INSTANCE = new Singleton();

    private Singleton() {} //인자 없고 private 생성자. 인스턴스화 방지

    public static Singleton Instance {
        get {
            return INSTANCE;
        }
    }
}
```

```csharp
public static class Singleton {

    private static readonly MyOtherClass INSTANCE = new MyOtherClass();

    public static MyOtherClass Instance {
        get {
            return INSTANCE;
        }
    }
}
```

## Thread safe

- 동기화 문제는 해결
- instance 요청할 때마다 lock을 쓰게 되어 성능저하

```csharp
public sealed class Singleton
    {
    private static Singleton instance = null;
    private static readonly object padlock = new object();

    Singleton()
    {
    }

    public static Singleton Instance
    {
	      get
	      {
		    lock (padlock)
		    {
			    if (instance == null)
			    {
				    instance = new Singleton();
			    }
		    return instance;
		    }
		}
	}
    }
```

## Thread safe using double check locking

```csharp
// Bad code! Do not use!
    public sealed class Singleton
    {
        private static Singleton instance = null;
        private static readonly object padlock = new object();

        Singleton()
        {
        }

        public static Singleton Instance
        {
            get
            {
            if (instance == null)
                {
                lock (padlock)
                    {
                        if (instance == null)
                        {
                        instance = new Singleton();
                        }
                    }
                }
            return instance;
            }
        }
    }
```

## lock 없이 thread safe

```csharp
public sealed class Singleton
{
    private static readonly Singleton instance = new Singleton();

    // Explicit static constructor to tell C# compiler
    // not to mark type as beforefieldinit
    static Singleton()
    {
    }

    private Singleton()
    {
    }

    public static Singleton Instance
    {
        get
        {
            return instance;
        }
    }
}
```

## lazy initialization, thread safe, nested class 사용

```csharp
public sealed class Singleton
{
    private Singleton()
    {
    }

    public static Singleton Instance { get { return Nested.instance; } }

    private class Nested
    {
        // Explicit static constructor to tell C# compiler
        // not to mark type as beforefieldinit
        static Nested()
        {
        }

        internal static readonly Singleton instance = new Singleton();
    }
}
```

## Lazy<T>

- 닷넷 4 이상 부터 사용 가능

```csharp
public sealed class Singleton
{
    private static readonly Lazy<Singleton> lazy = new Lazy<Singleton>(() => new Singleton());

    public static Singleton Instance { get { return lazy.Value; } }

    private Singleton()
    {
    }
}
```