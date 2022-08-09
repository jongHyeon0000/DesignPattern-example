# EnumSingleton
## 장점

enum 문법이 제공하는 모든 장점을 누릴 수 있다.
        
> 자바에서의 enum은 C++에서의 EnumClass랑 비슷하지만 더 강력하다.  
> enum 내부의 값은 public static final로 선언된 인스턴스와 같은 취급을 받는다.  
> final Modify는 초기화 시점 외에는 값 변경이 불가능한 상수와 같은 성질을 지닌다.  
> 따라서 enum은 그 자체로 불변 클래스(Immutable Class)라고 할 수 있다.  
        
- 불변 클래스는 Thread-safe 하다.
        
> " ...불변 객체는 근본적으로 스레드 안전하여 따로 동기화할 필요 없다.  
> 여러 스레드가 동시에 사용해도 절대 훼손되지 않는다.  
> 사실 클래스를 스레드 안전하게 만드는 가장 쉬운 방법이기도 하다. 불변 객체에 대해서는  
> 그 어떤 스레드도 다른 스레드에 영향을 줄 수 없으니 불변 객체는 안심하고 공유할 수 있다. "  
> (Effective Java Item.17)
        
> 개발자가 직렬화(Serizalizable)를 구현하지 않더라도,
> enum의 직렬화는 JVM이 내부적으로 안전하게 처리해준다.
> 직접 java.lang.enum에 들어가 확인해보면 알 수 있다.

> java.lang.reflect Constructor 클래스의 newInstance 메소드는 enum 타입의 아규먼트를
> 전달 할 경우 예외를 던지도록 구현되었기 때문에, 리플렉션 공격에 대한 방어 처리를
> 신경 쓸 필요가 없다.

---------------------

## 단점
enum 문법의 한계가 EnumSingleton의 한계가 된다.

> 영속성을 가진 환경에서 개발 시 사용이 어렵다 ex)Android  
> 자세한 내용은 구글링