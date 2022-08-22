package DesignPattern.example.composite_iterator.rendering;

import DesignPattern.example.composite_iterator.map.Dungeon;

public class Renderer {
  public static void rendering(Dungeon dungeon) {
    mapRendering(dungeon);
  }
  
  private static void mapRendering(Dungeon dungeon) {    
    dungeon.Rendering();
  }
}
