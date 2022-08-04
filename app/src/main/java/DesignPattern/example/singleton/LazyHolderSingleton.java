package DesignPattern.example.singleton;

import java.lang.reflect.Constructor;
import java.util.concurrent.atomic.AtomicBoolean;

//장점 :  -> 정적 멤버 클래스(Static Member Class)의 이점을 활용 할 수 있다.
//           클래스 내부에 선언된 정적 멤버 클래스는 처음 호출되기 전 초기화 되지 않지만,
//           getInstance(Line 10)를 클라이언트가 호출하는 순간 LazyHolder(Line 14)클래스가 초기화 된다.
//           이 때 instance에 싱글톤 객체가 할당되므로, 런타임 중 동적으로 instance의 초기화를 제어한다.
//           따라서 메모리의 부담(Memory Leak)을 줄일 수 있다.
//           
//           또한 클래스 로딩은 JVM의 클래스 로더(Class Loader)에 의해 이루어진다.
//           이는 개발자가 간섭 할 수 없는 영역이므로 항상 Thread-safe을 보장한다.
//
//        -> Android 환경에서의 개발과 같이 Context 의존성이 존재하는 경우
//           싱글톤 객체 초기화 과정 중 Context가 끼어들 가능성이 있다.
//           LazyHolder 싱글톤 패턴은 이런 상황에 대해 안전하다
//           자세한 사항은 구글링
//
//
//단점 :  -> (이하 내용은 EnumSingleton을 제외하면 모든 싱글톤 패턴이 공유하는 문제다.)
//
//           1. 역직렬화(Deserializable) 이슈
//              ... 둘 중 하나의 방식(EagerInitialzation) 으로 만든 싱글턴 클래스를 직렬화하려면 단순히 Serializable을
//              구현한다고 선언하는 것만으로는 부족하다. 모든 인스턴스 필드를 일시적(transient)이라고 선언하고
//              readResolve 메서드를 제공해야 한다. 이렇게 하지 않으면 직렬화된 인스턴스를 역직렬화할 때마다
//              새로운 인스턴스가 만들어진다. (Effective Java Item.3)
//
//           2. 런타임 중 악의적인 리플렉션 코드가 불변식을 깨뜨리기 위한 공격에 대한 방어 코드가 필요하다.(Line 40, 42)


public class LazyHolderSingleton {
  // 원자성(Atomicity)을 보장하는 Boolean 타입을 다루는 AtomicBoolean 클래스
  // 이 객체의 상태(state)를 싱글톤 객체가 로딩 될 때 false로 초기화 한다.
  private static final AtomicBoolean isCreated = new AtomicBoolean(false); 
  
  private LazyHolderSingleton() {
    if(isCreated.get()) {
      throw new IllegalStateException();
    }
    //생성자가 처음 호출될 때 isCreated 객체의 상태를 true로 바꾼다.
    isCreated.compareAndSet(false, true);
  };
  
  public static LazyHolderSingleton getInstance() {
    return LazyHolder.instance;
  }
  
  private static class LazyHolder{
    private static final LazyHolderSingleton instance = new LazyHolderSingleton();
  }
}
           
class Villain{
  public void ReflectionAttack() throws Exception{
    LazyHolderSingleton lazyHolder1 = LazyHolderSingleton.getInstance();
    
    Constructor<? extends LazyHolderSingleton> constructor = lazyHolder1.getClass().getConstructor(new Class[0]);
    constructor.setAccessible(true);
    
    //lazyHolder1이 있음에도 lazyHolder2가 새로 만들어졌다!
    LazyHolderSingleton lazyHolder2 = constructor.newInstance();
  }
}
