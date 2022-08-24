package DesignPattern.example.abstract_factory.monitor;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

abstract public class Monitor {
  protected String name;
  protected int cost;
  protected int weight;
  protected int inch;

  Monitor(String name, int cost, int weight, int inch) {
    this.name = name;
    this.cost = cost;
    this.weight = weight;
    this.inch = inch;

    LowestPriceMonitorManager.putMonitor(this);
  }

  public String getName() {
    return name;
  }

  public int getCost() {
    return cost;
  }

  /**
   * <p>
   * 가장 저렴한 Monitor 객체의 주요 정보(제품명 name, 가격 cost)를 타입 안전 이종 컨테이너 패턴으로 보관합니다.
   * </p>
   * 
   * <p>
   * 타입 안전 이종 컨테이너는 Collection의 HashMap으로 구현 됩니다.
   * </p>
   * 
   * <p>
   * Monitor 추상 클래스에서 파생된 하위 클래스의 타입 토큰을 키(key)로 사용합니다. 키(key)에 대응하는 값(value)으로 제품명을 키(key)로 가지고, 해당
   * 제품의 최저가를 값(value)으로 가집니다.
   * </p>
   * 
   * <p>
   * 이 클래스의 메서드는 Map 객체의 불변식을 보장하면서, 새 원소의 추가와 값 접근 기능을 제공하는 정적 도우미 메서드로 이루어져 있습니다.
   * </p>
   * 
   * <p>
   * 이 클래스를 확장하거나 메서드를 오버라이딩 할 수 없습니다.
   * </p>
   * 
   * <p>
   * 이 클래스는 멀티 쓰레드 환경에서 thread-safe을 보장하지 않습니다. Map 객체의 인스턴스가 2개 이상 만들어 진다면, 클래스의 불변식은 보장되지 않습니다.
   * </p>
   * 
   * @author jongHyeon0000
   * @since 2022-08-24
   */
  public static final class LowestPriceMonitorManager {
    private static final Map<Class<? extends Monitor>, Map<String, Integer>> lowestPriceMonitorMap =
        new HashMap<Class<? extends Monitor>, Map<String, Integer>>();

    /**
     * <p>
     * Monitor 객체의 생성자에서 호출되는 자가 사용 메서드입니다.
     * </p>
     * 
     * <p>
     * 이 메서드를 Monitor 객체의 생성자 외부에서 호출 시 클래스의 불변성을 보장하지 않습니다.
     * </p>
     * 
     * <p>
     * 이 클래스는 리플렉션 공격에 대한 방어를 보장하지 않습니다. 리플렉션 API를 이용해 이 메서드를 호출 시 클래스의 불변식을 보장할 수 없습니다.
     * </p>
     * 
     * @param monitor : 생성된 Monitor 객체 자신의 참조값(this)
     * @throws NullPointerException 이 메서드를 호출한 객체의 생성이 정상적으로 이루어지지 않았을 경우 던져집니다. 이 예외가 발생한 Monitor
     *         객체는 정상적인 초기화 과정이 이루어지지 않았으므로, 일관성을 보장 하지 않습니다. 이 클래스의 타입 안전 이종 컨테이너는 해당 Monitor 객체를
     *         원소로 삽입하지 않습니다.
     * @since 2022-08-24
     */
    private static final void putMonitor(Monitor monitor) {
      Class<? extends Monitor> monitorType = Objects.requireNonNull(monitor).getClass();

      Map<String, Integer> concreteMonitorMap;

      if (Objects.nonNull(concreteMonitorMap = lowestPriceMonitorMap.get(monitorType))) {
        if (Objects.nonNull(concreteMonitorMap.get(monitor.getName()))) {
          int lowestPrice = concreteMonitorMap.get(monitor.getName());

          if (lowestPrice > monitor.getCost()) {
            concreteMonitorMap.put(monitor.getName(), monitor.getCost());
          }
        } else {
          concreteMonitorMap.put(monitor.getName(), monitor.getCost());
        }
      } else {
        Map<String, Integer> map = new HashMap<>();
        map.put(monitor.getName(), monitor.getCost());

        lowestPriceMonitorMap.put(monitorType, map);
      }
    }

    /**
     * <p>
     * Monitor 추상 클래스에서 파생된 하위 클래스의 타입 토큰을 키(key)로 사용합니다. 키(key)에 대응하는 값(value)으로 제품명을 키(key)로 가지고,
     * 해당 제품의 최저가를 값(value)으로 가지는 Map 객체의 복사본을 반환합니다.
     * </p>
     * 
     * <p>
     * 만약 원소를 찾을 수 없다면 Collections의 빈 맵(EMPTY_MAP)을 반환합니다.
     * </p>
     * 
     * @param monitorType : Monitor 추상 클래스에서 파생된 하위 클래스의 타입 토큰
     * @return 제품명을 키(key)로 가지고, 해당 제품의 최저가를 값(value)으로 가지는 Map 객체와 같은 데이터를 가진 새 Map 객체를 반환합니다.
     * @since 2022-08-24
     */
    public static final Map<String, Integer> getLowestPriceMonitorMap(
        Class<? extends Monitor> monitorType) {
      Map<String, Integer> concreteMonitorMap =
          lowestPriceMonitorMap.get(Objects.requireNonNull(monitorType));

      if (Objects.nonNull(concreteMonitorMap)) {
        return concreteMonitorMap;
      } else {
        return Collections.emptyMap();
      }
    }

    /**
     * <p>
     * 제품명의 최저가를 반환합니다.
     * </p>
     * 
     * <p>
     * 이 메서드는 오버로딩 되어 있으며, 다른 메서드의 자가 사용 메서드 입니다. 이 메서드를 재정의 한 경우 다른 메서드의 정상적인 동작을 보장하지 않습니다.
     * </p>
     * 
     * @param monitorType : Monitor 추상 클래스에서 파생된 하위 클래스의 타입 토큰
     * @param monitorName : Monitor의 제품명
     * @throws IllegalStateException Monitor 추상 클래스에서 파생된 하위 클래스가 한번도 인스턴스화 되지 않은 경우 던져집니다.
     * @return 정수 자료형(int)으로 제품명의 최저가를 반환합니다.
     * @since 2022-08-24
     */
    public static final int getLowestPriceMonitor(Class<? extends Monitor> monitorType,
        String monitorName) {
      int lowestPrice = getLowestPriceMonitorMap(monitorType).get(monitorName);

      if (Objects.nonNull(lowestPrice)) {
        return lowestPrice;
      }

      throw new IllegalStateException(String.format("%s 제품이 없습니다.", monitorName));
    }

    /**
     * <p>
     * 제품명의 최저가를 반환합니다.
     * </p>
     * 
     * <p>
     * 이 메서드는 오버로딩 되어 있으며, 다른 메서드를 자가 사용 합니다. 이 메서드를 재정의 한 경우 다른 메서드의 정상적인 동작을 보장하지 않습니다.
     * </p>
     * 
     * @param monitorType : Monitor 추상 클래스에서 파생된 하위 클래스의 타입 토큰
     * @param monitor : monitor 객체
     * @throws IllegalStateException Monitor 추상 클래스에서 파생된 하위 클래스가 한번도 인스턴스화 되지 않은 경우 던져집니다.
     * @throws NullPointerException monitor 객체가 null 일 경우 던져집니다.
     * @return 정수 자료형(int)으로 제품명의 최저가를 반환합니다.
     * @since 2022-08-24
     */
    public static final int getLowestPriceMonitor(Class<? extends Monitor> monitorType,
        Monitor monitor) {
      return getLowestPriceMonitor(monitorType, Objects.requireNonNull(monitor).getName());
    }
  }
}