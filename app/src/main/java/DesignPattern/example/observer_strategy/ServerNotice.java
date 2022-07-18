package DesignPattern.example.observer_strategy;

import java.util.ArrayList;
import java.util.List;

public class ServerNotice implements NoticeController{
  private List<NoticeObserver> noticeObserverList = new ArrayList<NoticeObserver>();
  private EventStrategy eventStrategy;
  
  public void setEventStrategy(EventStrategy eventStrategy) {
    this.eventStrategy = eventStrategy;
    NotifyObserver();
  }
  
  @Override
  public void Attach(NoticeObserver noticeObserver) {
    noticeObserverList.add(noticeObserver);
  }
  
  @Override
  public void Detach(NoticeObserver noticeObserver) {
    noticeObserverList.remove(noticeObserver);
  }
  
  @Override
  public void NotifyObserver() {
    for(NoticeObserver iter : noticeObserverList) {
      iter.Update(eventStrategy);
    }
  }
}