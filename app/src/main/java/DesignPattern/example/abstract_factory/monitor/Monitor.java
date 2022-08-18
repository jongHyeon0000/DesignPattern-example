package DesignPattern.example.abstract_factory.monitor;

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
    
    LowestPriceMonitorManager.putMonitor(this.getClass(), this);
  }

  public String getName() {
    return name;
  }

  public int getCost() {
    return cost;
  }

  public final static class LowestPriceMonitorManager {
    private static Map<Class<? extends Monitor>, Map<String, Integer>> monitorTable =
        new HashMap<Class<? extends Monitor>, Map<String, Integer>>();

    public static void putMonitor(Class<? extends Monitor> monitorType, Monitor monitor) {
      Map<String, Integer> concreteMonitorTable;

      if (Objects.nonNull(concreteMonitorTable = monitorTable.get(Objects.requireNonNull(monitorType)))) {
        int lowestPrice;
        
        if(Objects.nonNull(lowestPrice = concreteMonitorTable.get(monitor.getName()))){
          if(lowestPrice > monitor.getCost()) {
            concreteMonitorTable.put(monitor.getName(), monitor.getCost());
          }
        }
        else {
          concreteMonitorTable.put(monitor.getName(), monitor.getCost());
        }
      }
      else {
        Map<String, Integer> map = new HashMap<>();
        map.put(monitor.getName(), monitor.getCost());
        
        monitorTable.put(monitorType, map);
      }
    }

    public static Map<String, Integer> getMonitorLowestPriceList(Class<? extends Monitor> monitorType) {
      return monitorTable.get(Objects.requireNonNull(monitorType));
    }

    public static int getMonitorLowestPrice(Class<? extends Monitor> monitorType, Monitor monitor) {
      int lowestPrice;

      if (Objects.nonNull(lowestPrice =
          monitorTable.get(Objects.requireNonNull(monitorType)).get(monitor.getName()))) {
        return lowestPrice;
      }

      return 0;
    }
  }
}
