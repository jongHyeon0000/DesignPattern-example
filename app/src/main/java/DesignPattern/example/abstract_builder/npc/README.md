# NPC

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

---------------

## **재귀적 타입 한정(recirsove type bound)**

>타입 매개변수가 자신의 타입을 포함하는 상위 모듈에 한정되는 것을 말한다. 

Comparable 인터페이스는 자신과 파라미터로 받은 객체의 대소관계를 비교하는데 사용되는 메소드인 compareTo() 추상 메소드를 가지고 있다. 따라서 Comparable 인터페이스를 구현 했다는 것은  

    "구현체 자신과 파라미터로 받은 객체의 대소관계를 비교 할 수 있다." 

이며 자바에서 제공하는 정렬과 관련된 메서드는 재정의한 compareTo 메서드를 통해 객체를 정렬 한다.

Comparable 인터페이스는 제네릭 타입 인터페이스이다. 즉 Comparable 인터페이스의 실제 타입 매개변수와 같은 타입의 객체를 파라미터로 받아 그 객체와 대소관계 비교를 한다는 의미이다.  

Comparable\<String>을 구현 한 클래스가 있다고 가정하자.  
컴파일 타임 때 Comparable\<T> 는 Comparable\<String> 으로 실체화 되고, compareTo(T o)는 compareTo(String o)로 실체화 된다.  

따라서 ... implements Comparable\<String> 은

    "자기 자신과 String 타입 객체의 대소관계 비교를 할 수 있는 규약의 인터페이스를 구현 했다." 

를 뜻한다.  

그러나 자기 자신과 전혀 다른 타입과는 대소관계 비교는 논리적으로 할 수 없다.  

따라서 Comparable 인터페이스를 구현하는 거의 모든 클래스는 자신과 같은 타입의 원소와만 비교 할 수 있도록 Comparable 인터페이스를 구현 할 것이다.  

방법은 간단하다. Comparable 인터페이스의 정규 타입 매개변수가 구현체 자신과 같은 타입으로 실체화 시키는 것이다.  

```Java
  class simpleClass implements Comparable<SimpleClass>{

  }
```

이를 지키지 않으면 자바 컬렉션이 제공하는 Comparable 타입을 파라미터로 받는 도우미 메서드를 전부 이용하지 못할 것이다.

예제로 살펴보자.

```Java
public static <E extends Comparable> E getMax(List<E> list){
  return list.stream().max((e1, e2) -> e1.compareTo(e2)).orElseThrow();
}
```

list의 원소 중 Comparable 인터페이스 규약에 맞는 메서드(compareTo)를 이용해, 가장 큰 원소를 리턴 해주는 흔한 getMax 메서드이다. 이 메서드는 비검사 경고가 두 개 발생하는데, 제네릭 타입인 Comparable을 로 타입으로 사용 한 것이 첫 번째, getMax 메서드의 아규먼트로 쓸 람다 식의 타입 추론 중 발생 한 것이 두 번째 이다. 이 중 두 번째 비검사 경고를 살펴보자. 

    Type safety: The method compareTo(Object) belongs to the raw type Comparable.  
    References to generic type Comparable<T> should be parameterized

Comparable이 로 타입이라 타입 안정성을 보장 할 수 없다는 내용이다. e1이 E 타입인 것은 알겠으나, e2의 타입은 당연히 추론이 불가능하다. Comparable이 로 타입이기 때문이다.

getMax 메서드의 파라미터 타입은 Comparator<? super T> comparator 이다. Comparator 인터페이스는 같은 타입의 두 객체를 파라미터로 받아, 그 두 객체의 대소관계 비교를 하는 compare 추상 메서드를 가진 함수형 인터페이스다.  

```Java
  @FunctionalInterface
  interface Comparator<T>{
    int compare​(T o1, T o2);
  }
```

자바 컴파일러는 로 타입으로는 정확한 타입 추론이 불가능하므로, 정규 타입 매개변수 T는 Object가 된다. 이는 컴파일 타임 때 정규 타입 매개변수가 실제 타입 매개변수로 실체화 된 것이 아닌, 제네릭 타입 정보가 소각된 것이다.  

```Java
  class simpleClass implements Comparable<SimpleClass>{

  }
```

위 예제에서 Comparable의 실제 타입 매개변수는 SimpleClass지만, getMax 메서드에서 엉뚱하게 로 타입으로 받아 타입 정보가 소각된 것이다.

```Java
public static <E extends Comparable<E>> getMax(List<E> list){
  return list.stream().max((e1, e2) -> e1.compareTo(e2)).orElseThrow();
}
```

방법은 간단하다.  

    "타입 E는 자기 자신과 어떤 E 타입의 객체와 상호 비교가 가능하다" 

를 재귀적 타입 한정으로 구현하면 된다.