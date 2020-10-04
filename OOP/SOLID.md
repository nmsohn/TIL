# 개념
## 객체란

- 하나의 역할을 수행하는 메소드와 데이터의 묶음

## 클래스 vs 인스턴스

- 클래스는 본질. 인스턴스는 대상.

## 정보 은닉 vs 캡슐화

- 캡슐화
    - 관련된 데이터와 함수를 묶는 것
    - 재사용 가능하게 함
    - 캡슐 내부와 외부를 구별
- 정보 은닉 (information hiding)
    - 캡슐 내의 요소의 세부 구현 사항을 외부로부터 숨기는 것
    - 캡슐화가 되어 있다고 해서 반드시 정보은닉이 된다고 보기는 어려움
    - 상위 타입 캐스팅, 캡슐화, 구현 은닉

### 예시

```java
class Rectangle{

    public void rectangle(){ System.out.println("rectangle"); }

}

public static void main(String[] args) {

    Rectangle rectangle = new Rectangle(); // --- 1

    rectangle.rectangle(); // Rectangle 클래스에 의존적인 코드

}
```

```java
abstract class Shape{
    abstract public void draw();
}

class Rectangle extends Shape{
    public void draw(){ rectangle();}   
    public void rectangle(){ System.out.println("rectangle"); }
}

public static void main(String[] args) {
    Shape shape = new Rectangle();         // --- 2
    shape.draw();  // Shape 클래스에 의존적인 코드
}
```

# SOLID 원칙

- 유연하고 확장 쉬운 소프트웨어 만들기 위한 수단
- 유지보수가 수월함

## SRP (Single responsibility principle)

- 단일 책임 원칙
- 모든 type, 함수 등은 하나의 책임만을 가져야함
- 어떤 함수를 수정할 때 하나만 수정해도 작동하도록
- 연쇄적인 side effects를 줄임
- 같은 이유로 변하는 것들을 모으고 다른 이유로 변경되는 것을 분리할 것

    > There should never be more than one reason for a class to change

- 응집도는 높게 결합도는 낮게
    - low coupling
        - 각 책임간의 결합도를 최소로 하기
    - shotgun surgery
        - 하나의 책임이 여러군데로 분산
        - 책임이 너무 분산될 경우 다시 cohesion을 높여주어야함
        - 하나로 모아주어 high cohesion

```kotlin
class BigMac {
    var brand = "Macdonald"
    var price = 4900
    var calorie = 525f
    var patty: BigMacPatty = BigMacPatty()
    var bun: SesameBread = SesameBread()
}
```

➡ [extract class](https://refactoring.guru/extract-class)로 분리하기

```kotlin
class BigMac {
    var patty: BigMacPatty = BigMacPatty()
    var bun: SesameBread = SesameBread()
    var information: BigMacInformation = BigMacInformation()
}

class BigMacInformation {
    var brand = "Macdonald"
    var price = 3000
    var calorie = 525f
}
```

- AOP 또한 SRP의 예제가 될 수 있다. 여러개의 클래스가 로깅이나 보안, 트랜잭션과 같은 부분은 공유하고 있을 수 있다. 이런 부분을 모듈화를 통해 각각 필요한 곳에 위빙해주는 방식을 위해 도입된 AOP또한 로깅, 보안, 트랜잭션과 같은 부분을 하나의 모듈에 단일책임으로 부여하여 이를 사용하게 할 수 있도록 함으로써 SRP를 지키는 방법이다

## OCP (Open closed principle)

- 개방 폐쇄 원칙
- 변경되는 것이 무엇인지에 초점
- 확장에 열려있어야 하고 수정에 닫혀있어야 함
- *컴파일 시 의존성을 interface에 고정*시키고 런타임 시 의존성을 변경시킴
    - 추가 클래스를 생성해도 동작은 추가(개방)되지만 기존의 코드는 수정되지 않음(폐쇄)

## interface

```kotlin
interface Bun {}

interface Patty {}

interface Information {
    var brand: String
    var price: Int
    var calorie: Float
}

abstract class Hamburger {
    abstract var bun: Bun
    abstract var patty: Patty
    abstract var information: Information
}
```

```kotlin
class BigMacPatty: Patty {...}
class SesameBread: Bun {...}
class BigMacInformation: Information {
    override var brand = "Macdonald"
    override var price = 3000
    override var calorie = 525f
}
```

```kotlin
class BigMac: Hamburger() {
    override var patty: Patty = BigMacPatty()
    override var bun: Bun = SesameBread()
    override var information: Information = BigMacInformation()
}

class ShanghaiSpicyChickenBurger: Hamburger() {
    override var patty: Patty = ChickenPatty()
    override var bun: Bun = SanghaiBun()
    override var information: Information = SanghaiInformation()
}
```

### 추상화

- 다형성
- 예제

```java
struct Triangle {
    var pointA: Point
    var pointB: Point
    var pointC: Point
}

struct Canvas {
    func draw(triangle: Triangle) {
        drawPoint(triangle.pointA)
        drawPoint(triangle.pointB)
        drawPoint(triangle.pointC)
    }
}
```

```swift
struct Rectangle {
    var pointA: Point
    var pointB: Point
    var pointC: Point
    var pointD: Point
}
```

- 새로운 도형이 추가됐을 때 기존 draw는 호환되지 않음

```swift
protocol Drawable {
    func drawablePoints() -> [Point]
}
```

```swift
protocol Drawable {
    func drawablePoints() -> [Point]
}
struct Triangle: Drawable {
    var pointA: Point
    var pointB: Point
    var pointC: Point    func drawablePoints() -> [Point] {
        return [pointA, pointB, pointC]
    }
}
struct Square: Drawable {
    var pointA: Point
    var pointB: Point
    var pointC: Point
    var pointD: Point    func drawablePoints() -> [Point] {
        return [pointA, pointB, pointC, pointD]
    }
}
struct Canvas {
    func draw(shape: Drawable) {
        for point in shape.drawablePoints() {
            drawPoint(point)
        }
    }
}
```

## LSP (Liskov substitution principle)

- 리스코프 치환 원칙
- override 가급적 피할 것?
- 상위 유형이 들어갈 위치에 하위 유형이 들어가도 작동에 문제가 없어야함
    - Subtypes must be substitutable for their base types
- 위배되는 상황
    - 부모 유형을 잘못 상속받아 잘못된 override를 구현하는 경우
    - 부모 유형이 가진 기능과 다른 기능을 override하는 경우
    - 부모 유형의 작동 방식을 정확하게 알지 못한 상태에서 기능을 override하는 경우
- 부모가 수행하고 있는 책임을 그대로 수행하면서 추가적인 필드나 기능을 제공하려는 경우에만 상속을 하는 것이 바람직
- 자식 클래스는 부모 클래스의 책임을 무시하거나 재정의하지 않고 확장만 수행
- 상속 관계에서는 **일반화 관계(IS-A)**가 성립해야 한다. 일반화 관계에 있다는 것은 일관성이 있다는 것

```kotlin
data class LoginRequest(
    val id: String,
    val password: String
)

class LoginHelper {
    fun login(loginRequest: LoginRequest) {
    	println("NormalLogin : ${loginRequest.id}")
    }
}

class LoginController(val loginHelper: LoginHelper) {
    fun login(loginRequest: LoginRequest) {
        loginHelper.login(loginRequest)
    }
}

fun main() {
    val loginHelper: LoginHelper = LoginHelper()
    val loginController = LoginController(naverLoginHelper)
    val request = LoginRequest("lsb156", "p@ssw0rd")
    loginController.login(request)
}
```

```kotlin
data class LoginRequest(
    val id: String,
    val password: String
)

class LoginHelper {
    fun login(loginRequest: LoginRequest) {
    	println("NormalLogin : ${loginRequest.id}")
    }
}

class KakaoLoginHelper : LoginHelper() {
    override fun login(loginRequest: LoginRequest) {
        println("KakaoLogin : ${loginRequest.id}")
    }
}

class NaverLoginHelper : LoginHelper() {
    override fun login(loginRequest: LoginRequest) {
        println("NaverLogin : ${loginRequest.id}")
    }
}

class LoginController(val loginHelper: LoginHelper) {
    fun login(loginRequest: LoginRequest) {
        loginHelper.login(loginRequest)
    }
}

fun main() {
    val kakaoLoginHelper: LoginHelper = KakaoLoginHelper()
    // val naverLoginHelper: LoginHelper = NaverLoginHelper()
    val loginController = LoginController(kakaoLoginHelper)
    val request = LoginRequest("lsb156", "p@ssw0rd")
    loginController.login(request)
}
```

- Covariant 공변성
    - T’가 T의 서브타입이면, C<T’>는 C<? extends T>의 서브타입
- contravariant 반공변성
    - T’가 T의 서브타입이면, C는 C<? super T’>의 서브타입
- invariant 무공변성
    - C와 C<T’>는 아무 관계가 없다

## ISP (Interface segregation principle)

- Client should not be forced to depend upon interfaces that they do not use
- 인터페이스 분리 원칙
- 상위 레벨의 모듈은 자신이 사용하지 않는 인퍼테이스에 의존하면 안됨
    - 최소한의 인터페이스
    - 인터페이스의 단일 책임
- grasp 원칙의 높은 응집도 원칙과 비슷 (high cohesion)

```kotlin
interface Arithmetic {
    fun plus(a: Int, b: Int): Int
    fun minus(a: Int, b: Int): Int
    fun times(a: Int, b: Int): Int
    fun div(a: Int, b: Int): Int
}

class Test01 : Arithmetic {
    override fun plus(a: Int, b: Int) = a + b
    override fun minus(a: Int, b: Int) = a - b
    override fun times(a: Int, b: Int) = throw NotSupportedException("times")
    override fun div(a: Int, b: Int) = throw NotSupportedException("div")
}

class Test02 : Arithmetic {
    override fun plus(a: Int, b: Int) = throw NotSupportedException("plus")
    override fun minus(a: Int, b: Int) = throw NotSupportedException("minus")
    override fun times(a: Int, b: Int) = a * b
    override fun div(a: Int, b: Int) = a / b
}
```

- test1에서 plus, minus만 사용하고 test2에서 time, div만 사용할 경우

```kotlin
interface Plus { fun plus(a: Int, b: Int): Int }
interface Minus { fun minus(a: Int, b: Int): Int }
interface Times { fun times(a: Int, b: Int): Int }
interface Div { fun div(a: Int, b: Int): Int }

class Test01 : Plus, Minus {
    override fun plus(a: Int, b: Int) = a + b
    override fun minus(a: Int, b: Int) = a - b
}

class Test02 : Times, Div {
    override fun times(a: Int, b: Int) = a + b
    override fun div(a: Int, b: Int) = a - b
}
```

## DIP (Dependency inversion principle)

- *High-level modules should not depend on low-level modules. Both should depend on abstractions.*
- *Abstractions should not depend on details. Details should depend on abstractions.*
- 상위 레벨의 모듈은 하위 레벨의 모듈 interface가 변경되어도 추가로 코드 수정할 필요 없음
- 각각 class 또는 모듈 간의 의존성을 끊고 상위 레벨에서 정의한 추상을 하위레벨 모듈이 구현하게 하는 원칙
- **DIP를 만족한다는 것은 의존관계를 맺을 때, 구체적인 클래스보다 인터페이스나 추상 클래스와 관계를 맺는다는 것을 의미**
- 변화하기 쉬운 것 또는 자주 변화하는 것보다는 변화하기 어려운 것, 거의 변화가 없는 것에 의존하라는 가이드라인을 제공하는 원칙
- 외부에서 의존성을 주입받아 low coupling하게 함
- Adapter 패턴 과 흡사

```java
package kail.study.java.solid.dip;
  
public class Kid {
	private Toy toy;

	public void setToy(Toy toy) {
		this.toy = toy;
	}

	public void play() {
		System.out.println(toy.toString());
	}
}
```

```java
package kail.study.java.solid.dip;
  
public class Lego extends Toy{
	@Override
	public String toString() {
		return "Lego";
	}
}
```

```java
package kail.study.java.solid.dip;
    
public abstract class Toy{
	public abstract String toString();
}

package kail.study.java.solid.dip;

public class Main {
	public static void main(String[] args) {
		Kid kid = new Kid();
		kid.setToy(new Lego());
		kid.play();
	}
}
```

- 변경될 수 있는 장난감은 abstract class 혹은 interface 를 통해 관리함으로써 변경사항에 대해서 유연하게 대처할 수 있고 변화하는 부분을 추상화하여 변화되지 않는 형태로 만든 추상클래스를 의존하기 때문에 DIP원칙과 OCP 둘다 만족하는 형태를 갖는다.