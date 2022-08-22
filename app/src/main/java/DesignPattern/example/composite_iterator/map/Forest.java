package DesignPattern.example.composite_iterator.map;

public class Forest extends Dungeon{
  public Forest(String dungeonName, int attackAdventage, int defenceAdvantage, int timeLimit){
    super(dungeonName, attackAdventage, defenceAdvantage, timeLimit);
  }

  @Override
  public void Rendering(){
    System.out.printf("<<----------------- Map Name : %s ----------------->>\n", dungeonName);
    System.out.printf("    Map level : %d\n", level);
    System.out.printf("    Monster's Attack Adventage: %d%c\n", attackAdvantage, '%');
    System.out.printf("    Monster's Defence Adventage : %d%c\n", defenceAdvantage, '%');
    System.out.printf("    Time Limit : %d\n", timeLimit);
    System.out.println("    ...Dense forest");
    System.out.println("<<--------------------------------------------------->>\n");
  }
}
