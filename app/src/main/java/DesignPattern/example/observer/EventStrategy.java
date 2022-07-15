package DesignPattern.example.observer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

abstract public class EventStrategy {
  private String eventName;
  private LocalDate beginDate;
  private LocalDate endDate;
  
  EventStrategy(String eventName, int eventPeriod){
    this.eventName = eventName;
    
    beginDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    endDate.plusDays(eventPeriod);
  }
  
  abstract public void Event(float additionalScale);
}
