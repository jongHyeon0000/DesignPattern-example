# 6가지 종류의 싱글톤 패턴(Singleton Pattern)과 안전성이 검증된 두 싱글톤 패턴(Lazy Holder, Enum Singleton)

## **불변식(Invariant)** 
> 어떤 객체가 정상적으로 작동하기 위해 절때 허무러지지 않아야 하는 값, 식, 상태 등을 뜻한다.

Car라는 객체에는 현재 보유한 연료의 양과 최대로 넣을 수 있는 연료의 양이 있다.

```Java
  class Car{
    private static final int MAX_OIL_VALUE = 1000;
    private int oilValue;

    Car(int oilValue){
      this.oilValue = oilValue;
    }
  }
```

이때 oilValue의 값이 0 미만이 되거나, MAX_OIL_VALUE를 넘어서는 경우는
정상적인 자동차 객체의 상태(State)라고 할 수 없다.  
이런 경우를 **Car 객체(Object)의 불변식(Invariant)이 깨졌다.** 라고 부를 수 있다.  
  
Car 객체의 불변식을 유지하기 위해서는 oilValue의 값이 초기화, 변경 될 때 방어적인 값 체크가 필요하다.

```Java
  class Car{
    private static final int MAX_OIL_VALUE = 1000;
    private int oilValue;

    Car(int oilValue){
      // 불변식(Invariant)를 유지하기 위한 방어적 아규먼트 체크
      if(oilValue < 0 && oilValue > MAX_OIL_VALUE){ 
        // 불변 클래스(Immutable Class)가 아니라면 예외를 던질 때 
        // 실패 원자성(Failure Atomicity)을 고려해야 한다.
        throw new IllegalArgumentException();
      }
      this.oilValue = oilValue;
    }
  }
```

------------

## **불변 클래스(Immutable Class)**
> 객체 내부의 값을 처음 객체가 생성 될 때 외에는 수정, 변경할 수 없는 클래스를 말한다.

  **불변 객체는 단순하다.**
>  생성된 시점부터 GC에 의해 파괴될 때 까지 값이나 상태가 변하지 않는다.  
   따라서 생성자의 아규먼트 값이 올바른 유효 범위인지 체크하는 경우를 제외하고, 번거로운 불변식 체크가 필요없다.
> 
  **불변 객체는 스레드 안전(Thread-safe)하다.**
> 최초 생성 이후로 값이나 상태를 변경할 수 없으니 당연한 결과이다.  
> 조슈아 블리크는 이 때문에 StringBuffer 클래스를 직접 사용하는 것을 '구닥다리' 라고 했다. String 클래스는 애초에 불변이기 때문이다.
> 자세한 내용은 구글링 

  **불변 객체는 안심하고 공유가 가능하다.**
> 불변 객체를 상속해 구체 클래스(Concrete Class)를 만들 수 없기 때문이다.  
> 따라서 상속을 고려한 API 문서 작성 부담 또한 줄어든다.

  **객체 재사용성이 높다.**
> 예컨대 자주 사용하는 상수 풀(public static final float PI = 3.14 등..)을 모아두거나,
> 다른 클래스의 구현을 도와주는 도우미 역할의 클래스로 적합하다. 

  **불변 객체임이 보장된다면, 불변 객체끼리의 내부 데이터 공유는 안전하다.**
> 시간이 나면 자주 사용하는 BigInteger, String 객체의 내부 구현을 구경해보자.

  **불변 객체는 그 자체로 실패 원자성을 제공한다.**
> 불변 객체의 불변식은 항상 보장되므로, 언제 어디서 예외를 던져도 객체의 내부 상태는 불일치 상태가 되지 않는다.

  **내부 값이 조금이라도 다르면 반드시 독립된 객체로 만들어야 한다.**
> 따라서 불변 객체의 무분별한 사용은 큰 비용이 따른다.
>
> 이를 해결하기 위해 불변 클래스에 비해 클라이언트가 다루기 어렵고, 위험하며, 복합한 로직으로 구현된 가변 클래스를
> 여러개 구현하고, 하나의 불변 클래스 내부에 이 가변 클래스들이 실질적인 일을 하는 설계를 사용하는 경우가 많다.
> **(가변 동반 클래스, Companion Class)**
>
> 이런 불변 클래스의 내부 구현은 수많은 가변 클래스로 이루어져 있다.
> 클라이언트는 복잡한 내부는 모른채 다양한 기능이 모인 불변 클래스를 쉽고 안전하게 다루거나,
> 성능을 위해 직접 가변 클래스를 쓸 수도 있다.
>
> 대표적인 예가 불변 클래스 String과 가변 동반 클래스 StringBuilder이다.

### **안전성을 검증받은 불변 클래스를 설계하는 법은 다음과 같다.**
```Java
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
```

- **객체의 상태를 변경하는 메서드(변경자, setter)를 제공하지 않는다.**

```Java
  class People{
    int age;
    String name;

    People(int age, String name){
      this.age = age;
      this.name = name;
    }
  }
```

- **클래스를 확장(extend)할 수 없도록 한다. 즉 상속을 문법적으로 금지한다.**

```Java
  // 이 클래스는 절대 재정의 될 수 없음을 명확하게 전달한다.
  final class People{
    int age;
    String name;

    People(int age, String name){
      this.age = age;
      this.name = name;
    }
  }
```

- **모든 필드를 final로 선언한다.**

```Java
  final class People{
    // 이 클래스의 필드는 절대 변경되지 않음을 명확하게 전달한다.
    final int age;
    final String name;

    People(int age, String name){
      this.age = age;
      this.name = name;
    }
  }
```

- **모든 필드의 접근 제어자(Access Modifier)를 private으로 선언한다.**

```Java
  final class People{
    // 이 클래스의 필드는 외부에서 접근할 수 없게 문법적으로 안전하게 보호받고 있다.
    // People 클래스는 다음 릴리즈 때 People 클래스를 사용하던 외부 클라이언트의 호환성을 고려하여
    // 내부 구현을 수정하는 것에 부담이 줄어든다.
    // (SOLID, Open Closed Principle), (GRASP, Low coupling), 정보 은닉(information hiding)
    private final int age;
    private final String name;

    People(int age, String name){
      this.age = age;
      this.name = name;
    }
  }
```

- **자신 외에는 내부의 가변 컴포넌트에 접근할 수 없도록 한다.**  
  

```Java
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
```
- **불변 필드라 할 지라도, 적시에 방어적 복사본을 만든다.**

```Java
final class DiscountEventPeriod {
  private final Date start;
  private final Date end;

  public DiscountEventPeriod(Date start, Date end) {
    if (start.compareTo(end) > 0) {
      throw new IllegalArgumentException(
        "종료시간(" + end + ") 가 시작시간(" + start + ")보다 빠를 수 없습니다.");
    }

    this.start = start;
    this.end = end;
  }

  public Date getStart() {
    return start;
  }

  public Date getEnd() {
    return end;
  }
}
```
사실 Date 클래스는 자신의 가변 필드를 노출하는 위험성이 내포된 클래스이고, Java 8 이후 LocalDateTime 클래스와 ZonedDateTime 클래스에게 자리를 넘겨주었다.  

하지만 아직 Date 객체를 이용하는 프로그램은 많을 것이다. Date 객체(start, end)는 위에서 설명한 규약을 모두 지킨 불변 필드이고, 불변 필드만 보유한 DiscountEventPeriod 클래스는 불변 클래스임이 분명하다. 

이제 start와 end의 불변식을 망가뜨려보자.  

```Java
public static void main(String args[]) {
  Date start = new Date();
  Date end = new Date();
  
  DiscountEventPeriod discountEventPeriod = new DiscountEventPeriod(start, end);
  start.setYear(122); // 2022년
  end.setYear(112); // 2012년
}
```
해당 코드를 삽입하면 setYear() 메서드에 줄이 그어져 있고, 자바 컴파일러의 경고(warning) 메시지가 뜬다. 더 이상 사용하지 않는 메서드라는 사실을 알리기 위해 @Deprecated 에너테이션이 붙어있기 때문이다.  

Date 클래스는 오버라이딩 된 메서드나, 정적(static) 메서드를 제외하면 대부분이 @Deprecated 에너테이션이 붙어 있다. 그럼에도 예전에 작성된 낡은 Date 객체를 새로운 날짜 객체로 대체하는 것이 쉽지 않을 수 있다.  

악의적인 외부 공격이나, 클라이언트의 실수를 막기 위해선 생성자에서 받은 가변 파라미터를 방어적 복사(defensive copy)해 자신의 불변 필드를 보호해야 한다.

```Java
final class DiscountEventPeriod {
  private final Date start;
  private final Date end;

  public DiscountEventPeriod(Date start, Date end) {
    this.start = new Date(start.getTime());
    this.end = new Date(end.getTime());
    
    if (start.compareTo(end) > 0) {
      throw new IllegalArgumentException(
        "종료시간(" + end + ") 가 시작시간(" + start + ")보다 빠를 수 없습니다.");
    }
  }

  ...
}
```

실패 원자성을 설명 할 때 유효성을 검사하는 코드를 최상위에 삽입해야 한다고 했지만, 방어적 복사 코드는 반드시 파라미터 유효성 검사 코드보다 상위에 삽입 되어야 한다.

"종료 연도는 시작 연도보다 빠를 수 없다" 가 DiscountEventPeriod 객체의 불변식이다. 1번 쓰레드가 DiscountEventPeriod 객체의 생성자의 유효성 검사를 통과하고 방어적 복사를 하려던 찰나의 순간, 원본 객체를 다루는 2번 쓰레드가 갑자기 시작 연도를 100년이나 늘려버린다면 우리의 불변식 검사 코드는 쓸모 없어진다.  

요약하자면 한 쓰레드가 원본 객체의 유효성을 검사한 후 복사본을 만드는 찰나의 순간에 다른 쓰레드가 원본 객체를 수정 해 버린 것이다. 이런 공격을 검사시점/사용시점(TOCTOU, time-of-check) 공격 이라 한다.

Date 클래스는 Cloneable 인터페이스를 구현 하였으므로, clone() 메서드를 사용 할 수 있지만 사용하지 않았다. Date 클래스는 파생 클래스(하위 클래스)를 만들 수 있는데, final 모디피어가 붙지 않았기 때문이다.
즉 Date의 clone() 메서드가 아닌 악의적인 하위 클래스의 clone() 메서드가 엉뚱한 인스턴스를 반환할 수도 있다. 만약 이 악의적인 하위 클래스가 Date 객체의 가변 필드 참조를 따로 인스턴스화 해 보관 중이라면 이 인스턴스에 접근해 Date 객체를 이용하는 모든 객체의 불변식을 전부 망가뜨릴 수 있다.

아직 보안 구멍은 더 남아있다.

```Java
public static void main(String args[]) {
  Date start = new Date();
  Date end = new Date();
  
  DiscountEventPeriod discountEventPeriod = new DiscountEventPeriod(start, end);
  discountEventPeriod.getStart().setYear(122); // 2022년
  discountEventPeriod.getEnd().setYear(112); // 2012년
}
```

답은 간단하다. 불변 객체의 가변 필드 참조를 반환하는 게터(getter) 메서드가 있다면 역시 방어적 복사를 적용한다.

```Java
final class DiscountEventPeriod {
  ...

  public Date getStart() {
    return new Date(start.getTime());
  }

  public Date getEnd() {
    return new Date(end.getTime());
  }

  ...
}
```

드디어 DiscountEventPeriod 클래스는 흠잡을 곳 없는 완벽한 불변 클래스가 되었다.  
<span style="color: #808080"> 
  (네이티브 메소드나 리플렉션 같이 언어 외적인 수단은 예외로 한다.) 
</span> 

DiscountEventPeriod 클래스의 필드는 가장 높은 단계의 **캡슐화(Encapsulation)** 되었다.
게터 메서드에서는 방어적 복사에 clone을 사용해도 된다. 우리가 생성자에서 new Date()로 필드를 초기화 했으므로, start 객체와 end 객체는 Date 클래스임이 확실하기 때문이다.

방어적 복사는 새로운 객체를 계속 생성하므로 성능 저하가 따르고, 같은 패키지에 속한다면 항상 쓸 수 있는 기법은 아니다. 성능 이슈로 방어적 복사를 포기하려면 다음 규약을 참고하자

**호출자(클라이언트)를 신뢰 할 수 있다면 생략 한다.**
> 대신 클라이언트의 조작으로 불변 클래스의 불변식이 깨질 수 있음을 명확히 문서화 해야 한다.

**호출자(클라이언트)에게 객체의 통제권을 명백히 이전 받음을 약속받는다**
> 아규먼트에 삽입된 객체는 더 이상 수정하는 일이 없고, 수정 시 불변 클래스의 불변식이 깨질 수 있음을 명확히 문서화 한다. 하지만 악의적인 객체를 넘기는 사람은 약속을 지킬 마음이 없을 것이다. 이 경우 불변식이 훼손되어도, 그 영향이 호출자에게만 국한되도록 해야 한다. 이 때 래퍼 클래스(Wrapper Class)가 좋은 해답이 될 수 있다.

**불변 필드의 필드, 상태 등을 특정 값으로 매핑 한다.**
> Date 객체에 getTime() 메소드는 타임 스탬프(Time Stamp)를 long 정수로 반환한다.  
> <span style="color: #808080"> 
  (타임 스탬프 : 1970 년 1 월 1 일부터 UTC로 시작하는 초 수의 시간 표현 중 하나.) 
</span> 

```Java
final class DiscountEventPeriod {
  ...

  public long getStart() {
    return start.getTime();
  }

  public long getEnd() {
    return end.getTime();
  }

  ...
}
```

타임 스탬프 값은 어떤 Date 객체에서도 쓸 수 있으니 적절한 해답이 될 수 있다.

------------

## **실패 원자성(Failure Atomicity)**
> 호출된 메서드가 실패 하더라도 해당 객체는 메서드 호출 전 상태를 유지해야 한다. **(실패 원자적, Failure-Atomic)**  
> 실패 원자적인 메서드나 객체는 예외(Exception)가 발생하더라도, 정상적으로 사용할 수 있는 상태를 유지한다.

간단한 Stack 예제를 실패 원자적으로 만들어 보자 

```Java
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
    
    // 내부 배열이 가득 차면 현재 배열의 크기를 2배로 늘린다.
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
 ```

  ### **불변 클래스는 태생적으로 실패 원자성을 보장한다.**

> 불변 객체는 복구하기 힘든 예외(주로 RuntimeException)가 발생 하여 원본 객체랑 같은 새로운 객체를 생성하는 방법을 사용하긴 힘들다.  
> 
> 그러나 원본 객체가 불안정하고 불일치적인 상태에 빠지는 경우는 없다.

### **메서드의 파라미터가 존재하거나 실패할 가능성이 있는(예외가 throw 될 수 있는) 코드, 객체 내부의 상태를 변경하려는 경우, 유효성을 검사하는 코드를 최상위에 삽입한다.**
```Java
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
```

흔한 배열 경계 체크같아 보이지만, 이점은 꽤 크다.

> 만약 size가 0 이라면 elements[--size] 는 elements[-1] 이 되므로, 배열의 인덱스 접근 과정에서 당연히 예외가 발생한다.  
  문제는 호출한 쪽에서 이 예외를 잘 처리했다고 한들 size의 값은 이미 -1 이 되었다는 것이다.  
  결국 불변식이 깨진 망가진 클래스가 되어버린 것이다.  
  만약 클래스의 생성자에서 이런 일이 발생한다면, 그 객체는 시한폭탄이나 다름없다.

> 배열을 다룰 때 발생 할 수 있는 잠재적 예외를 클래스의 용도에 맞는 명확한 추상화 수준의 예외로 바꾸어 준다.  
> 호출한 쪽에서 위와 같은 상황일 때 ArrayIndexOutOfBoundsException 과 EmptyStackException 중 무엇이 더 직관적인가?

> 예컨데 자바의 TreeMap의 원소들은 항상 정렬된 상태를 유지하는데, TreeMap이 비교할 수 없는 엉뚱한 원소가 들어오려 하면  
> 해당 원소를 TreeMap에 넣는 중 어떤 예외가 발생 할 지는 아무도 모른다.   
> 하지만 사용자의 실수는 명확하다.  
> 이 때 TreeMap에 있는 유효성 검사 코드가 ClassCastException 이라는 명확한 예외를 던진다.

### **객체의 임시 복사본에서 작업을 수행한 다음, 작업이 성공적으로 완료되면 원래 객체와 교체한다.**
```Java
  class Stack<T>{

  ...
  
  public T pop(){
    Stack<'T'> temp = new Stack<>(elements.length);
    temp = this; // 깊은 복사(Deep Copy)가 구현 되었다고 가정한다.
    
    return temp.pop();
  }

  ...

}
```
  
> 임시 자료구조(여기서는 temp)를 만들고 다루는 비용이 부담되지 않는다면 고려해볼 만 하다.

### **작업 도중 발생하는 실패를 가로채는 복구 코드를 작성하여 작업 전 상태로 되돌리는 방법**
> 자주 쓰이는 방법은 아니다.

    실패 원자성을 항상 달성하기 위해 집착은 금물이다. 예를 들어 두 Thread가 동기화 없이 같은 객체에 접근해 Write 중이라면,  
    ConcurrentModificationException을 잡아 냈다고 해도 그 객체의 일관성이 유지중이라는 보장이 없다.  
    따라서 그 객체를 여전히 쓸 수 있는 상태로 가정하는 것은 위험하다.

    또한 실패 원자적인 코드를 작성하는 것은 큰 비용이나 복잡도가 필요 할 수도 있다.  
    자체적인 예외 API를 만들거나, 가시성만 떨어뜨리는 강박적인 null 체크가 그 대표적인 예다.