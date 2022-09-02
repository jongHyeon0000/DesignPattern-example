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
      Map<String, Integer> concreteMonitorMap = lowestPriceMonitorMap.get(monitor.getClass());

      if (Objects.nonNull(concreteMonitorMap)) {
        if (Objects.nonNull(concreteMonitorMap.get(monitor.getName()))) {
          if (concreteMonitorMap.get(monitor.getName()) > monitor.getCost()) {
            concreteMonitorMap.put(monitor.getName(), monitor.getCost());
          }
        } else {
          concreteMonitorMap.put(monitor.getName(), monitor.getCost());
        }
      } else {
        Map<String, Integer> map = new HashMap<>();
        map.put(monitor.getName(), monitor.getCost());

        lowestPriceMonitorMap.put(monitor.getClass(), map);
      }
    }

    public static final Map<String, Integer> getLowestPriceMonitorMap(
        Class<? extends Monitor> monitorType) {
      return (Objects.nonNull(lowestPriceMonitorMap.get(Objects.requireNonNull(monitorType))))
          ? lowestPriceMonitorMap.get(monitorType)
          : Collections.emptyMap();
    }

    public static final int getLowestPriceMonitor(Class<? extends Monitor> monitorType) {
      return getLowestPriceMonitorMap(monitorType).values().stream()
          .min((i1, i2) -> i1.compareTo(i2)).orElseThrow();
    }
  }
}
