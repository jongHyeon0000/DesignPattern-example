package DesignPattern.example.composite_iterator;

import DesignPattern.example.composite_iterator.iterable.Iterator;
import DesignPattern.example.composite_iterator.map.Cave;
import DesignPattern.example.composite_iterator.map.Dungeon;
import DesignPattern.example.composite_iterator.map.DungeonDirectory;
import DesignPattern.example.composite_iterator.map.Forest;
import DesignPattern.example.composite_iterator.map.Mountain;
import DesignPattern.example.composite_iterator.map.Swamp;
import DesignPattern.example.composite_iterator.rendering.Renderer;

public class Main {
  public static void main(String[] args) {
    DungeonDirectory<Dungeon> dungeonA = new DungeonDirectory<>("Crossroads 1", 0, 0, 0);
    DungeonDirectory<Dungeon> dungeonB = new DungeonDirectory<>("Crossroads 2", 0, 0, 0);

    dungeonA.addDungeon(new Cave("Cave 1", 10, 20, 20));
    dungeonA.addDungeon(new Forest("Forest 1-1", 20, 40, 20));

    dungeonB.addDungeon(new Mountain("Mountain 2", 30, 20, 90));
    dungeonB.addDungeon(new Swamp("Swamp 2", 40, 25, 60));

    dungeonA.addDungeon(dungeonB);
    dungeonA.addDungeon(new Mountain("Maountain 1", 30, 30, 20));
    dungeonA.addDungeon(new Forest("Forest1-2", 35, 10, 5));
    dungeonB.addDungeon(new Cave("Cave 2", 60, 35, 110));

    Renderer.rendering(dungeonA);
    
    for(Iterator<? extends Dungeon> iter = dungeonB.getIterator(); iter.hasNext();) {
      iter.next().Rendering();
    }
  }
}
