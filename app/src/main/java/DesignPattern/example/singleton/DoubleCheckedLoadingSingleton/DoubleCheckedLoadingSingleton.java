package DesignPattern.example.singleton.DoubleCheckedLoadingSingleton;
        
@Deprecated
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
//        https://junghyungil.tistory.com/150