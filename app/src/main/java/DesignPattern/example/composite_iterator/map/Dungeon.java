package DesignPattern.example.composite_iterator.map;

import DesignPattern.example.composite_iterator.rendering.Renderable;

abstract public class Dungeon implements Renderable{  
  protected String dungeonName;
  protected int level;
  protected int attackAdvantage;
  protected int defenceAdvantage;
  protected int timeLimit;

  public Dungeon(String dungeonName, int attackAdventage, int defenceAdvantage, int timeLimit){
    this.dungeonName = dungeonName;
    this.level = 1;
    this.attackAdvantage = attackAdventage;
    this.defenceAdvantage = defenceAdvantage;
    this.timeLimit = timeLimit;
  }
  
  public int getLevel(){
    return level;
  }

  public void setLevel(int level){
    this.level = level;
  }

  @Override
  public void Rendering(){
    System.out.printf("<<----------------- Map Name : %s ----------------->>\n", dungeonName);
    System.out.printf("    Map level : %d\n", level);
    System.out.printf("    Monster's Attack Adventage: %d%c\n", attackAdvantage, '%');
    System.out.printf("    Monster's Defence Adventage : %d%c\n", defenceAdvantage, '%');
    System.out.printf("    Time Limit : %d\n", timeLimit);
    System.out.println("<<--------------------------------------------------->>\n");
  }
}