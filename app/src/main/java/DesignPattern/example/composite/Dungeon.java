package DesignPattern.example.composite;

abstract public class Dungeon implements Renderable{
  protected enum DUNGERON_MODIFIER{
    
  }
  
  private int level;
  private int attackAdvantage;
  private int defenceAdvantage;
  private  int timeLimit;

  Dungeon(int level, int attackAdventage, int defenceAdvantage, int timeLimit){
    this.level = level;
    this.attackAdvantage = attackAdventage;
    this.defenceAdvantage = defenceAdvantage;
    this.timeLimit = timeLimit;
  }

  @Override
  public void Rendering(){
    System.out.printf("Map level : %d", level);
    System.out.printf("Monster's Attack Adventage: %d%c", attackAdvantage, '%');
    System.out.printf("Monster's Defence Adventage : %d%c", defenceAdvantage, '%');
    System.out.printf("Time Limit : %d", timeLimit);
  }
}
