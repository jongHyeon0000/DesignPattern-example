package DesignPattern.example.observer;

public class Main {
  public static void main(String[] args) {
    ServerNotice serverNotice = new ServerNotice(NoEventStrategy.getInstance("NO EVENT", 10));
    
    PlayerManager serverPlayerAAA = new ServerPlayer("AAA");
    PlayerManager serverPlayerBBB = new ServerPlayer("BBB");
    
    serverNotice.Attach(serverPlayerAAA);
    serverNotice.Attach(serverPlayerBBB);
    
    serverNotice.setEventStrategy(XpEventStrategy.getInstance("OpenEvent", 10, 1.2f));
    
    serverPlayerAAA.GainXp(10);
    serverPlayerAAA.GainGold(100);
    serverPlayerBBB.GainXp(10);
    serverPlayerBBB.GainGold(100);
    
    serverNotice.setEventStrategy(GoldEventStrategy.getInstance("OpenEvent2", 10, 1.4f));
    
    serverPlayerAAA.GainXp(10);
    serverPlayerAAA.GainGold(100);
    serverPlayerBBB.GainXp(10);
    serverPlayerBBB.GainGold(100);
  }
}
