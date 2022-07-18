package DesignPattern.example.observer;

public class XpEventStrategy extends EventStrategy{
  private XpEventStrategy(String eventName, int eventPeriod){
    super(eventName, eventPeriod);
  }
  
  public static XpEventStrategy getInstance(String eventName, int eventPeriod, float xpAdditionalScale) {
    XpEventStrategy xpEventStrategy = new XpEventStrategy(eventName, eventPeriod);
    xpEventStrategy.xpAdditionalScale = xpAdditionalScale;
    
    return xpEventStrategy;
  }
  
  @Override
  public void EventPrint() {
    System.out.printf("<< Now Xp Eventing.. %d%c more gain >>  ", (int)(100 * xpAdditionalScale) - 100, '%');
  }
}
