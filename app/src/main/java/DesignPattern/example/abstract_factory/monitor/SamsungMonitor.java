package DesignPattern.example.abstract_factory.monitor;

public class SamsungMonitor extends Monitor{
  public SamsungMonitor(int cost, int weight, int inch){
    super(cost, weight, inch);
  }

  @Override
  public String toString() {
    return "I'm SamsungMonitor";
  }
}
