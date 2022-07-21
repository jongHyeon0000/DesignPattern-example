package DesignPattern.example.observer_strategy;

import java.util.ArrayList;
import java.util.List;

abstract class ServerController {
  protected String serverName;
  protected final int MAXIMUM_USER_LIMIT;
  
  protected List<NoticeObserver> noticeObserverList = new ArrayList<NoticeObserver>();
  protected EventStrategy eventStrategy;
  
  ServerController(String serverName, int maximumUserLimit){
    this.serverName = serverName;
    this.MAXIMUM_USER_LIMIT = maximumUserLimit;
  }
  
  abstract public void Init(NoticeObserver noticeObserver);
  abstract public void setEventStrategy(EventStrategy eventStrategy);

  abstract protected void Attach(NoticeObserver noticeObserver);
  abstract protected void Detach(NoticeObserver noticeObserver);
  abstract protected void NotifyObserver();
  
  public EventStrategy getEventStrategy() {
    return eventStrategy;
  }
  public String getServerName() {
    return serverName;
  }
  public int getMaximumUser() {
    return MAXIMUM_USER_LIMIT;
  }
  public int getNumberOfUser() {
    return noticeObserverList.size();
  }
}
