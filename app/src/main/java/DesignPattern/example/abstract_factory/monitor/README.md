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

## 제네릭 문법 도입

모든 메소드를 제네릭 메소드로 만드는 것은 오히려 코드를 경직되게 만들 수 있다.  
하지만 다양한 파라미터 타입을 받아야 하는 정적 유틸리티 클래스 라면 제네릭 문법을 적용해 제네릭 메소드로 만드는 것이 훨신 유연한 메소드가 된다.  
  
좋든 싫든 우리는 java 라이브러리가 제공하는 강력한 클래스들을 사용 하고 싶다면 제네릭 문법에 익숙해져야 한다.  
간단한 예제 메서드로 살펴보자. Stack과 Object 타입 파라미터를 하나 받아 대신 push 해주는 심플한 정적 도우미 메서드다.

```
public static void simplePushMethod(Stack stack, Object obj) {
	stack.push(obj);
}
```

Java8 이상 부터는 컴파일은 가능하지만, 비검사 경고가 두 개 발생한다.

			Stack is a raw type. References to generic type Stack<E> should be parameterized	Monitor.java  

			Type safety: The method push(Object) belongs to the raw type Stack. 
			 References to generic type Stack<E> should be parameterized	Monitor.java
Stack은 제네릭 클래스이기 때문에, Stack 객체를 인스턴스화 할 때는 제네릭 타입으로 사용하는 것이 바람직 하다.  
하지만 우리는 Stack을 로 타입으로 선언했다. 
  
로 타입으로 선언된 Stack 객체 내부의 제네릭 메소드 또한 로 타입 메소드가 된다.  
원래라면 push 메소드의 파라미터 타입은 Stack 객체를 선언 할 때 정의 한 제네릭 타입과 같은 정규 타입 매개변수(parameter) 타입이지만,  
우리는 Stack 객체를 로 타입으로 선언했으므로, push는 더 이상 T 타입이 아닌 Object 타입을 파라미터로 받는 구 시대 Stack을 사용하고 있다.
  
제네릭의 특징과 장점을 하나하나 알아보자.

- **타입 안정성을 컴파일 타임 때 정의 한다**  

배열과 대표적인 제네릭 타입 클래스인 List를 비교해보자.  
배열(array)은 공변(covariant)이다. 배열과 다르게 제네릭은 불공변(invariant)이다.

```
public static void simplePushMethod() {
	Object[] array = {10, 20}; // OK
	List<Object> list = new ArrayList<String>(); // Type mismatch: cannot convert from ArrayList<String> to List<Object>
}
```
배열은 실체화(reify)된다. 런타임에도 자신이 담기로 한 타입을 인지하고 확인한다.
  
array는 Object 타입을 받을 수 있는 배열이다. {10, 20} 은 Integer 타입으로 오토박싱(auto boxing) 되고,  
Integer는 Object의 하위 타입 이다. 배열은 런

배열이 유연해 보인다면, 이제 다음 예제를 살펴보자.

```
public static void simplePushMethod() {
	Object[] objectArray = new Long[20];
	objectArray[0] = "I'm String Type";
}
```

예상과 달리 배열에 원소(element)를 넣으려 할 때, ArrayStoreException이 발생한다.  
당연하게도 Long 배열에 String 타입을 넣으려 했기 때문이다.  
    
object 클래스는 모든 클래스의 상위 타입 이기 때문에, 어떤 타입이든 받을 수 있다.  
우리는 객체지향 프로그래밍의 유연함을 얻었지만, 타입 안정성은 잃었다.  
언제 어디서 ClassCastException이 나올지 모르는 위험한 코드다.




      정적 유틸리티 메서드는 주로 정적 도우미 클래스에 담겨져 있는 경우가 많다. Collections 클래스의 정렬(sort), 탐색(search) 메서드가 대표적이다.  
      다음 예제는 스택과 Object 타입의 파라미터 하나를 받아 스택에 push 해주는 간단한 정적 도우미 메서드다.