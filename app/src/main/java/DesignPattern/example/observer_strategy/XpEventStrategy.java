package DesignPattern.example.observer_strategy;

public class XpEventStrategy extends EventStrategy{
  XpEventStrategy(String eventName, int eventPeriod, float xpAdditionalScale){
    super(eventName, eventPeriod);
    this.xpAdditionalScale = xpAdditionalScale;
  }
  
  @Override
  public void EventPrint() {
    System.out.println(beginDate + " ~ " + endDate);
    System.out.printf("<< Now %s Xp Eventing.. %d%c more gain >>  ", eventName, (int)(100 * xpAdditionalScale) - 100, '%');
    System.out.println();
  }
}
