package DesignPattern.example.singleton;

//장점 : LazyInitializationSynchronized 싱글톤 패턴에서 성능 이슈를 보완한 싱글톤 패턴
//        -> 이 싱글톤 객체를 사용하기 위해 getInstance()를 호출 하여 instance를 생성한 경우
//           첫 번째 if문(Line 13) 에서 synchronized 블록(Line 15) 접근을 제한한다.
//           런타임중 synchronized 블록(Line 15)에 접근하는 경우는 instance가 처음 초기화될 때 뿐이므로,
//           실제 런타임중 성능이 크게 개선된다.
//           
//
//단점 :  실제 안전한가?
//        -> 둘 이상의 CPU가 탑재된 컴퓨터에서 실행 시 선언한 변수의 값(instance : DoubleCheckedLoadingSingleton)이
//        Memory에만 존재하는 것이 아니라, CPU 캐시(cahce)라고 하는 영역에도 존재한다.
//        이는 CPU가 Memory에서 값을 읽고(Read), 다시 쓰고(Write) 하는 시간을 아끼기 위함이다.
//        더 큰 문제는 CPU cache에 값이 Memory에 언제 옮겨갈지도 모른다는 것이다. (가시성 이슈, Visibility Issue)
//           
//        1. 첫번째 Thread가 instance 를 생성하고 synchronized 블록을 벗어난다(Line 33).
//
//        2. 첫번째 Thread에서 생성한 instance 가 자신의 Working Memory(여기서는 CPU cache) 에만 존재하고,
//        Main Memory 에는 존재하지 않을 경우(아직 Main Memory에 Write가 이루어지지 않음.)
//        Main Memory에는 아직 instance가 null 이므로, 두 번째 스레드가 synchronized 블록(Line 33)에 들어오게 된다.
//        
//        혹은, 첫번째 Thread가 Main Memory에 instance를 자신이 초기화 한 DoubleCheckedLoadingSingleton으로 Write 했으나,
//        두 번째 Thread에서 아직 Read 하지 않아, Working Memory에는 instance가 아직 null인 경우
//        두 번째 Thread가 synchronized 블록(Line 33)에 들어오게 된다.
//        
//        4. 두번째 Thread는 instance를 또 생성하게 된다. 결국 클래스의 불변식(invariant)이 깨져버렸다.
//        
//        5. 따라서 instance는 volatile 키워드를 통해 가시성을 확보해야 한다.
//        
//        -> 그럼 정말 DoubleCheckedLoading 싱글톤 패턴은 멀티 쓰레드 환경에서 안전한가?
//        volatile 제어자(Modifier)는 가시성(Visibility)은 확보하지만, 원자성(Atomicity)은 확보하지 못한다.
//        
//        자세한 설명은 구글링을 이용하자
        
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