package DesignPattern.example.abstract_factory.monitor;

public class AppleMonitor extends Monitor {
  public AppleMonitor(String name, int cost, int weight, int inch) {
    super(name, cost, weight, inch);
  }

  @Override
  public String toString() {
    return "I'm AppleMonitor";
  }
}