package DesignPattern.example.decorator;

public class HudRenderingWithPercent extends HudRenderingDecorator{
  HudRenderingWithPercent(HudRenderer hudRenderer){
    super(hudRenderer);
  }

  @Override
  public void Rendering(){
    super.Rendering();
    PercentRenderingLogic();
  }

  private void PercentRenderingLogic(){
    System.out.println();
    System.out.printf("( %f%c )    ( %f%c )",
      super.hudRenderer.getHp().getHpPercentage(), '%',
        super.hudRenderer.getMp().getMpPercentage(), '%');
  }
}
