# EagerInitializationSingleton


## **장점** 
### **Method Area에 싱글톤 클래스가 로딩 될 때, 싱글톤 객체 인스턴스가 초기화 된다.**
> 정적(static)으로 선언된 변수는 Class Loader에 의해 Method Area(Static Area)으로 로드된다.
           
이는 개발자가 간섭 할 수 없는 JVM의 영역이므로 항상 Thread-safe 을 보장한다.
           
## **단점**
### **사용 유무와 상관없이, 클래스가 로딩되는 시점에 항상 싱글톤 객체 인스턴스가 생성된다.**
> 무거운 싱글톤 객체인 경우 메모리에 부담이 가중된다.(Memory Leak)