package DesignPattern.example.observer_strategy;

public class ServerNotice extends ServerController{
  ServerNotice(String serverName, int maximumUserLimit){
    super(serverName, maximumUserLimit);
    eventStrategy = NoEventStrategy.getInstance("NO EVENT", 0);
  }
  
  public void setEventStrategy(EventStrategy eventStrategy) {
    this.eventStrategy = eventStrategy;
    NotifyObserver();
  }
  
  public EventStrategy getEventStrategy() {
    return eventStrategy;
  }
  
  public String getServerName() {
    return serverName;
  }
  
  public int getMaximumUser() {
    return maximumUserLimit;
  }
  
  public int getNumberOfUser() {
    return noticeObserverList.size();
  }
  
  @Override
  public void Attach(NoticeObserver noticeObserver) {
    noticeObserverList.add(noticeObserver);
    noticeObserver.Update();
  }
  
  @Override
  public void Detach(NoticeObserver noticeObserver) {
    noticeObserverList.remove(noticeObserver);
    noticeObserver.Update();
  }
   
  @Override
  public void NotifyObserver() {
    for(NoticeObserver iter : noticeObserverList) {
      iter.Update();
    }
  }
}