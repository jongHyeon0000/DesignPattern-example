package DesignPattern.example.decorator;

public class Main {
  public static void main(String[] args) {
    Renderable hudRenderer = new CommonHudRenderer(new HP(1000, 100), new MP(2000, 540));
    hudRenderer.render();
    
    System.out.printf("\n\n");
    
    hudRenderer = new PercentHudRenderer(new CommonHudRenderer(new HP(1000, 100), new MP(2000, 540)));
    hudRenderer.render();
    
    System.out.printf("\n\n");
    
    hudRenderer = new WarningHudRenderer(new CommonHudRenderer(new HP(1000, 100), new MP(2000, 540)));
    hudRenderer.render();
  }
}
