package DesignPattern.example.observer_strategy;

import java.util.ArrayList;
import java.util.List;

abstract class ServerController {
  protected String serverName;
  protected int maximumUserLimit;
  
  protected List<NoticeObserver> noticeObserverList = new ArrayList<NoticeObserver>();
  protected EventStrategy eventStrategy;
  
  ServerController(String serverName, int maximumUserLimit){
    this.serverName = serverName;
    this.maximumUserLimit = maximumUserLimit;
  }
  
  abstract protected void Attach(NoticeObserver noticeObserver);
  abstract protected void Detach(NoticeObserver noticeObserver);
  abstract protected void NotifyObserver();
}
