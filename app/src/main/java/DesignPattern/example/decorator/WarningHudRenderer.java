package DesignPattern.example.decorator;

public class WarningHudRenderer extends HudDecorator{

  public WarningHudRenderer(HudRenderer hudRenderer) {
    super(hudRenderer);
  }
  
  @Override
  public void render() {
    super.render();
    warningHudRendering();
  }
  
  private void warningHudRendering() {
    if(hp.getHpPercentage() < 20) {
      System.out.println();
      System.out.println("     -----HP WARNING-----");
    }
    
    if(mp.getMpPercentage() < 20) {
      System.out.println();
      System.out.println("     -----MP WARNING-----");
    }
  }
}
