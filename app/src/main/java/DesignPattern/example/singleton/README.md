## 6가지 종류의 싱글톤 패턴(Singleton Pattern)과 안전성이 검증된 두 싱글톤 패턴(Lazy Holder, Enum Singleton)

# 용어 정리
  - 불변식(Invariant) 
      어떤 객체가 정상적으로 작동하기 위해 절때 허무러지지 않아야 하는 값, 식, 상태 등을 뜻한다.
      Car라는 객체에는 현재 보유한 연료의 양과 최대로 넣을 수 있는 연료의 양이 있다고 예를 들자

      <pre>
      <code>
        class Car{
          private static final int MAX_OIL_VALUE = 1000;
          private int oilValue;

          Car(int oilValue){
            this.oilValue = oilValue;
          }
        }
      </code>
      </pre>

      이때 oilValue의 값이 0 미만이 되거나, MAX_OIL_VALUE를 넘어서는 경우는 정상적인 자동차의 상태(State)
      라고 하기는 어렵다.

      이런 경우를 "Car 객체(Object)의 불변식(Invariant)이 깨졌다." 
      라고 부를 수 있다.

      <pre>
      <code>
        class Car{
          private static final int MAX_OIL_VALUE = 1000;
          private int oilValue;

          Car(int oilValue){
            // 불변식(Invariant)를 유지하기 위한 방어적 아규먼트 체크
            if(oilValue < 0 && oilValue > MAX_OIL_VALUE){
              // 불변 클래스(Immutable Class)가 아니라면 예외를 던질 때 실패 원자성(Failure Atomicity)을 고려해야 한다.
              throw new IllegalArgumentException();
            }
            this.oilValue = oilValue;
          }
        }
      </code>
      </pre>

  - 불변 클래스(Immutable Class)
      객체 내부의 값을 처음 객체가 생성 될 때 외에는 수정, 변경할 수 없는 클래스를 말한다.

      안정성을 검증받은 불변 클래스를 설계하는 법은 다음과 같다.
      <pre>
      <code>
        Class People{
        private int age;
        private String name;

        People(int age, String name){
          this.age = age;
          this.name = name;
        }


        public void setAge(int age){
          this.age = age;
        }

        public void setName(String name){
          this.name = name
        }
      }
      </code>
      </pre>

      1. 객체의 상태를 변경하는 메서드(변경자, setter)를 제공하지 않는다.

      <pre>
      <code>
        Class People{
          private int age;
          private String name;

          People(int age, String name){
            this.age = age;
            this.name = name;
          }
        }
      </code>
      </pre>

      <pre>
      <code>
      </code>
      </pre>