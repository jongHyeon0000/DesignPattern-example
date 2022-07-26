package DesignPattern.example.singleton;

public class DoubleCheckedLoadingSingleton {
  private static volatile DoubleCheckedLoadingSingleton instance;
  
  private DoubleCheckedLoadingSingleton() {}
  
  public DoubleCheckedLoadingSingleton getInstance() {
    if(instance == null) {
      synchronized(DoubleCheckedLoadingSingleton.class) {
        if(instance == null) {
          instance = new DoubleCheckedLoadingSingleton();
        }
      }
    }
    
    return instance;
  }
}
