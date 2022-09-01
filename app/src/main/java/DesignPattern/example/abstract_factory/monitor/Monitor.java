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

  public static final class LowestPriceMonitorManager {
    private static final Map<Class<? extends Monitor>, Map<String, Integer>> lowestPriceMonitorMap =
        new HashMap<Class<? extends Monitor>, Map<String, Integer>>();

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

    public static final int getLowestPriceMonitor(Class<? extends Monitor> monitorType) {
      return getLowestPriceMonitorMap(monitorType).values().stream().min((i1, i2) -> i1.compareTo(i2)).orElseThrow();
    }
  }
}