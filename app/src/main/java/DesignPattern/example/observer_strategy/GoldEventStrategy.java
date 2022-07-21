package DesignPattern.example.observer_strategy;

public class GoldEventStrategy extends EventStrategy{
  GoldEventStrategy(String eventName, int eventPeriod, float goldAdditionalScale){
    super(eventName, eventPeriod);
    this.goldAdditionalScale = goldAdditionalScale;
  }
  
  @Override
  public void EventPrint() {
    System.out.println(beginDate + " ~ " + endDate);
    System.out.printf("<< Now %s Gold Eventing.. %d%c more gain >>  ", eventName,  (int)(100 * goldAdditionalScale) - 100, '%');
    System.out.println();
  }
}