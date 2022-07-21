package DesignPattern.example.observer_strategy;

public class NoEventStrategy extends EventStrategy{
  private static NoEventStrategy noEventStrategy = new NoEventStrategy("No Event..", 0);
  
  private NoEventStrategy(String eventName, int eventPeriod){
    super(eventName, eventPeriod);
  }
  
  public static NoEventStrategy getInstance(String eventName, int eventPeriod) {
    return noEventStrategy;
  }

  @Override
  public void EventPrint() {
    // TODO Auto-generated method stub
    
  }
}
