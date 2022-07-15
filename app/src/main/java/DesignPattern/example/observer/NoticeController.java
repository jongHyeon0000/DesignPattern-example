package DesignPattern.example.observer;
import java.util.ArrayList;
import java.util.List;

abstract public class NoticeController {
  private List<NoticeObserver> noticeObserverList = new ArrayList<NoticeObserver>();
  private EventStrategy eventStrategy;
  
  NoticeController(EventStrategy eventStrategy){
    this.eventStrategy = eventStrategy;
  }
  
  public void setEventStrategy(EventStrategy eventStrategy) {
    NotifyObserver();
    this.eventStrategy = eventStrategy;
  }
  
  EventStrategy getEventStrategy(EventStrategy eventStrategy) {
    return eventStrategy;
  }
  
  public void Attach(NoticeObserver noticeObserver) {
    noticeObserverList.add(noticeObserver);
  }
  
  public void Detach(NoticeObserver noticeObserver) {
    noticeObserverList.remove(noticeObserver);
  }
  
  protected void NotifyObserver() {
    for(NoticeObserver iter : noticeObserverList) {
      iter.Update(eventStrategy);
    }
  }
  
  abstract protected void sendMessage(int state);
}
