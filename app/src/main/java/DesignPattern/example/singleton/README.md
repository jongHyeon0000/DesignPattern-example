# 6가지 종류의 싱글톤 패턴(Singleton Pattern)과 안전성이 검증된 두 싱글톤 패턴(Lazy Holder, Enum Singleton)

## 불변식(Invariant) 
> 어떤 객체가 정상적으로 작동하기 위해 절때 허무러지지 않아야 하는 값, 식, 상태 등을 뜻한다.

Car라는 객체에는 현재 보유한 연료의 양과 최대로 넣을 수 있는 연료의 양이 있다고 예를 들자

<pre>
<code>
  class Car{
    private static final int MAX_OIL_VALUE = 1000;
    private int oilValue;

    Car(int oilValue){
      this.oilValue = oilValue;
    }
  }
</code>
</pre>

이때 oilValue의 값이 0 미만이 되거나, MAX_OIL_VALUE를 넘어서는 경우는 정상적인 자동차의 상태(State)
라고 하기는 어렵다.

이런 경우를 **Car 객체(Object)의 불변식(Invariant)이 깨졌다.** 라고 부를 수 있다.

<pre>
<code>
  class Car{
    private static final int MAX_OIL_VALUE = 1000;
    private int oilValue;

    Car(int oilValue){
      // 불변식(Invariant)를 유지하기 위한 방어적 아규먼트 체크
      if(oilValue < 0 && oilValue > MAX_OIL_VALUE){
        // 불변 클래스(Immutable Class)가 아니라면 예외를 던질 때 실패 원자성(Failure Atomicity)을 고려해야 한다.
        throw new IllegalArgumentException();
      }
      this.oilValue = oilValue;
    }
  }
</code>
</pre>

------------

## 불변 클래스(Immutable Class)
> 객체 내부의 값을 처음 객체가 생성 될 때 외에는 수정, 변경할 수 없는 클래스를 말한다.

안정성을 검증받은 불변 클래스를 설계하는 법은 다음과 같다.
<pre>
<code>
  class People{
    int age;
    String name;

    People(int age, String name){
      this.age = age;
      this.name = name;
    }


    public void setAge(int age){
      this.age = age;
    }

    public void setName(String name){
      this.name = name
    }
  }
</code>
</pre>

1. 객체의 상태를 변경하는 메서드(변경자, setter)를 제공하지 않는다.

<pre>
<code>
  class People{
    int age;
    String name;

    People(int age, String name){
      this.age = age;
      this.name = name;
    }
  }
</code>
</pre>

2. 클래스를 확장(extend)할 수 없도록 한다. 즉 상속을 문법적으로 금지한다.

<pre>
<code>
  // 이 클래스는 절대 재정의 될 수 없음을 명확하게 전달한다.
  final class People{
    int age;
    String name;

    People(int age, String name){
      this.age = age;
      this.name = name;
    }
  }
</code>
</pre>

3. 모든 필드를 final로 선언한다.

<pre>
<code>
  final class People{
    // 이 클래스의 필드는 절대 변경되지 않음을 명확하게 전달한다.
    final int age;
    final String name;

    People(int age, String name){
      this.age = age;
      this.name = name;
    }
  }
</code>
</pre>

4. 모든 필드의 접근 제어자(Access Modifier)를 private으로 선언한다.

<pre>
<code>
  final class People{
    // 이 클래스의 필드는 외부에서 접근할 수 없게 문법적으로 안전하게 보호받고 있다.
    // 외부 클라이언트는 이 필드를 직접 이용하지 못하니, People 클래스는 내부 구현을 수정하는 것에 부담이 줄어든다.
    // (SOLID, Open Closed Principle), (GRASP, Low coupling), 정보 은닉(information hiding)
    private final int age;
    private final String name;

    People(int age, String name){
      this.age = age;
      this.name = name;
    }
  }
</code>
</pre>

5. 자신 외에는 내부의 가변 컴포넌트에 접근할 수 없도록 한다.

<pre>
<code>
  final class People{
    private final int age;
    private final String name;

    // 불변 클래스 내부의 가변 객체가 있다면 반드시 이 객체의 참조(Reference)를 얻을 수 없게 해야한다.
    private Book book = new Book();

    People(int age, String name){
      this.age = age;
      this.name = name;
    }
  }

  class Book{
    ...
  }
</code>
</pre>

  **불변 객체는 단순하다.**
>  생성된 시점부터 GC에 의해 파괴될 때 까지 값이나 상태가 변하지 않으니, 번거로운 아규먼트 체크가 필요없다.

  **불변 객체는 스레드 안전(Thread-safe)하다.**
> 최초 생성 이후로 값이나 상태를 변경할 수 없으니 당연한 결과이다.

  **불변 객체는 안심하고 공유가 가능하다.**
> 불변 객체를 상속해 구체 클래스(Concrete Class)를 만들 수 없기 때문이다. 따라서 API 문서 작성 부담 또한 줄어든다.

  **객체 재사용성이 높다.**
> 예컨대 자주 사용하는 상수 풀(public static final float PI = 3.14 등..)을 모아두거나,
> 다른 클래스의 구현을 도와주는 도우미 역할의 클래스로 적합하다. 

  **불변 객체임이 보장된다면, 불변 객체끼리의 내부 데이터 공유는 안전하다.**
> 자주 사용하는 BigInteger, String 객체의 내부 구현이 그 이유이다.

  **불변 객체는 그 자체로 실패 원자성을 제공한다.**
> 불변 객체의 불변식은 항상 보장되므로, 언제 어디서 예외를 던져도 객체의 내부 상태는 불일치 상태가 되지 않는다.

  **내부 값이 조금이라도 다르면 반드시 독립된 객체로 만들어야 한다.**
> 따라서 불변 객체의 무분별한 사용은 큰 비용이 따른다.
>
> 이를 해결하기 위해 불변 클래스에 비해 클라이언트가 다루기 어렵고, 위험하며, 복합한 로직으로 구현된 가변 클래스를
> 여러개 구현하고, 하나의 불변 클래스 내부에 이 가변 클래스들이 실질적인 일을 하는 설계를 사용하는 경우가 많다.
> (가변 동반 클래스, Companion Class)
>
> 이런 불변 클래스의 내부 구현은 수많은 가변 클래스로 이루어져 있다.
> 클라이언트는 복잡한 내부는 모른채 다양한 기능이 모인 불변 클래스를 쉽고 안전하게 다루거나,
> 성능을 위해 직접 가변 클래스를 쓸 수도 있다.
>
> 대표적인 예가 불변 클래스 String과 가변 동반 클래스 StringBuilder이다.

------------

## 실패 원자성(Failure Atomicity)
> 호출된 메서드가 실패 하더라도 해당 객체는 메서드 호출 전 상태를 유지해야 한다. (실패 원자적, Failure-Atomic)
> 실패 원자적인 메서드나 객체는 예외(Exception)가 발생하더라도, 정상적으로 사용할 수 있는 상태를 유지한다.

간단한 Stack 예제를 실패 원자적인 예제로 만들어 보자 

<pre>
<code>
  class Stack<T>{
  private int size; // 스택의 현재 사이즈
  private Object[] elements; // 스택 내부 배열
  
  Stack(int capacity) {
    size = 0;
    elements = new Object[capacity];
  }
  
  public void push(T t) {
    ensureCapacity();
    elements[++size] = t;
  }
  
  private void ensureCapacity() {
    if(size <= 0) {
      elements = Arrays.copyOf(elements, elements.length * 2 + 1);
    }
  }
  
  public T pop(){    
    @SuppressWarnings("unchecked") T result = (T) elements[--size];
    elements[size] = null;
    
    return result;
  }

  ...

  }
  </code>
  </pre>

1. 불변 클래스는 태생적으로 실패 원자성을 보장한다.

> 불변 객체는 복구하기 힘든 예외가 발생 하여 원본 객체랑 같은 새로운 객체를 생성하는 방법을 사용하긴 힘들다.
> 그러나 원본 객체가 불안정하고 불일치적인 상태에 빠지는 경우는 없다.

2. 메서드의 파라미터가 존재하거나 실패할 가능성이 있는(예외가 throw 될 수 있는) 코드, 객체 내부의 상태를 변경하려는 경우, 유효성을 검사하는 코드를 최상위에 삽입한다. 
<pre>
<code>
  class Stack<T>{

  ...
  
  public T pop(){
    if(size == 0){
      throw new EmptyStackException();
    }    

    @SuppressWarnings("unchecked") T result = (T) elements[--size];
    elements[size] = null;
    
    return result;
  }

  ...

}
</code>
</pre>
> 만약 해당 코드가 삽입되지 않았다면 size의 값은 1 줄어들었으나, 배열 인덱스 접근 도중 예외가 발생하므로
> size의 불변식이 깨지게 된다. 따라서 이후 스택 객체를 정상적으로 사용 할 수 없다.

> 또한 배열을 다룰 때 발생 할 수 있는 잠재적 예외를 클래스에 맞는 명확한 추상화 수준의 예외로 바꾸어 준다.
> 위와 같은 상황에서 ArrayIndexOutOfBoundsException 과 EmptyStackException 중 무엇이 더 직관적인가?

> 예컨데 자바의 TreeMap의 원소들은 항상 정렬된 상태를 유지하는데, TreeMap이 비교할 수 없는 엉뚱한 원소가 들어오려 하면
> 해당 원소를 TreeMap에 넣는 중 어떤 예외가 발생 할 지는 아무도 모른다. 하지만 사용자의 실수는 명확하다.
> 이 때 TreeMap에 있는 유효성 검사 코드가 ClassCastException 이라는 명확한 예외를 던진다.

3. 객체의 임시 복사본에서 작업을 수행한 다음, 작업이 성공적으로 완료되면 원래 객체와 교체한다.
<pre>
<code>
  class Stack<T>{

  ...
  
  public T pop(){
    Stack<T> temp = new Stack<>(elements.length);
    temp = this; // 깊은 복사(Deep Copy)가 구현 되었다고 가정한다.
    
    return temp.pop();
  }

  ...

}
</code>
</pre>
  
> 임시 자료구조(여기서는 temp)를 만들고 다루는 비용이 부담되지 않는다면 고려해볼 만 하다.

4. 작업 도중 발생하는 실패를 가로채는 복구 코드를 작성하여 작업 전상태로 되돌리는 방법
> 자주 쓰이는 방법은 아니다.

실패 원자성을 항상 달성하기 위해 집착은 금물이다.
예를 들어 두 Thread가 동기화 없이 같은 객체에 접근해 Write 중이라면,
ConcurrentModificationException을 잡아 냈다고 해도 그 객체의 일관성이 유지중이라는 보장이 없다. 따라서 그 객체를 여전히 슬 수 있는 상태로 가정하는 것은 위험하다.

또한 실패 원자적인 코드를 작성하는 것은 큰 비용이나 복잡도가 필요 할 수도 있다.
가시성만 떨어뜨리는 강박적인 null 체크가 그 대표적인 예다.

배보다 배꼽이 커지지 않게 주의하자.