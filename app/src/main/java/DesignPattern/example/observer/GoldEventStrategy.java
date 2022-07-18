package DesignPattern.example.observer;

public class GoldEventStrategy extends EventStrategy{
  private GoldEventStrategy(String eventName, int eventPeriod){
    super(eventName, eventPeriod);
  }
  
  public static GoldEventStrategy getInstance(String eventName, int eventPeriod, float goldAdditionalScale) {
    GoldEventStrategy goldEventStrategy = new GoldEventStrategy(eventName, eventPeriod);
    goldEventStrategy.goldAdditionalScale = goldAdditionalScale;
    
    return goldEventStrategy;
  }
  
  @Override
  public void EventPrint() {
    System.out.printf("<< Now Gold Eventing.. %d%c more gain >>  ", (int)(100 * goldAdditionalScale) - 100, '%');
  }
}