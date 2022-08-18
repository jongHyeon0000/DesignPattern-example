package DesignPattern.example.abstract_factory.monitor;

public class SamsungMonitor extends Monitor{
  public SamsungMonitor(String name, int cost, int weight, int inch){
    super(name , cost, weight, inch);
  }

  @Override
  public String toString() {
    return "I'm SamsungMonitor";
  }
}
