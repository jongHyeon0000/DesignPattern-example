package DesignPattern.example.composite;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DungeonDirectory extends Dungeon{
  private List<Dungeon> dungeonList = new ArrayList<Dungeon>();

  DungeonDirectory(String dungeonName, int attackAdventage, int defenceAdvantage, int timeLimit){
    super(dungeonName, attackAdventage, defenceAdvantage, timeLimit);
  }

  public void addDungeon(Dungeon dungeon){
    dungeon.setLevel(level);
    dungeonList.add(dungeon);
  }
  
  public void addDungeon(DungeonDirectory dungeonDirectory){
    dungeonDirectory.setLevel(dungeonDirectory.getLevel() + 1);

    for (Iterator<Dungeon> iter = dungeonDirectory.getIterator(); iter.hasNext();){
      iter.next().setLevel(dungeonDirectory.getLevel());
    }
    dungeonList.add(dungeonDirectory);
  }
  

  public void removeDungeon(Dungeon dungeon){
    dungeonList.remove(dungeon);
  }

  public Iterator<Dungeon> getIterator(){
    return dungeonList.iterator();
  }

  public int getRemainingClearTime(){
    int remainingCrearTime = 0;

    for (Dungeon iter : dungeonList){
      remainingCrearTime += iter.timeLimit;
    }

    return remainingCrearTime;
  }
  
  @Override
  public void Rendering(){
    DungeonDirectoryRendering();

    for (Dungeon iter : dungeonList){
      if (iter instanceof DungeonDirectory){
        System.out.println("!!! DUNGEON LEVEL UP !!!");
        System.out.println();

        iter.Rendering();

        System.out.println("Dungeon Level Down...");
        System.out.println();
      }
      else{
        iter.Rendering();
      }
    }
  }

  public void DungeonDirectoryRendering(){
    System.out.printf("<<----------------- Shelter Name : %s ----------------->>\n", dungeonName);
    System.out.printf("    Map level : %d\n", level);
    System.out.printf("    Monster's Attack Adventage : Safe\n");
    System.out.printf("    Monster's Defence Adventage : Safe\n");
    System.out.printf("    Next Dungeon's Time Limit : %d\n", getRemainingClearTime());
    System.out.println("<<--------------------------------------------------->>\n");
  }
}
