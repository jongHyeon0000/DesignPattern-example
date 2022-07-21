package DesignPattern.example.observer_strategy;

public class ServerPlayer implements NoticeObserver{
  private String serverName;
  private int maximumUserLimit;
  private int numberOfUser;
  
  private String playerName;
  private ServerNotice serverNotice;
  
  ServerPlayer(String playerName, ServerNotice serverNotice){
    this.playerName = playerName;
    this.serverNotice = serverNotice;
  }
  
  @Override
  public String toString() {
      return playerName;
  }
  
  @Override
  public void Update() {
    serverName = serverNotice.getServerName();
    maximumUserLimit = serverNotice.getMaximumUser();
    numberOfUser = serverNotice.getNumberOfUser();
  }
  
  public void Init() {
    serverNotice.Init(this);
    
    /*
     * Init Logic...
     */
    
    System.out.printf("<----- Welcome to %s server! %d / %d ----->\n", serverName, numberOfUser, maximumUserLimit);
    System.out.println();
  }
  
  public void Rendering(float xp, float gold) {
    serverNotice.getEventStrategy().EventPrint();
    
    GainXpRendering(xp);
    GainGoldRendering(gold);
    
    System.out.println();
  }
  
  public void GainXpRendering(float xp) {
    if(xp > 0) {
      System.out.printf("%s's %f gained xp...", playerName, xp * serverNotice.getEventStrategy().getXpAdditionalScale());
      System.out.println();
    }
  }
   
  public void GainGoldRendering(float gold) {
    if(gold > 0) {
      System.out.printf("%s's %f gained gold...", playerName, gold * serverNotice.getEventStrategy().getGoldAdditionalScale());
      System.out.println();
    }
  }
  
}
