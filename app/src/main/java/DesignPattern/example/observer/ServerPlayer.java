package DesignPattern.example.observer;

public class ServerPlayer extends PlayerManager{
  private EventStrategy eventStrategy;
  
  ServerPlayer(String playerName){
    this.playerName = playerName;
  }
  
  @Override
  public String toString() {
      return playerName;
  }
  
  @Override
  public void Update(EventStrategy eventStrategy) {
    this.eventStrategy = eventStrategy;
  }
  
  @Override
  public void GainXp(float Xp) {
    System.out.printf("%s's %f gained xp...", playerName, Xp * eventStrategy.getXpAdditionalScale());
    
    if(eventStrategy instanceof XpEventStrategy) {
      eventStrategy.EventPrint();
    }
    System.out.println();
  }
  
  @Override 
  public void GainGold(float gold) {
    System.out.printf("%s's %f gained gold...", playerName, gold * eventStrategy.getGoldAdditionalScale());
    
    if(eventStrategy instanceof GoldEventStrategy) {
      eventStrategy.EventPrint();
    }
    
    System.out.println();
  }
}
