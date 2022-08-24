# 추상 팩토리 패턴(Abstract Factory Pattern)

![ex_screenshot](../../../../resources/abstract_factory/176FF73D5039C4BA30.png)


> 객체 생성을 담당하는 구체 클래스를 상세하게 정의하지 않고도, 서로 의존성이 있거나 독립적인 여러 객체의 집합을 생성하기 위한 추상 클래스 혹은 인터페이스를 정의한다.
--------------------

## **생성 패턴(Creational Patterns)**
> 객체를 생성(클래스의 인스턴스를 만드는 절차), 합성(Compositon) 하는 방법을 기존 클래스에서 분리한다.  
>  **(SOLID, Single Responsibility Principle : 단일 책임 원칙)**

> 필요시 클래스의 인스턴스(instance)를 만드는 절차, 과정을 추상화(Abstraciton) 한다.

> 객체의 표현 방법을 기존 클래스에서 분리 한다.

-------------------
![ex_screenshot](../../../../resources/abstract_factory/Abstract_Method.drawio.png)

**BluetoothSupport**
> 블루투스 기능을 구현하기 위한 규약이 모인 인터페이스

**Computer**
> 컴퓨터를 표현하기 위한 클래스이다. 키보드, 모니터, 마우스를 표현한 객체를 필드에 인스턴스화 하여 사용하고 있다. 키보드, 모니터, 마우스는 컴퓨터라는 집합의 원소 이며, 라이프 타임 은 독립적이다.

**ComputerFactory**
> 컴퓨터 

-------------------
### **구체적인 제품 객체의 정보를 클라이언트에게서 분리한다.**
>main 메서드를 살펴보자. 우리는 한성 컴퓨터 객체를 생성하기 위해, 한성 컴퓨터 팩토리 객체를 사용했다.
한성 컴퓨터 팩토리 객체는 한성 컴퓨터가 사용하는 제품군(삼성 키보드, 애플 마우스, 애플 모니터)을 만들어 한성 컴퓨터 객체의 생성을 도와준다. main 메서드 어디에도 제품군 객체 코드는 존재하지 않는다.
따라서 사용자는 한성 컴퓨터가 어떤 마우스, 키보드, 모니터를 쓰는지는 알 필요가 없다. 제품 객체를 생성하는 과정과 책임을 캡슐화 하였고, 구체적인 구현 클래스가 클라이언트에게서 분리되었다.

추상 팩토리는 제품 객체를 생성하는 과정과 책임을 캡슐화 하였고, 구체적인 구현 클래스가 클라이언트에게서 분리되었다.

### **제품군을 쉽게 대체할 수 있다.**
>한성 컴퓨터를 MSI 컴퓨터로 바꾸고 싶다면, MSI 컴퓨터 팩토리 객체를 만들기만 하면 된다. MSI 컴퓨터 팩토리 객체는 키보드, 마우스, 모니터를 한 번에 MSI 컴퓨터 제품으로 변경 해준다. 한성 컴퓨터 팩토리와 MSI 컴퓨터 팩토리는 같은 추상 팩토리의 하위 클래스이니, 팩토리 객체를 변경하는 것 또한 유연하고 간단하다.

### **코드의 일관성을 높힌다.**
>MSI 컴퓨터는 삼성 제품군 필드만을 사용해야 하는데, MSI 컴퓨터를 만드는 사용자(클라이언트)가 충분히 실수로 애플 제품을 넣을 수도 있다. MSI 컴퓨터 객체의 일관성이 깨져버린 것이다. 팩토리 객체로 제품군을 만드는 과정은 한번에 이루어지므로, 객체 인스턴스 생성 중 객체의 일관성이 망가질 일이 없다.

### **새로운 타입의 제품을 제공하기 어렵다**
>만약 한성 컴퓨터와 MSI 컴퓨터가 스피커 까지 제공해야 한다면, 추상 팩토리와 구체 팩토리가 전부 수정되어야 한다.

--------------------
## **구현**

구체 팩토리 객체는 오직 특정 객체가 사용 할 제품군을 정해진 규약에 따라 생성하는 책임만 가지고 있다. 따라서 구체 팩토리 객체의 인스턴스는 프로그램 실행 중 하나만 있어도 충분하다. 

```Java
public interface ComputerFactory {
  public enum Compony {
    HANSUNG, MSI
  }

  ComputerFactory HANSUNG_COMPUTER_FACTORY = new HansungComputerFactory();
  ComputerFactory MSI_COMPUTER_FACTORY = new MsiComputerFactory();

  Keyboard CreateKeyboard(String name);
  Monitor CreateMonitor(String name, int cost, int weight, int inch);
  Mouse CreateMouse(int cost, int weight, String modelNumber);

  static ComputerFactory getComputerFactory(Compony compony) {
    switch (compony) {
      case HANSUNG:
        return HANSUNG_COMPUTER_FACTORY;
      case MSI:
        return MSI_COMPUTER_FACTORY;
      default:
        throw new IllegalStateException();
    }
  }
}
```

정적 팩터리 메서드(static factory method)를 이용해, 미리 만들어 놓은 팩토리 객체를 캐싱하여 사용하는 방법도 좋다.




