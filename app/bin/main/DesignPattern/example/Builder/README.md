# 빌더 패턴(Builder Pattern)

![ex_screenshot](../../../../resources/builder/Builder-pattern-class-diagram.png)

------------------

## **생성 패턴(Creational Patterns)**
> 객체를 생성(클래스의 인스턴스를 만드는 절차), 합성(Compositon) 하는 방법을 기존 클래스에서 분리한다.  
>  **(SOLID, Single Responsibility Principle : 단일 책임 원칙)**

> 필요시 클래스의 인스턴스(instance)를 만드는 절차, 과정을 추상화(Abstraciton) 한다.

> 객체의 표현 방법을 기존 클래스에서 분리 한다.

----------------------

**NPC**
> 플레이어와 상호 작용이 가능한 NPC(Non-player character)를 구현한 클래스  
> 
> 각각의 NPC 객체는 이름(name), 비용(cost), 신규 플레이어 도우미 여부(helper),  
> NPC의 타입(npcType), NPC의 특성(npcModifier) 값을 가졌다.

**NPC.Builder**
> NPC 클래스의 인스턴스를 만드는 일련의 생성 과정을 분리한 정적 멤버 클래스

------------------------

## **점층적 생성자 패턴(Telescoping Constructor Pattern)**

몸집이 큰 객체는 그만큼 객체를 사용하기 위해 초기화 해야 할 값이나 상태 또한 많아지게 된다.
클라이언트 입장에서 한번 생각해보자. 객체 사용 전 꼭 초기화 해야 할 값이 5개이고, 클라이언트의 목적에 따라 초기화를 꼭 하지 않아도 되는 값이 13개인 객체가 있다.  

이 객체의 생성자는 아주 복잡하고 이해 하기도 어려울 것이다. 이런 몸집이 큰 객체의 생성은 어떻게 구현하면 좋을까?

아마 대부분은 점층적 생성자 패턴이 가장 먼저 떠오를 것이다.

```Java
  NPC(String name, NPC_Type npcType){
    this(name, npcType, 0, false, NPC_Modifier.FRIENDLY);
  }
  
  NPC(String name, NPC_Type npcType, int cost){
    this(name, npcType, cost, false, NPC_Modifier.FRIENDLY);
  }
  
  NPC(String name, NPC_Type npcType, int cost, boolean helper){
    this(name, npcType, cost, helper, NPC_Modifier.FRIENDLY);
  }
  
  NPC(String name, NPC_Type npcType, int cost, boolean helper, NPC_Modifier npcModifier){
    this.name = name;
    this.npcType = npcType;
    this.cost = cost;
    this.helper = helper;
    this.npcModifier = npcModifier;
  }
```

얼핏 보아도 유연하지 않은 코드임이 느껴진다. cost, helper, npcModifier 는 필수 입력 값이 아니기 때문에, 입력 하지 않을 시 임의 값을 넣어 최종 생성자에 넘겨준다.  

아직은 매개변수가 겨우 5개 뿐이지만 조금만 파라미터가 늘어나도 생성자가 차지하는 코드의 비율은 걷잡을 수 없게 늘어난다.

클라이언트가 생성자로 특정 필드 값을 직접 설정하길 원하지 않고 NPC 클래스의 디폴트 값을 사용하고 싶을 수도 있다. 하지만 점층적 생성자 패턴에서는 클라이언트가 직접 임의 값을 넣어 주어야 한다. 생성자 파라미터가 더욱 늘어난다면, 클라이언트는 임의 값을 정하기 위해 NPC의 내부 구현을 정확히 알아야 하는 부담이 가중된다.

만약 같은 타입의 매개변수가 연속되어 배치됬다면, 단순한 실수가 찾기 어려운 버그로 이어 질 수 있다. 컴파일 타임이 아닌 런타임에 엉뚱하게 동작하는 것 뿐이고, 타입이 같으니 아규먼트 체크를 통과 할 지, 못할지 알 수없다. 잘못된 값으로 필드가 초기화 됬다면 버그 발생지점은 생성자 호출 지점이지만, 발견되는건 먼 미래에 객체 사용 중 발생 할 것이다.

```Java
public static void simpleMethod(int age, int cost, int level, int charNum) {
  ...Init logic...    
}
  
public static void main(String[] args) {
  int age = 18;
  int cost = 1400;
  int level = 210;
  int charNum = 1;

  // 단순한 실수지만 프로그램 규모가 커지면 디버깅 비용이 높아질 수 있다.
  simpleMethod(age, level, cost, charNum);
}
```
-----------------------
## **자바빈즈 패턴(javabeans pattern)**

```Java
...
// NPC 객체의 필수 필드, 반드시 초기화 되어야 하기 때문에 기본값이 있을 수 없다. 
private String name;
private NPC_Type npcType;

private int cost = 1;
private boolean helper = false;
private NPC_Modifier npcModifier = NPC_Modifier.FRIENDLY;

public void setName(String name) {
  this.name = name;
}

public void setNpcType(NPC_Type npcType) {
  this.npcType = npcType;
}

public void setCost(int cost) {
  this.cost = cost;
}

public void setHelper(boolean helper) {
  this.helper = helper;
}

public void setNpcModifier(NPC_Modifier npcModifier) {
  this.npcModifier = npcModifier;
}

...
```

세터(setter) 메서드가 보기 싫게 느껴지지만, 클라이언트에서 NPC 객체 생성 코드는 더 간결해질 것 같다.

```Java
public static void main(String args[]) {
  NPC npc = new NPC();
  npc.setCost(200);
  npc.setHelper(true);
  npc.setName("jong");
  npc.setNpcModifier(NPC_Modifier.FRIENDLY);
}
```

점층적 생성자 패턴보다 객체 생성 코드의 가독성도 높아지고, 더 간결해졌다.

하지만 자바빈즈 패턴은 심각한 단점을 하나 가지고 있는데, 객체 하나를 만들기 위해 세터 메서드를 여러 개 호출 해야 하는 것이다. 만약 클라이언트가 몇몇 세터 메서드를 누락한다면, NPC 객체는 마치 생성자에서 문제가 생긴 객체마냥 일관성(consistency)를 잃게 된다. 물론 자바 컴파일러는 아무런 도움도 주지 못한다. 이렇듯 객체가 시한폭탄이 되는 건 오직 클라이언트 개발자의 손에 맡겨있는데, NPC 객체의 오작동을 막기 위해 각 필드의 내부 구현을 자세히 설명하는 API를 작성하는 것은 매우 고될 것이다.

또한 자바빈즈 패턴이 적용된 클래스는 세터 메서드를 필수로 가지고 있고, 객체의 일관성이 언제든 무너질 수 있는 클래스 이기 때문에, 절대 불변 클래스로 만들 수 없다. NPC 객체가 멀티 쓰레드 프로그램 위에서 작동 중이라면 쓰레드 안전을 위한 동기화 이슈도 몇 배는 더 복잡해진다.

생성이 끝난 객체를 수동으로 얼리고(freezing), 얼리기 전에는 사용할 수 없도록 하기도 한다.
물론 다루기 매우 어려울 뿐 더러 자바 컴파일러나 자바 문법의 어떤 도움도 받지 못하니, 매우 취약한 방법이다.

이제 빌더 패턴을 알아보자.

-------------------------
## 빌더 패턴(Builder Pattern)

**복잡한 객체를 생성하는 방법과 표현하는 방법을 정의하는 클래스를 별도로 분리한다.**  
> 객체의 생성을 담당하는 책임을 다른 클래스로 분리 함으로써 코드 유지 보수성을 늘린다.  
> **(SOLID, Single Responsibility Principle : 단일 책임 원칙)**

**서로 다른 표현이라도 이를 생성할 수 있는 동일한 절차를 제공할 수 있도록 한다.**
> NPC 객체로 예를 들면 내부 필드에 String, int, boolean, NPC_Type, NPC_Modifier 등 다양한 타입이 존재한다.  
> 이들의 초기화는 빌더 패턴에 의해 설계 된 동일한 절차로 이루어 진다. 

**가변인수(varargs) 매개변수를 여러 개 사용 할 수 있다.**
> 생성자에서는 누릴 수 없는 사소한 이점인데, 가변인수는 파라미터당 하나만 존재 할 수 있다. 하지만 빌더 패턴은 객체의 생성을 다양한 세터 메서드로 분리 했으므로, 가변인수 각각을 적절한 메서드로 나눠 선언한 것과 같다.

이제 클라이언트는 필요한 객체를 직접 만드는 대신, 필수 파라미터만으로 생성자나 정적 팩터리 메서드를 호출해 빌더 객체를 얻는다.

NPC.Builder는 name과 npcType을 생성자로 받음으로써, 외부 클래스(outer class)의 핵심 필드 name과 npcType의 초기화를 문법적으로 강제한다. 물론 핵심 필드가 많다면, NPC 객체 생성을 담당하는 팩토리 객체를 받는 설계도 충분히 좋다.

빌더 패턴이 구현된 객체의 초기화는 플루언트 API(fluent API) 또는 메서드 연쇄(method chaining)이라는 기법이 사용된다. NPC.Builder의 세터 메서드는 특정 필드를 초기화 하고, 그 필드를 가진 자기 자신의 참조값(this)를 리턴한다. 클라이언트는 더 초기화 하고 싶은 필드가 있다면, 리턴된 참조값에 바로 세터 메서드를 호출하면 된다. 

초기화가 끝나면 최종적으로 build() 메서드를 호출 한다. build() 메서드는 초기화된 필드가 담긴 자기 자신의 참조값을 NPC의 생성자에 넘겨준다. NPC의 생성자는 파라미터로 받은 NPC.Builder에서 필드를 모두 추출한다. 그렇게 만들어진 NPC 객체를 build() 메서드가 최종적으로 리턴한다.

빌더 패턴은 주로 초기화 할 필드가 많아 생성자의 파라미터가 많을 때 주로 사용된다. 따라서 NPC.Builder의 생성ㅈ와 세터 메서드는 입력받은 파라미터가 올바른 상태(state)인지 확인해야 한다.
또한 NPC 객체의 생성자는 NPC 객체 필드의 불변식을 보장하기 위해 방어적 코드가 삽입 되어야한다.






