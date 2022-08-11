package DesignPattern.example.abstract_factory;

public class AppleMonitor extends Monitor{
  AppleMonitor(int cost, int weight, int inch){
    super(cost, weight, inch);
  }
  
  @Override
  public String toString() {
    return "I'm AppleMonitor";
  }
}
