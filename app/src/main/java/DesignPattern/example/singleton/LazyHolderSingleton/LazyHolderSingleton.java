package DesignPattern.example.singleton.LazyHolderSingleton;

import java.lang.reflect.Constructor;
import java.util.concurrent.atomic.AtomicBoolean;

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
  public static void ReflectionAttack() throws Exception{
    LazyHolderSingleton lazyHolder1 = LazyHolderSingleton.getInstance();
    
    Constructor<? extends LazyHolderSingleton> constructor = lazyHolder1.getClass().getConstructor(new Class[0]);
    constructor.setAccessible(true);
    
    //lazyHolder1이 있음에도 lazyHolder2가 새로 만들어졌다!
    LazyHolderSingleton lazyHolder2 = constructor.newInstance();
  }
}
