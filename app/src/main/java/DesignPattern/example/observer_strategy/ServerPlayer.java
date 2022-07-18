package DesignPattern.example.observer_strategy;

public class ServerPlayer implements NoticeObserver{
  private EventStrategy eventStrategy;
  private String playerName;
  private float xpAdditionalScale;
  private float goldAdditionalScale;
  
  ServerPlayer(String playerName){
    this.playerName = playerName;
    eventStrategy = NoEventStrategy.getInstance("NO EVENT", 0);
  }
  
  @Override
  public String toString() {
      return playerName;
  }
  
  @Override
  public void Update(EventStrategy eventStrategy) {
    this.eventStrategy = eventStrategy;
  }
  
  public void Rendering(float xp, float gold) {
    eventStrategy.EventPrint();
    
    GainXpRendering(xp);
    GainGoldRendering(gold);
    
    System.out.println();
  }
  
  public void GainXpRendering(float xp) {
    if(xp > 0) {
      System.out.printf("%s's %f gained xp...", playerName, xp * eventStrategy.getXpAdditionalScale());
      System.out.println();
    }
  }
   
  public void GainGoldRendering(float gold) {
    if(gold > 0) {
      System.out.printf("%s's %f gained gold...", playerName, gold * eventStrategy.getGoldAdditionalScale());
      System.out.println();
    }
  }
  
}
