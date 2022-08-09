package DesignPattern.example.singleton.LazyInitializationSynchronizedSingleton;

@Deprecated
public class LazyInitializationSynchronizedSingleton {
  private static LazyInitializationSynchronizedSingleton instance;
  
  private LazyInitializationSynchronizedSingleton() {}
  
  public static synchronized LazyInitializationSynchronizedSingleton getInstance() {
    if(instance == null) {
      instance = new LazyInitializationSynchronizedSingleton();
    }
    
    return instance;
  }
}
