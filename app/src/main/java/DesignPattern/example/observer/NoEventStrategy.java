package DesignPattern.example.observer;

public class NoEventStrategy extends EventStrategy{
  private static NoEventStrategy noEventStrategy;
  
  private NoEventStrategy(String eventName, int eventPeriod){
    super(eventName, eventPeriod);
  }
  
  public static NoEventStrategy getInstance(String eventName, int eventPeriod) {
    if(noEventStrategy == null) {
      noEventStrategy = new NoEventStrategy(eventName, eventPeriod);
      return noEventStrategy;
    }
    else {
      return noEventStrategy;
    }
  }

  @Override
  public void EventPrint() {
    // TODO Auto-generated method stub
    
  }
}
