package DesignPattern.example.observer_strategy;

public class ServerNotice extends ServerController{
  ServerNotice(String serverName, int maximumUserLimit){
    super(serverName, maximumUserLimit);
    eventStrategy = NoEventStrategy.getInstance("NO EVENT", 0);
  }
  
  @Override
  public void setEventStrategy(EventStrategy eventStrategy) {
    this.eventStrategy = eventStrategy;
    NotifyObserver();
  }
  
  @Override
  public void Init(NoticeObserver noticeObserver) {
    Attach(noticeObserver);
    
    /*
     * 
     * Init Logic...
     * 
     */
    
    noticeObserver.Update();
  }
  
  @Override
  protected void Attach(NoticeObserver noticeObserver) {
    noticeObserverList.add(noticeObserver);
  }
  
  @Override
  public void Detach(NoticeObserver noticeObserver) {
    noticeObserverList.remove(noticeObserver);
    noticeObserver.Update();
  }
   
  @Override
  protected void NotifyObserver() {
    for(NoticeObserver iter : noticeObserverList) {
      iter.Update();
    }
  }
}