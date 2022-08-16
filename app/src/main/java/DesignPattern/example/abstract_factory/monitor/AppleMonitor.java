package DesignPattern.example.abstract_factory.monitor;

public class AppleMonitor extends Monitor{
  public AppleMonitor(int cost, int weight, int inch){
    super(cost, weight, inch);
  }
  
  @Override
  public String toString() {
    return "I'm AppleMonitor";
  }
}
