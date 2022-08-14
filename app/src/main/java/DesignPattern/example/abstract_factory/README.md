# 추상 팩토리 패턴(Abstract Factory Pattern)

하하

# Keyboard

## 객체 식별성(물리적 식별성, Object Identity)
> 두 객체(object)가 물리적으로 같은지를 비교하는 것이다.  
> new 연산자를 이용해 객체를 생성하면 JVM의 Heap area에 객체의 실제 인스턴스 값이 할당 되고, Stack Area에서 해당 인스턴스를 가리키는 참조값을 
> 가지게 된다.  
> Object에서 구현된 equel은 두 객체의 참조값을 비교해 참조값이 다르면 두 객체는 다르다고 판단하는 역할을 하는 메소드이다. 