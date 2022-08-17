# 빌더 패턴(Builder Pattern)

------------------

## 생성 패턴(Creational Patterns)
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

## 장점
### **객체를 생성하는 과정이 캡슐화 된다.**

몸집이 큰 객체는 그만큼 객체를 사용하기 위해 초기화 해야 할 값이나 상태 또한 많아지게 된다.
클라이언트 입장에서 한번 생각해보자. 꼭 초기화 해야 할 값이 5개이고, 클라이언트의 목적에 따라 초기화를 꼭 하지 않아도 되는 값이 13개인 객체가 있다고 치면, 이 객체의 생성자는 아주 복잡하고 이해 하기도 어려울 것이다. 이런 몸집이 큰 객체의 생성은 어떻게 구현하면 좋을까?

아마 대부분은 점층적 생성자 패턴(telescoping constructor pattern)이 가장 먼저 떠오를 것이다.

```
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

얼핏 보아도 유연하지 않은 코드임이 느껴진다. cost, helper, npcModifier 는 필수 입력 값이 아니기 때문에, 입력 하지 않을 시 임의 값을 넣어 최종 생성자에 넘겨준다. 아직은 매개변수가 겨우 5개 뿐이지만 조금만 파라미터가 늘어나도 생성자가 차지하는 코드의 비율은 걷잡을 수 없게 늘어난다.
또한 cost나 helper처럼 임의 값을 넣는 것이 부담 스럽지 않을 수 있지만, 이는 결국 하드 코딩(hard coding)이다. 그럼npcModifier는 어떨까?
특성이 없음을 나타내기 위해 NPC_Modifier 의 설계를 변경해야 할 수도 있다. 개발자와 클라이언트 모두 객체를 생성하는 데에만 많은 시간을 쓸 것이다. 거기다 만약 같은 타입의 매개변수가 연속되어 배치됬다면 단순한 실수가 찾기 어려운 버그로 이어 질 수 있다.   