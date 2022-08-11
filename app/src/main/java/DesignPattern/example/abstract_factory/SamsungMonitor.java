package DesignPattern.example.abstract_factory;

public class SamsungMonitor extends Monitor{
  SamsungMonitor(int cost, int weight, int inch){
    super(cost, weight, inch);
  }

  @Override
  public String toString() {
    return "I'm SamsungMonitor";
  }
}
