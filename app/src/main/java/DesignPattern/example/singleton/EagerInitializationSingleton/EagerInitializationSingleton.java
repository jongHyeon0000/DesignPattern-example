package DesignPattern.example.singleton.EagerInitializationSingleton;

@Deprecated
public class EagerInitializationSingleton {
  private static EagerInitializationSingleton instance = new EagerInitializationSingleton();
  
  private EagerInitializationSingleton() {}
  
  public static EagerInitializationSingleton getInstance() {
    return instance;
  }
}
