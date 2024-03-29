# DoubleCheckedLoadingSingleton

> LazyInitializationSynchronized 싱글톤 패턴에서 성능 이슈를 보완한 싱글톤 패턴 

## 장점 
이 싱글톤 객체를 사용하기 위해 getInstance()를 호출 하여 instance를 생성한 경우
첫 번째 if문 에서 synchronized block 접근을 제한한다.  
런타임중 synchronized block에 접근하는 경우는 instance가 처음 초기화될 때 뿐이므로,
실제 런타임중 성능이 크게 개선된다.
           
-------------------------

## 단점
둘 이상의 CPU가 탑재된 컴퓨터에서 실행 시 선언한 변수의 값(instance : DoubleCheckedLoadingSingleton)이
Main Memory에만 존재하는 것이 아니라, CPU 캐시(cahce)라고 하는 영역에도 존재한다.
이는 CPU가 Main Memory에서 값을 읽고(Read), 다시 쓰고(Write) 하는 시간을 아끼기 위함이다.
문제는 CPU cache에서 새로 정의된 값이 Main Memory에 언제 Read 할지 모른다는 것이다. **(가시성 이슈, Visibility Issue)**
>          
> 1. 첫번째 Thread가 instance 를 생성하고 synchronized block을 벗어난다.
 
> 2. 첫번째 Thread에서 생성한 instance 가 자신의 Working Memory(여기서는 CPU cache) 에만 존재하고,
> Main Memory 에는 존재하지 않을 경우(아직 Main Memory에 Write가 이루어지지 않음.)
> Main Memory에는 아직 instance가 null 이므로, 두 번째 스레드가 synchronized block에 들어오게 된다.  
> 혹은, 첫번째 Thread가 Main Memory에 초기화 된 instance를 Main Memory로 Write 했으나,
> 두 번째 Thread에서 아직 Read 하지 않아, Working Memory에는 instance가 아직 null인 경우
> 두 번째 Thread가 synchronized block에 들어오게 된다.
       
> 4. 두 번째 Thread는 instance를 또 생성하게 된다. 결국 클래스의 불변식(Invariant)이 깨져버렸다.
       
> 5. 따라서 instance는 volatile 키워드를 통해 가시성(Visibility)을 확보해야 한다.
        
그럼 정말 DoubleCheckedLoading 싱글톤 패턴은 멀티 쓰레드 환경에서 안전한가?  
volatile 제어자(Modifier)는 가시성(Visibility)은 확보하지만, 원자성(Atomicity)은 확보하지 못한다.
        
해당 내용은 보강 예정

    https://velog.io/@syleemk/Java-Concurrent-Programming-%EA%B0%80%EC%8B%9C%EC%84%B1%EA%B3%BC-%EC%9B%90%EC%9E%90%EC%84%B1

이미 정리된 포스트가 있다.