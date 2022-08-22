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

stampsStack 객체에 Coin 타입을 push 하는 실수 또한, 컴파일 타임 때 명확하게 알 수 있다.  
C++의 Vector를 사용하다 골머리를 썩어본 적이 있다면, 제네릭이 얼마나 강력한 고마운 문법인지 알 수 있다.

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

스트림의 중간 연산 메서드(intermediate operations)들은 주로 데이터를 가공하거나 필터를 적용해 특정 조건에 맞는 데이터를 걸러 낸다.  
map 메서드는 스트림의 중간 연산 메서드이며, Predicate 함수형 인터페이스(Functional Interface) 규칙에 맞는 람다 식을 입력 받는다.  
그리고 스트림의 원소를 순회하며, 입력한 람다 식에 따라 내용물을 변환한다.  

    스트림 연산에서 사용하는 원소는 전부 상수 취급된다! Stream은 원본 데이터로부터 데이터를 읽기만 할 뿐, 원본 데이터 자체를 변경하지 않는다.  
     SQL에서 SELECT 구문이 실제 내용물을 변환하지 않고, 특정 속성(attribute)만 조회하는 것과 같다.

정말 신기한점은 람다 식은 메서드 바디만 존재 할 뿐이고, 파라미터와 리턴값의 타입 까지도 컴파일러에게 알려 주지 않는데, 컴파일러는 s가 String 타입임을 정확히 알고 있다.  
또한 종단 연산(Terminal Operations)인 forEach 메소드의 아규먼트로 System.out::println 메서드 참조를 넘겨 주었다.  
System.out.println() 메서드는 입력 값으로 모든 기본형 타입과 String, Object 타입 까지 받을 수 있도록 오버로딩 되어 있는데, 아무도 알려주지 않았음에도 String 타입 파라미터를 가진 메서드 시그니처의 println() 메서드로 척척 알아 맞춘다. 

이런 자바 컴파일러의 타입 추론 능력은 전부 제네릭 문법에서 온다. 스트림을 생성하는 과정(Stream Source)의 시작은 stringList 객체 인데, stringList 객체는 제네릭 타입(List\<String>)이다. 따라서 stringList 객체로 스트림을 열면 그 스트림 또한 제네릭 타입(Stream\<String>)이 된다.    
스트림의 중간 연산과 종단 연산에서 사용되는 메서드도 제네릭 메서드이고, 함수형 인터페이스 또한 제네릭 타입이니, 컴파일러는 메서드 바디만 존재하는 람다 식으로 스스로 타입을 추론해 일을 수행한다.  

한번 stringList 객체를 로 타입으로 선언해보자. 그리고 얼마나 많은 비검사 경고와 타입 캐스팅 에러가 뜨는지 확인해보자. 제네릭 문법이 없는 람다 식과 스트림은 존재 가치가 없다.

---------------------
## **한정적 와일드카드**

매개변수화 타입은 불공변(invariant)이다. 서로 다른 타입 Type1 와 Type2가 있을 때 List\<Type1> 과 List\<Type2>는 그 누구의 상위 타입도, 하위 타입도 아니다. 따라서 List\<String>은 List\<Object>의 하위 타입이 아니다. 왜 제네릭 문법이 이런지는 조금만 생각해보면 알 수 있다. 상위 모듈이 수행 할 수 있는 
모든 일은 하위 모듈에서도 정상적으로 동작 해야한다.(리스코프 치환 원칙) 그러나 List\<String> 은 문자열을 다루는 객체만을 넣고, 빼고, 세는 일만 할 수 있고, Object 객체를 다루는 일은 하지 못한다.  
  
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
    @SuppressWarnings("unchecked") T result = (T) elements[--size];
    elements[size] = null;
    
    return result;
  }

  ...

}
```

정렬 할 수 있음을 알리는 인터페이스(Iterable)를 구현 한 모든 원소를, 한번에 스택에 push 해주는 pushAll() 메서드를 추가해 보자.

```Java
class Stack<T>{
  ...

  public void pushAll(Iterator<T> src){
    for (T e : src){
      push(e);
    }
  }

  ...

}
```

큰 문제 없는 메서드 같지만, 완벽하진 않다. 

```Java
class People implements Iterator<People>{

  @Override
  public boolean hasNext() {
    ...
  }

  @Override
  public People next() {
    ...
  }
}

class Man extends People{

}

public static void main(String[] args) {
  Stack<People> peopleStack = new Stack<>(10);
  List<Man> manlist = new ArrayList<>();
  
  Iterator<Man> iter = manlist.iterator();
  peopleStack.pushAll(iter);
}
```

실제로는 pushAll(iter)를 할 때 에러 메시지가 나온다. peopleStack 객체의 매개변수화 타입은 Stack\<People>로 컴파일 타임 때 이미 정해졌고, 매개변수화 타입은 불공변 이므로, 런타임 도중 다시는 바꿀 수 없다. 따라서 peopleStack 객체의 pushAll(Iterator\<T> src) 메서드도 컴파일 타임 때 pushAll(Iterator\<People> src) 로 이미 "치환" 되었다. 에러 메시지의 내용은 Iterator\<Man> 타입을 Iterator\<People> 타입에 넣을 수 없다는 메시지다.  

People 클래스는 Man 클래스의 상위 클래스이고, 둘의 상속 관계가 적절(is-a, 리스코프 치환 원칙 준수) 하다면, 당연히 상위 클래스인 People 클래스의 원소 순회 방식(Iterator)이 하위 클래스인 Man 클래스에서도 동작 해야 하므로, 별 문제 없어 보이지만 제네릭 문법은 엄격하다.  

이 문제를 유연하게 해결 할 수 있는 방법이 바로 한정적 와일드카드 타입이라는 특별한 매개변수화 타입이다.

--------------------------------------

## **타입 안전 이종 컨테이너**