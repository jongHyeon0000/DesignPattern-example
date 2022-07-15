package DesignPattern.example.observer;

public class XpEventStrategy extends EventStrategy{
  XpEventStrategy(String eventName, int eventPeriod){
    super(eventName, eventPeriod);
  }
  
  @Override
  public void Event(float additionalScale) {
    XpManager.XpScale = additionalScale;
  }
}
