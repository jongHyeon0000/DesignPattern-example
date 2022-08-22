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

  public static final class LowestPriceMonitorManager {
    private static final Map<Class<? extends Monitor>, Map<String, Integer>> lowestPriceMonitorMap =
        new HashMap<Class<? extends Monitor>, Map<String, Integer>>();

    private static final void putMonitor(Class<? extends Monitor> monitorType, Monitor monitor) {
      monitorType = Objects.requireNonNull(monitorType);
      monitor = Objects.requireNonNull(monitor);
      
      Map<String, Integer> concreteMonitorMap;
      
      if (Objects.nonNull(concreteMonitorMap = lowestPriceMonitorMap.get(monitorType))) {        
        if(Objects.nonNull(concreteMonitorMap.get(monitor.getName()))){
          int lowestPrice = concreteMonitorMap.get(monitor.getName());
          
          if(lowestPrice > monitor.getCost()) {
            concreteMonitorMap.put(monitor.getName(), monitor.getCost());
          }
        }
        else {
          concreteMonitorMap.put(monitor.getName(), monitor.getCost());
        }
      }
      else {
        Map<String, Integer> map = new HashMap<>();
        map.put(monitor.getName(), monitor.getCost());
        
        lowestPriceMonitorMap.put(monitorType, map);
      }
    }

    public static final Map<String, Integer> getLowestPriceMonitorMap(Class<? extends Monitor> monitorType) {
      Map<String, Integer> concreteMonitorMap;
      
      if(Objects.nonNull(concreteMonitorMap = lowestPriceMonitorMap.get(Objects.requireNonNull(monitorType)))) {
        return concreteMonitorMap;
      }
      
      throw new IllegalStateException("해당 회사 제품이 없습니다.");
    }
    
    public static final int getLowestPriceMonitor(Class<? extends Monitor> monitorType, String monitorName) {
      int lowestPrice;

      if (Objects.nonNull(lowestPrice = getLowestPriceMonitorMap(monitorType).get(monitorName))) {
        return lowestPrice;
      }

      throw new IllegalStateException(String.format("%s 제품이 없습니다.", monitorName));
    }
    
    public static final int getLowestPriceMonitor(Class<? extends Monitor> monitorType, Monitor monitor) {
      return getLowestPriceMonitor(monitorType, Objects.requireNonNull(monitor).getName());
    }
  }
}
