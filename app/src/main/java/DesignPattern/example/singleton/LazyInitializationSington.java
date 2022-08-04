package DesignPattern.example.singleton;

//장점 :  -> 싱글톤 객체(instance : EagerInitializationSingleton)를 사용할 때
//           런타임 중 동적으로 싱글톤 객체를 할당 하여 사용할 수 있다.
//
//       
//단점 :  -> 멀티 쓰레드 환경에서 Thread-safe을 보장할 수 없다.(Line.17)
//
//        -> 런타임 중 악의적인 리플렉션 코드가 불변식을 깨뜨리기 위한 공격에 대한 방어 코드가 필요하다.(Line 23, 26)
//
//        LazyHolderSingleton lazyHolder1 = LazyHolderSingleton.getInstance();
//
//        Constructor<? extends LazyHolderSingleton> constructor = lazyHolder1.getClass().getConstructor(new Class[0]);
//        constructor.setAccessible(true);
//
//        LazyHolderSingleton lazyHolder2 = (LazyHolderSingleton) constructor.newInstance();

public class LazyInitializationSington {
  private static LazyInitializationSington instance;
  
  private LazyInitializationSington() {}
  
  public static LazyInitializationSington getInstance() {
    // ex) 두 Thread가 동시에 if(instance == null)에 진입하는 경우
    //    -> 런타임 중 동적으로 싱글톤 객체를 할당하므로 충분히 가능한 상황
    if(instance == null) {
      // 두 개의 instance가 생성될 수 있다. -> Lock이 필요한 상황
      instance = new LazyInitializationSington();
    }
    
    return instance;
  }
}
