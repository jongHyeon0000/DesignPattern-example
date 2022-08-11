package DesignPattern.example.singleton.LazyInitializationSingleton;

@Deprecated
public class LazyInitializationSingleton {
  private static LazyInitializationSingleton instance;
  
  private LazyInitializationSingleton() {}
  
  public static LazyInitializationSingleton getInstance() {
    // ex) 두 Thread가 동시에 if(instance == null)에 진입하는 경우
    //    -> 런타임 중 동적으로 싱글톤 객체를 할당하므로 충분히 가능한 상황
    if(instance == null) {
      // 두 개의 instance가 생성될 수 있다. -> Lock이 필요한 상황
      instance = new LazyInitializationSingleton();
    }
    
    return instance;
  }
}
