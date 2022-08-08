package DesignPattern.example.composite_iterator;
import java.util.ArrayList;
import java.util.List;

public class DungeonDirectory<T extends Dungeon> extends Dungeon{
  private List<T> dungeonList = new ArrayList<>();

  DungeonDirectory(String dungeonName, int attackAdventage, int defenceAdvantage, int timeLimit){
    super(dungeonName, attackAdventage, defenceAdvantage, timeLimit);
  }

  public void addDungeon(T dungeon){
    dungeon.setLevel(level);
    dungeonList.add(dungeon);
  }
  
  @SuppressWarnings("unchecked")
  public void addDungeon(DungeonDirectory<T> dungeonDirectory){
    dungeonDirectory.setLevel(dungeonDirectory.getLevel() + 1);
    
    for(Dungeon iter : dungeonDirectory.dungeonList) {
      iter.setLevel(dungeonDirectory.getLevel());
    }
    
    dungeonList.add( (T) dungeonDirectory);
  }

  public void removeDungeon(Dungeon dungeon){
    dungeonList.remove(dungeon);
  }

  public int getRemainingClearTime(){
    int remainingCrearTime = 0;

    for (Dungeon iter : dungeonList){
      remainingCrearTime += iter.timeLimit;
    }

    return remainingCrearTime;
  }
  
  public Iterator<T> getIterator() {
    return new DungeonIterator(dungeonList);
  }
  
  @Override
  public void Rendering(){
    System.out.printf("<<----------------- Shelter Name : %s ----------------->>\n", dungeonName);
    System.out.printf("    Map level : %d\n", level);
    System.out.printf("    Monster's Attack Adventage : Safe\n");
    System.out.printf("    Monster's Defence Adventage : Safe\n");
    System.out.printf("    Next Dungeon's Time Limit : %d\n", getRemainingClearTime());
    System.out.println("<<--------------------------------------------------->>\n");
    
    for(Dungeon iter : dungeonList) {
      iter.Rendering();
    }
  }

  public class DungeonIterator implements Iterator<T>{
    private List<T> iteratorDungeonList = new ArrayList<>();
    private int index = 0;
    
    DungeonIterator(List<T> dungeonList){
      iteratorDungeonList = new ArrayList<>(dungeonList);
    }
    
    @Override
    public boolean hasNext() {
      return (index < iteratorDungeonList.size()) ? true : false;
    }

    @Override
    public T next() {
      return iteratorDungeonList.get(index++);
    }
  }
}
