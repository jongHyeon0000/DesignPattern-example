package DesignPattern.example.observer_strategy;

public class Main {
  public static void main(String[] args) {
    ServerNotice serverNotice = new ServerNotice();   
    
    ServerPlayer serverPlayerAAA = new ServerPlayer("AAA");
    ServerPlayer serverPlayerBBB = new ServerPlayer("BBB");
    
    serverNotice.Attach(serverPlayerAAA);
    serverNotice.Attach(serverPlayerBBB);
    
    serverNotice.setEventStrategy(XpEventStrategy.getInstance("OpenEvent", 10, 1.2f));
    
    // Monster Hunting Logic...
    serverPlayerAAA.Rendering(10.0f, 100.0f);
    // Monster Hunting Logic...
    serverPlayerBBB.Rendering(15.0f, 130.0f);
    
    serverNotice.setEventStrategy(GoldEventStrategy.getInstance("OpenEvent2", 10, 1.4f));
    
    // Monster Hunting Logic...
    serverPlayerAAA.Rendering(20.0f, 200.0f);
    // Monster Hunting Logic...
    serverPlayerBBB.Rendering(40.0f, 250.0f);
  }
}
