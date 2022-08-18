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
  }

  public String getName() {
    return name;
  }

  public int getCost() {
    return cost;
  }

  public final static class CreateMonitorRank {
    private static Map<Class<? extends Monitor>, Map<String, Integer>> monitorRankingTable =
        new HashMap<Class<? extends Monitor>, Map<String, Integer>>();

    public static void putMonitor(Class<? extends Monitor> monitorType, Monitor monitor) {
      int cost;

      if (Objects.nonNull(cost = monitorRankingTable.get(Objects.requireNonNull(monitorType)).get(monitor.getName()))
          && cost < monitor.getCost()) {
        monitorRankingTable.get(monitorType).put(monitor.getName(), monitor.getCost());
      } 
      else {
        monitorRankingTable.get(monitorType).put(monitor.getName(), monitor.getCost());
      }
    }

    public static Map<String, Integer> getMonitorRankingList(Class<? extends Monitor> monitorType) {
      return monitorRankingTable.get(Objects.requireNonNull(monitorType));
    }

    public static int getMonitorLowestPrice(Class<? extends Monitor> monitorType, Monitor monitor) {
      int lowestPrice;

      if (Objects.nonNull(lowestPrice = monitorRankingTable.get(Objects.requireNonNull(monitorType)).get(monitor.getName()))) {
        return lowestPrice;
      }

      return 0;
    }
  }
}
