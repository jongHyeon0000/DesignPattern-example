package DesignPattern.example.singleton;

//장점 : 정적(static)으로 생성된 변수에 싱글톤 객체(instance : EagerInitializationSingleton)를 선언 및 할당 한다.
//        -> 정적으로 선언된 변수는 Class Loader에 의해 클래스가 로딩될 때 Method Area(Static Area)에
//           싱글톤 객체가 생성된다.
//           
//           따라서 클래스가 최초 로딩될 때 객체가 생성됨으로 Thread-safe 하다.
//           
//
//단점 : 싱글톤 객체 사용 유무와 상관없이, 클래스가 로딩되는 시점에 항상 싱글톤 객체가 생성된다.
//        -> 무거운 싱글톤 객체인 경우 메모리에 부담이 가중된다.(MemoryLeak)

@Deprecated
public class EagerInitializationSingleton {
  private static EagerInitializationSingleton instance = new EagerInitializationSingleton();
  
  private EagerInitializationSingleton() {}
  
  public static EagerInitializationSingleton getInstance() {
    return instance;
  }
}
