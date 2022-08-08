package DesignPattern.example.composite_iterator;

public class Renderer {
  public static void rendering(DungeonDirectory<?> dungeonDirectory) {
    mapRendering(dungeonDirectory);
  }
  
  private static void mapRendering(Dungeon dungeonDirectory) {    
    dungeonDirectory.Rendering();
  }
}
