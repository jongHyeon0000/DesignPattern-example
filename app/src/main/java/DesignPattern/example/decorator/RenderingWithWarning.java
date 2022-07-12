package DesignPattern.example.decorator;

public class RenderingWithWarning extends HudRenderingDecorator{
  RenderingWithWarning(HudRenderer hudRenderer){
    super(hudRenderer);
  }

  @Override
  public void Rendering(){
    super.Rendering();
    WarningRendererLogic();
  }

  private void WarningRendererLogic(){
    if(super.hudRenderer.getHp().getHpPercentage() <= 20){
      System.out.printf("!!!WARNING!!!");
      System.out.println();
    }
    else if(super.hudRenderer.getHp().getHpPercentage() > 20 && super.hudRenderer.getHp().getHpPercentage() <= 40){
      System.out.printf("!warning!");
      System.out.println();
    }

    if(super.hudRenderer.getMp().getMpPercentage() <= 20){
      System.out.printf("!!!DRINK YOUR MP POTION!!!");
      System.out.println();
    }
    else if(super.hudRenderer.getMp().getMpPercentage() > 20 && super.hudRenderer.getMp().getMpPercentage() <= 40){
      System.out.printf("!drink your mp potion!");
      System.out.println();
    }
  }
}
