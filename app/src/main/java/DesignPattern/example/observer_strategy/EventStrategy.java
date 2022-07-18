package DesignPattern.example.observer_strategy;
import java.time.LocalDate;

abstract public class EventStrategy {
  private LocalDate beginDate;
  private LocalDate endDate;
  protected String eventName;
  protected float xpAdditionalScale;
  protected float goldAdditionalScale;
  
  protected EventStrategy(String eventName, int eventPeriod){
    this.eventName = eventName;
    xpAdditionalScale = 1.0f;
    goldAdditionalScale = 1.0f;
    
    beginDate = LocalDate.now();
    endDate = LocalDate.now();
    
    endDate.plusDays(eventPeriod);
  }
  
  public float getXpAdditionalScale() {
    return xpAdditionalScale;
  }
  
  public float getGoldAdditionalScale() {
    return goldAdditionalScale;
  }
  
  abstract public void EventPrint();
}
