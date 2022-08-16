# Keyboard

## 객체 식별성(object identity)
> 두 객체(object)가 물리적으로 같은지를 비교하는 것이다.  
> Object에서 구현된 equel은 두 객체의 참조값을 비교해 참조값이 다르면 두 객체는 다르다고 판단한다.

간단한 예제로 이해해보자.

```
public static void objectIdentity() {
  	String str1 = "AAA";
  	String str2 = "BBB";
  	
  	return str1.equals(str2); // false
  }

public static void main(String args[]){
    if(objectIdentity()) {
  		System.out.println("True");
  	}
  	else {
  		System.out.println("False");
  	}
}
```

str1에는 AAA라는 문자열이 할당 된 어떤 한 heap area의 주소를 가지고 있다.  
str2에는 BBB라는 문자열이 할당 된 어떤 한 heap area의 주소를 가지고 있다.  
(C와 다르게 Java에서는 개발자가 이 주소값을 직접 알거나 접근 할 수 없다.)  
즉 str1과 str2는 객체 식별성(object identity)으로 동치성을 비교 했을 때 다른 객체이다.  
str1과 str2는 서로 다른 물리적 공간(여기서는 heap area)의 서로 다른 주소를 가리키고 있기 때문이다.

**따라서 Object의 equel 메소드를 따로 오버라이딩(Overriding) 하지 않았다면 str1.equals(str2) 와 str1 == str2가 같은 일을 수행하는 것이다.**

----------------------

## 논리적 동치성(logical equality)
> 두 객체가 물리적으로 같은 지가 아닌 객체 내부의 값이 같은지로 동치성을 결정 하는 것.

값 클래스(Integer, String...)를 사용하는 개발자라면 두 값 객체를 equals로 비교 할 때,  
객체가 같은지로 동치성을 파악 하는 것이 아니라, 같은 값을 가지고 있는 가로 동치성을 파악 하고 싶을 것이다.  

이럴때 Object 클래스의 equals 메소드를 오버라이딩 하여 객체가 논리적 동치성으로 같은 객체인지를 판단하도록 하면 된다.

그러나 아래와 같은 상황 중 하나라도 해당된다면 equals 메소드를 재정의 하지 않는 것이 최선이다.
  
**각 인스턴스가 본질적으로 고유하다**
> 객체의 역할이 값을 표현하는 것이 아니라 동작하는 개체를 표현하는 클래스인 경우이다.  
> Thread 클래스가 좋은 예이다.  
> 값이 같은 인스턴스가 둘 이상 만들어지지 않음을 보장한다면 역시 해당된다.  
> 어차피 논리적으로 같은 인스턴스가 2개 이상 만들어지지 않으니, 객체 식별성과 논리적 동치성이 같은 의미이기 때문이다.   
> 싱글톤 패턴(Singleton Pattern)을 구현한 객체, Enum 객체(static final)가 대표적인 예다.

**인스턴스의 논리적 동치성을 검사할 일이 없다.**
> java.util.regex.Pattern은 정규표현식을 다루는 클래스이다.(%d %s)  
> 이 클래스의 equals을 재정의해서 두 Pattern 인스턴스가 같은 정규표현식을 나타내는지를 검사하는 방법도 있다.  
> 하지만 Pattern 객체를 사용하다보면 이런 방식의 비교를 원하지 않거나, 동치성 비교 자체가 거의 없는 경우가 많을 것이다.  
> 만약 그렇게 느껴진다면 굳이 equels을 재정의 하는 수고를 할 필요는 없다.

**상의 클래스에서 재정의한 equals가 하위 클래스에도 딱 들어맞는다.**
> 대부분의 Set 구현체(HashSet, TreeSet ...)는 AbstractSet이 구현한 equals를 상속받아 잘 사용하고 있다.  
> 애초에 Set 방식의 자료구조를 구현하다 보면, 동치성 비교는 어떤 Set 자료구조여도 같아야 하기 때문에 동치성 비교라는 로직을  
> 추상 클래스(AbstractSet)에 일반화(generalization)한 좋은 설계인 것이다.  
> 이는 List와 Map도 마찬가지이다. 개인적인 생각이지만 궁금하면 직접 뜯어보고 놀아보는게 제일 이해가 쉽다.

**클래스가 private이거나 package-private이고 equals 메서드를 호출할 일이 없다.**
> 애초에 equals 메서드를 쓸 일이 없는 경우이다.  
> equals 메서드가 실수로라도 호출되는 걸 어떻게든 막고 싶다면, equals 메서드를 오버라이딩 해서 호출 시 대처 불가능한 에러를 던질 수 도 있다.  
> 강박적으로 보일 수 있지만, 클라이언트가 equals 메서드를 사용하지 말 것을 강하게 전달받는 느낌이 핵심이다.

```
@Override 
@Deprecated
public boolean equals(Object o){
    throw new AssertionError(); 
}
```

이제 equals 메서드를 재정의 할 때 꼭 지켜야 하는 일반 규약을 알아보자.  
아래 내용은 Object 클래스의 실제 명세에 적인 규약이다.

- **반사성(reflexivity) : null이 아닌 모든 참조 값 x에 대해, x.equals(x)는 true다.**

- **대치성(symmetry) : null이 아닌 모든 참조 값 x,y에 대해, x.equals(y)가 true면 y.equals(x)도 true다.**

- **추이성(transitivity) : null이 아닌 모든 참조 값 x, y, z에 대해, x.equals(y)가 true고 y.equals(z)도 true면 x.equals(z)도 true다.**

- **일관성(consistency) : null이 아닌 모든 참조 값 x,y에 대해 x.equals(y)를 반복해서 호출하면 항상 true를 반환하거나 항상 false를 반환한다.**

- **Non-Null : null이 아닌 모든 참조 값 x에 대해, x.equals(null)은 항상 false 이다.**

예제 클래스를 만들어 설명하려 했으나 양이 너무 방대해서 시간 관계상 우선 패스 하겠습니다.  
이펙티브 자바 item 10을 보면 아주 자세한 설명이 있고, 책을 구입하지 않아도 구글에 널려 있기 때문에 구글링 참조

--------------

## hashCode 재정의 규약

만약 equals 메서드를 재정의 했다면 꼭 hashCode 메서드 또한 재정의 해야한다.  
다음은 Object 클래스 명세에서 발췌한 규약이다.

> equals 비교에 사용되는 정보가 변경되지 않았다면, 애플리케이션이 실행되는 동안
> 그 객체의 hashCode 메서드는 몇 번을 호출해도 일관되게 항상 같은 값을 반환해야 한다.  
> 단, 애플리케이션을 다시 실행한다면 이 값이 달라져도 상관없다.

> equals(Object)가 두 객체를 같다고 판단했다면, 두 객체의 hashCode는 똑같은 값을 반환해야 한다.

> equals(Object)가 두 객체를 다르다고 판단했더라도, 두 객체의 hashCode가 서로 다른 값을 반환할 필요는 없다.  
> 단, 다른 객체에 대해서는 다른 값을 반환해야 해시테이블의 성능이 좋아진다.

첫 번째와 세 번째 규약은 해시 함수와 해시 테이블의 원리를 안다면 자연스레 알고 있었을 것 이다.  
문제는 순수 자바 문법의 영역인 두 번째 규약인데, 만약 어떤 클래스의 equals 메서드를 재정의 하여 동치성 비교 방식을 논리적 동치성 비교 방식으로 바꾸었다면, hashCode 메서드 또한 논리적 동치성 비교 방식으로 동작해야 한다.
  
만약 hashCode를 사용하는 컬렉션(HashMap, HashSet)의 원소로 사용하는 객체가 이 두 번째 규약을 어기게 되면, 심각한 문제가 발생한다.  
오버라이딩 된 hashCode 메소드를 주석 처리하고, testMethod()를 호출 해보자.
  
**왜 null이 반환될까?**  

> HashMap 자료구조는 해시 함수로 계산된 hashCode로 값을 저장하고, 찾고, 꺼낸다.  
> 우리는 name이 같은 두 Keyboard 객체가 있으면, 두 객체가 같다고 판단하고 싶다. (논리적 동치성)  
> 하지만 Kayboard 클래스는 equals 메서드를 재정의 하여 두 객체의 논리적 동치성을 보장하였으나,
> hashcode 메서드를 재정의 하지 않았기 때문에,  
>  여전히 다른 hashCode가 나온다.  
> **두 객체의 객체 식별성은 다르기 때문이다.**  
> 따라서 HashMap은 엉뚱한 해시 버킷에 가서 객체를 찾다가, 결국 null을 리턴 한 것이다.  
> **애초에 HashMap의 구현은 해시 코드가 다른 엔트리끼리는 동치성 비교 자체를 하지 않도록 최적화 되어 있다.**

문제를 해결하는 방법은 단순하다. hashCode를 만드는 해시 함수를 equals 메소드를 재정의할 때 사용한 값과 같은 값을 사용하자.

**그렇다면 좋은 해시 함수를 만드는 방법은 무엇일까?**
> 이상적인 해시 함수(hash function) 동작은 깊게 들어가면 자료구조의 영역에 들어간다.  
> 평범한 개발자가 굳이 직접 만들어 쓸일이 있을까 싶고, Objects에선 검증된 성능의 hashCode를 반환해주는 정적 도우미 메서드가 이미 있다.  
>
> 해시 테이블(hash table)의 평균 시간 복잡도는 O(1)이고, 최악의 로직을 가진 해시 테이블의 시간 복잡도는 O(n) 이다.  
> O(n)은 무려 배열과 같은 시간 복잡도이다. 만약 이 해시 테이블이 해시 체이닝(hash chaining) 방식으로 구현되었다면,
> 진짜 링크드 리스트(linked list) 처럼 동작한다!  
> 자세한 내용은 자료구조 공부 때 배우는 것이 좋을 것 같다.