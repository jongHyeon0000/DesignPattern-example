package DesignPattern.example.observer;

public class GoldEventStrategy extends EventStrategy{
  GoldEventStrategy(String eventName, int eventPeriod){
    super(eventName, eventPeriod);
  }
  
  @Override
  public void Event(float additionalScale) {
    GoldManager.goldScale = additionalScale;
  }
}
