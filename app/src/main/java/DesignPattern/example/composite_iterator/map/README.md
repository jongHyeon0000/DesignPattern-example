- **타입 안정성을 컴파일 타임 때 정의 한다**  

일반적인 배열과 대표적인 제네릭 타입 클래스인 List를 비교해보자.  
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