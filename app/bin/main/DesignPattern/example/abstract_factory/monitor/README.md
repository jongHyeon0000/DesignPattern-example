# Monitor

### **용어 정리**
|한글 용어|영문 용어|예시|
|------|---|---|
|매개변수화 타입|parameterized type|List\<String>, Map\<Car.class, String>|
|실제 타입 매개변수|actual type parameter|String, Integer|
|제네릭 타입|generic type|List\<E>, Map<K, V>|
|정규 타입 매개변수|formal type parameter|E, T, V, K|
|비한정적 와일드카드 타입|unbounded wildcard type|List\<?>, Set\<?>|
|로 타입|raw type|List, Map, Set|
|한정적 타입 매개변수|bounded type parameter|\<E extends Number>, \<T extends Iterable>|
|재귀적 타입 한정|recirsove type bound|\<E extends Iterator\<E>>, <T extends Comparable\<T>>|
|한정적 와일드카드 타입|bounded wildcard type|List\<? extends Number>, Iterable<? super Integer>|
|제네릭 메서드|generic method|static \<E> List<E> asList(E[] a), static \<V extends Monitor> Map<Integer, V> getRanking(...)|
|타입 토큰|type token|String.class, Keyboard.class|

---------------------------------

## **제네릭 문법 도입** 
  
좋든 싫든 java 라이브러리가 제공하는 강력한 클래스들을 제대로 사용 하고 싶다면 제네릭 문법에 익숙해져야 한다.  
간단한 예제 메서드로 살펴보자. Stack과 Object 타입 파라미터를 하나 받아 대신 push 해주는 심플한 정적 도우미 메서드다.

```Java
public static void simplePushMethod(Stack stack, Object obj) {
	stack.push(obj);
}
```

Java8 이상 부터는 컴파일은 가능하지만, 비검사 경고가 두 개 발생한다.

			Stack is a raw type. References to generic type Stack<E> should be parameterized	Monitor.java  

			Type safety: The method push(Object) belongs to the raw type Stack. 
			 References to generic type Stack<E> should be parameterized	Monitor.java
Stack은 제네릭 타입이기 때문에, Stack 객체를 인스턴스화 할 때는 제네릭 타입으로 선언하는 것이 바람직 하다.  
우리는 SimplePushMethod의 파라미터로 로 타입 Stack 객체를 받았다. 제네릭 타입을 로 타입으로 사용하면 제네릭 클래스의 객체 내부는 어떻게 됬을까? 
  
로 타입으로 선언된 Stack 객체 내부의 정규 타입 매개변수는 Object 타입이 된다.  
원래라면 push 메소드의 파라미터 타입은 Stack 객체를 선언 할 때 컴파일러에게 알려준 실제 타입 매개변수가 된다.  

**제네릭 클래스의 정규 타입 매개변수는 컴파일 타임 때 실제 타입 매개변수로 "치환" 된다.**

우리는 Stack 객체를 로 타입으로 선언했으므로, 컴파일 타임 때 제네릭 타입 정보가 전부 지워진 것처럼 동작한다.  
결국 비검사 경고는 로 타입이 아닌 매개변수화 타입을 이용해 타입 안정성을 확보 하라는 내용이다.  
  
이제 Java의 세계에선 제네릭 문법이 쓰이지 않는 곳은 없다는 것을 알았으니, 제네릭 문법의 특징과 장점을 하나하나 알아보자.

### **타입 안정성 확보를 문법적으로 강제한다.**

자주 사용하는 자바 컬렉션의 대표적인 자료구조인 Stack 객체로 알아보자.  
Stack 객체는 어떤 타입이든 담을 수 있게 설계되었지만, stampsStack 객체는 Stamp 타입만 담기 위해 이름도 stampStack으로 명명했다.
  
```Java
class Stamp {

}


class Coin {

}

public static void main(String[] args) {
  Stack stampsStack = new Stack();
    
  stampsStack.push(new Stamp());
  stampsStack.push(new Stamp());
  stampsStack.push(new Stamp());
    
  for(Iterator i = stampsStack.iterator(); i.hasNext();) {
    Stamp stamp = i.next();
  }
}
```

해당 코드는 컴파일 조차 되지 않는다. stampsStack이 로 타입 이기 때문에, Stack 내부의 제네릭 타입 정보가 전부 치워져 Object 타입으로 바뀌었기 때문이다.  
따라서 i.next()가 리턴하는 값의 타입도 Object 타입이고, Object 타입을 하위 타입인 Stamp 타입으로 자동 형변환 할 수 없다는 이유이다.  
  
하지만 우리는 Stamp 타입만을 사용하는 Stack 객체임을 알고, 그래서 이름도 stampsStack으로 지었으니, 강제 형변환 하여 사용 해도 타입 안정성이 훼손 되지 않을 것 같다.

```Java
class Stamp {

}


class Coin {

}

public static void main(String[] args) {
  Stack stampsStack = new Stack(); // 이 객체는 Stamp 타입만 담는 스택이니 Stamp 타입만 넣어서 쓸 것!!!!!!!!
    
  stampsStack.push(new Stamp());
  stampsStack.push(new Stamp());
  stampsStack.push(new Coin());
    
  for(Iterator i = stampsStack.iterator(); i.hasNext();) {
    Stamp stamp = (Stamp) i.next();
  }
}
```

이번에는 런타임 도중 ClassCastException이 던져진다. 알고보니 실수로 Coin 객체가 push 되었고, Coin 타입을 Stamp 타입으로 형변환 할 수 없으니 발생한 예외이다.  
  
**오류는 가능한 한 발생 즉시, 가장 이상적인 경우는 컴파일 타임 때 발견되는 것이다.** 런타임에 발생한 문제를 디버깅 하는 과정은 이미 문제가 발생한 코드와 물리적으로 상당히 떨어져 있을 가능성이 높고, 디버깅을 위해 기나긴 스택트레이스 여행을 해야 하는 경우가 많다. stampStack 객체가 "Stamp 타입 만을 사용하는 Stack 객체" 라고 주석을 아무리 달더라도 컴파일러에겐 도움이 되지 못한다.

```Java
class Stamp {

}


class Coin {

}

public static void main(String[] args) {
  Stack<Stamp> stampsStack = new Stack<>();
    
  stampsStack.push(new Stamp());
  stampsStack.push(new Stamp());
  stampsStack.push(new Coin()); //The method push(Stamp) in the type Stack<Stamp> is not applicable for the arguments (Coin)
  
  for(Iterator<Stamp> i = stampsStack.iterator(); i.hasNext();) {
    Stamp stamp = i.next();
  }
}
```

제네릭을 활용하면 "Stamp 타입 만을 사용하는 Stack 객체" 라는 정보가 컴파일 타임 때 타입 선언 자체에 녹아든다. 자바 컴파일러는 정규 타입 매개변수를 컴파일 타임 때 실제 타입 매개변수로 치환한다.  

즉 push 메서드의 메서드 시그니쳐는 push(T item)에서 push(Stamp item)으로 변환된다. 당연히 i.next()의 리턴 타입도 Stamp 타입이 되므로  지긋지긋한 형변환 코드도 필요없다.  

stampsStack 객체에 Coin 타입을 push 하는 실수 또한, 컴파일 타임 때 에러 메시지로 명확하게 알 수 있다.  
C++의 Vector를 사용하다 골머리를 썩어본 적이 있다면, 제네릭이 얼마나 고마운 문법인지 알 수 있다.

### **자바 컴파일러의 타입 추론력은 제네릭 문법에서 나온다.**  

Java8 이후 부턴 함수형 프로그래밍 기법인 람다와 스트림 문법이 도입 되었다.

```Java
public static void SimpleStream() {
  List<String> stringList = List.of("AAA", "BBB", "CCC", "DDD", "EEE", "FFF");
  
  // forEach 종단 연산은 결과를 보고할 때만 사용 하는 것이 바람직 하다.(item 46)
  stringList.stream().map(s -> s.toLowerCase()).forEach(System.out::println);
}
```

    여기서 말하는 스트림(Stream)은 입출력에 사용되는 스트림이 아니다!

꽤나 단순한 일을 하는 스트림 함수인데  

List의 원소(element)를 순회 하며,  
대문자 문자열이 있으면 소문자 문자열로 바꿔주고, map(str -> str.toLowerCase()),  
콘솔 화면에 출력한다. forEach(System.out::println)

스트림의 중간 연산(intermediate operations)을 수행하는 메서드들은 주로 데이터를 가공하거나 필터를 적용해 특정 조건에 맞는 데이터를 걸러 낸다.  
map 메서드는 스트림의 중간 연산 메서드이며, Predicate 함수형 인터페이스(Functional Interface) 규칙에 맞는 람다 식을 입력 받는다.  
그리고 스트림의 원소를 순회하며, 입력한 람다 식에 따라 내용물을 변환한다.  

    스트림 연산에서 사용하는 원소는 전부 상수 취급된다! Stream은 원본 데이터로부터 데이터를 읽기만 할 뿐, 원본 데이터 자체를 변경하지 않는다.  
    함수형 프로그래밍을 지향하는 SQL에서 SELECT 구문이 실제 데이터를 변환하지 않고, 특정 속성(attribute)만 프로젝션(Projection) 하는 것과 같다.

정말 신기한점은 람다 식은 메서드 바디만 존재 할 뿐이고, 파라미터와 리턴값의 타입 까지도 컴파일러에게 알려 주지 않는데, 컴파일러는 s가 String 타입임을 정확히 알고 있다.  
또한 종단 연산(Terminal Operations)인 forEach 메소드의 아규먼트로 System.out::println 메서드 참조를 넘겨 주었다.  
System.out.println() 메서드는 입력 값으로 모든 기본형 타입과 String, Object 타입 까지 받을 수 있도록 오버로딩 되어 있는데, 아무도 알려주지 않았음에도 String 타입 파라미터를 가진 메서드 시그니처의 println() 메서드로 척척 알아 맞춘다. 

이런 자바 컴파일러의 타입 추론 능력은 전부 제네릭 문법에서 온다. 스트림을 생성하는 과정(Stream Source)의 시작은 stringList 객체 인데, stringList 객체의 제네릭 타입은 "List\<String>" 이고 이 제네릭 타입의 실제 타입 매개변수는 String 타입이다. 따라서 stringList 객체로 스트림을 열면 그 스트림의 실제 타입 매개변수 또한 String 타입이 된다.  

스트림의 중간 연산과 종단 연산에서 사용되는 메서드도 제네릭 메서드이고, 함수형 인터페이스 또한 제네릭 타입이니, 컴파일러는 메서드 바디만 존재하는 람다 식으로 스스로 타입을 추론해 일을 수행한다.  

한번 stringList 객체를 로 타입으로 선언해보자. 그리고 얼마나 많은 비검사 경고와 타입 캐스팅 에러가 뜨는지 확인해보자. 제네릭 문법이 없는 람다 식과 스트림은 존재 가치가 없다.

---------------------
## **한정적 와일드카드**

매개변수화 타입은 불공변(invariant)이다. 서로 다른 타입 Type1 와 Type2가 있을 때 List\<Type1> 과 List\<Type2>는 그 누구의 상위 타입도, 하위 타입도 아니다. 따라서 List\<String>은 List\<Object>의 하위 타입이 아니다. 왜 제네릭 문법이 이런지는 조금만 생각해보면 알 수 있다. 상위 모듈이 수행 할 수 있는 
모든 일은 하위 모듈에서도 정상적으로 동작 해야한다.(리스코프 치환 원칙) 그러나 List\<String> 은 문자열을 다루는 객체만을 넣고, 빼고, 세는 일만 할 수 있고, List\<Object> 는 Object 객체를 다루는 일을 한다. List\<String>이 List\<Object>의 책임에 부여된 역할을 정확히 수행 할 수 없으니 리스코프 치환 원칙에 위배 된다. 따라서 불공변인 것이다.  
  
하지만 가끔은 불공변 방식보다 유도리 있게 제네릭을 사용하고 싶다. 싱글톤 패턴에서 사용한 간단한 스택 예제로 알아보자.

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
    if(!isEmpty()) {
      @SuppressWarnings("unchecked") T result = (T) elements[--size];
      elements[size] = null;
      
      return result;
    }
    throw new EmptyStackException();
  }
  
  public boolean isEmpty() {
    return (size <= 0) ? true : false;
  }

  ...

}
```

정렬 할 수 있음을 알리는 인터페이스(Iterator)를 구현 한 모든 원소를, 한번에 스택에 push 해주는 pushAll() 메서드를 추가해 보자.

```Java
class Stack<T>{
  ...

  public void pushAll(Iterator<T> src){
    while(src.hasNext()){
      push(src.next());
    }
  }

  ...

}
```

큰 문제 없는 메서드 같지만, 완벽하진 않다. 

```Java
abstract class People{
  ...
}

class Man extends People implements Iterator<Man>{
  @Override
  public boolean hasNext() {
    ...
  }

  @Override
  public People next() {
    ...
  }
}

public static void main(String[] args) {
  Stack<People> peopleStack = new Stack<>(10);
  List<Man> manlist = new ArrayList<>();
  
  Iterator<Man> iter = manlist.iterator();
  peopleStack.pushAll(iter);
}
```

실제로는 pushAll(iter)를 할 때 에러 메시지가 나온다. peopleStack 객체의 매개변수화 타입은 Stack\<People>로 컴파일 타임 때 이미 정해졌고, 매개변수화 타입은 불공변 이므로, 런타임 도중 다시는 바꿀 수 없다. 따라서 peopleStack 객체의 pushAll(Iterator\<T> src) 메서드도 컴파일 타임 때 pushAll(Iterator\<People> src) 로 이미 "치환" 되었다. 에러 메시지의 내용은 Iterator\<Man> 타입을 Iterator\<People> 타입에 넣을 수 없다는 메시지다.  

상위 클래스인 People 클래스에서 Iterator를 구현하지 않았더라도, 하위 클래스 Man 클래스에서 Iterator를 구현 했다. 그리고 Man은 People의 하위 타입이면서, Iterator 규약에 맞는 메서드를 적절히 구현 했으니,  pushAll() 메서드의 동작에 문제가 없음을 잘 안다.  

하지만 제네릭 문법은 이를 허락하지 않는다. Iterator\<Man> 타입과 Iterator\<People> 타입은 전혀 다른 타입이므로 타입 캐스팅 될 수 없다.

이 문제를 유연하게 해결 할 수 있는 방법이 바로 한정적 와일드카드 타입이라는 특별한 매개변수화 타입이다.

```Java
class Stack<T>{
  ...

  public void pushAll(Iterator<? extends T> src){
    while(src.hasNext()){
      push(src.next());
    }
  }

  ...

}
```

이제 pushAll의 입력 매개변수화 타입을 풀어 설명하면 "T의 Iterator"가 아니라, "T 또는 T의 하위 타입의 Iterator" 가 된다. "T 또는 T의 하위 타입의 Itertor"의 자바 문법적인 표현이 Iterator\<? extends T> 다.  

이제 같은 방법으로 popAll() 메서드도 정의해보자.

```Java
class Stack<T>{
  ...

  public void popAll(Collection<T> dst){
    while(dst.hasNext()){
      push(dst.next());
    }
  }

  ...

}
```

파라미터로 Collection 타입의 dst를 파라미터로 받고, 자신의 원소를 비워나가면서(pop) 그 원소를 입력받은 컬렉션에 add 한다.

```Java
public static void main(String[] args) {
  Stack<Man> manStack = new Stack<>(10);
  Collection<People> peopleList = new ArrayList<>();
  
  manStack.popAll(peopleList);
}
```

Man 클래스는 People 클래스의 하위 클래스 이지만, Collection\<People>와 Collection\<Man>은 다른 타입이므로 역시 타입 캐스팅이 불가능하다. 

```Java
class Stack<T>{
  ...

  public void popAll(Collection<? super T> dst){
    while(dst.hasNext()){
      push(dst.next());
    }
  }

  ...

}
```

이제 popAll() 메서드의 입력 매개변수의 타입이 "T의 Collection"이 아니라 "T의 상위 타입의 Collection"이 된다.  

어떤 상황에서 한정적 와일드카드 문법을 사용하는 것이 바람직할까? 조슈아 블로크가 제안한 공식이다.  

**펙스(PECS) : producer-extends, consumer-super**  

매개변수화 타입 T가 생산자(producer)라면 \<? extends T>를 사용하고, 소비자(consumer)라면 \<? super T>를 사용하라는 뜻이다.  

위 Stack 예제에서 pushAll() 메서드는 Stack 내부의 T 타입 원소를 파라미터로 받은 src 객체로 생산 하므로 생산자에 속한다.  
위 Stack 예제에서 popAll() 메서드는 Stack 내부의 T 타입 원소를 파라미터로 받은 dst 객체로 소비 하므로 소비자에 속한다. 

    단 메서드의 리턴 타입은 한정적 와일드카드가 되어선 안된다. 클라이언트에서 리턴 타입을 받기 위한 객체 또한 한정적 와일드카드 타입으로 받아야 하기 때문이다. 클라이언트는 리턴 타입의 실제 타입 매개변수가 정확히 어떤 타입인지 알 수 없으므로, 클라이언트 측에서 객체를 사용하기 전 타입 캐스팅 코드가 삽입(주로 instanceof) 되는데, 이는 제네릭 문법의 이점을 스스로 버리는 것이다.
 
한정적 와일드카드 문법은 특히 재귀적 타입 한정 문법과 잘 맞는다.

```Java
public static <E extends Comparable<E>> E Max(List<E> list){
  return list.stream().max((e1, e2) -> e1.compareTo(e2)).orElseThrow();
}
```

이 정적 도우미 메서드는 파라미터로 list\<E> 타입의 리스트를 하나 받는다. 이 때 정규 타입 매개변수 E 는 Comparable\<E> 인터페이스를 구현 해야 한다. 즉 \<E extends Comparable\<E>> 는 "E 는 E 자신과 논리적 비교가 가능하다." 라는 뜻이 된다.  

그리고 list의 원소 중 Comparable\<E> 인터페이스 규약에 맞는 메서드(compareTo)를 이용해, 가장 큰 원소를 리턴 해주는 평범한 max 메서드이다.  

이제 이 max 메서드에 한정적 와일드카드 문법을 도입 하여 유연한 메서드로 리팩토링 해보자.  

```Java
public static <E extends Comparable<? super E>> E Max(List<? extends E> list){
  return list.stream().max((e1, e2) -> e1.compareTo(e2)).orElseThrow();
}
```

### **하위 모듈에서 Comparable 인터페이스를 구현 하지 않더라도, 상위 모듈에서 구현 한 Comparable 인터페이스를 사용 할 수 있다.**

>Man 클래스가 Comparable 인터페이스를 구현 하지 않았더라도, People 클래스에서 Comparable을 구현 했다면 max 메서드를 정상적으로 사용 할 수 있다. 상위 클래스에서 작동하는 모든 기능은 하위 클래스에서도 정상적으로 작동 해야 하기 때문이다. (리스코프 치환 원칙)  
  
Comparable<E> 인터페이스를 구현 했다는 것은, "두 E 타입의 객체 논리적 대소비교를 하는 compareTo() 메서드를 인터페이스 규약에 따라 구현한다" 라는 의미다. 즉 E 타입의 객체를 생산 하므로 \<? super E> 가 적절하다.

### **객체지향 프로그래밍의 유연함을 경직된 제네릭 문법에서도 느낄 수 있다.**

>"남성은 사람이다(Man is a People)" 라는 말을 들으면 어색하다고 느끼는 사람은 없다. 따라서 People 클래스와 Man 클래스의 상속 관계는 거의 적절하다. 따라서 우리는 "People man = new Man()" 과 같이 객체의 클래스 타입을 추상화된 상위 클래스로 선언 하여 사용한다. 문제는 실제 타입 매개변수로 Man, People 클래스를 명시 해도 , 매개변수화 타입은 상위 타입과 하위 타입의 계층적 구조가 존재하지 않는다.  
(List\<People> 은 List\<Man>의 상위 타입이 아니다, 불공변)  
>
max 메서드의 역할은 파라미터로 받은 list의 E 타입 원소 중 최대 값을 compareTo 메서드를 이용 해 도출한다. 즉 E 타입의 원소를 소비 하므로 \<? extends E>가 적절하다.

--------------------------------------

## **타입 안전 이종 컨테이너 패턴(type safe heterogeneous container pattern)**

제네릭이랑 궁합이 잘 맞는 객체는 값 객체이고, 그 이유로 자바의 컬렉션은 대부분 제네릭 타입 클래스로 작성 되었다.  

보통 우리가 자바 컬렉션을 사용할 때 제네릭 타입의 실제 타입 매개변수로 컨테이너 자신을 사용한다.  
List\<Double>, Map\<Integer, String> 처럼 말이다. 그 중에서도 Map의 키(key) 타입으로 Integer, String을 주로 사용하지만, 더 유연하고 멋진 수단을 소개하고자 한다.  

RDB(Relational Database)로 예를 들어보자, 어떤 한 릴레이션(relation, SQL의 table)은 n 개의 튜플(tuple)의 집합이고, 튜플은 n개의 속성(attribute)을 가진 집합이다. 그리고 하나의 속성이 취할 수 있는 허가된 값의 집합을 도메인(domain) 이라 한다. 그림으로 쉽게 알아보자.

![ex_screenshot](../../../../../resources/abstract_factory/2170804358C9268B2F.png)  

      출처 : https://pjh3749.tistory.com/153

자바와는 사뭇 동떨어진 관계형 모델이지만, 이를 유연하게 표현하는 방법이 있다. Map의 키 타입으로 컨테이너가 아닌 정보 그 자체를 키 타입으로 사용하고, 값(value)을 넣었다 뺄때, 매개변수화 된 키 타입의 정보를 제공 하면 멋질 것 같다.

Map\<Domain, Attribute>, Map\<Domain, Map<A>>