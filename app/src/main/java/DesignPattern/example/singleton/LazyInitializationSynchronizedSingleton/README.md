# LazyInitializationSynchronizedSingleton
LazyInitialization 싱글톤 패턴에서 Thread-safe 이슈를 해결 한 싱글톤 패턴 이다.

## 장점

> synchronized 키워드를 getInstance() 메소드에 적용해 하나의 Thread만이 
> getInstance() 메소드에 접근 가능 하다.
> 
> synchronized 키워드는 해당 메소드나 블록을 한번에 한 스레드씩 수행하도록 보장한다.

            " ... 한 객체가 일관된 상태를 가지고 생성되고, 이 객체에 접근하는 메서드는 그 객체에 락(lock)을 건다.  
            ... 즉, 객체를 하나의 일관된 상태에서 다른 일관된 상태로 변화시킨다.
            동기화를 제대로 사용하면 어떤 메소드도 이 객체의 상태가 일관되지 않은 순간을 볼 수 없을 것이다. "
            Effective Java Item.78
            
-------------------------

## 단점
> synchronized 블록은, 해당 영역(scope)이나 메소드를 Lock-Unlock 처리하기 때문에 
> 많은 비용이 발생한다.  
> 대략 50~200배 느려진다고 한다..