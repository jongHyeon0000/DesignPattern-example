package DesignPattern.example.singleton;

public class LazyInitializationSington {
  private static LazyInitializationSington instance;
  
  private LazyInitializationSington() {}
  
  public static LazyInitializationSington lazyInitializationSington() {
    if(instance == null) {
      instance = new LazyInitializationSington();
    }
    
    return instance;
  }
}

synchronized
