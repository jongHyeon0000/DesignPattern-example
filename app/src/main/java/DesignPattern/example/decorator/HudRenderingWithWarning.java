package DesignPattern.example.decorator;

public class HudRenderingWithWarning extends HudRenderingDecorator{
  HudRenderingWithWarning(HudRenderer hudRenderer){
    super(hudRenderer);
  }

  @Override
  public void Rendering(){
    super.Rendering();
    WarningRendererLogic();
  }

  private void WarningRendererLogic(){
    if(hudRenderer.getHp().getHpPercentage() <= 20){
      System.out.printf("!!!WARNING!!!");
    }
    else if(hudRenderer.getHp().getHpPercentage() > 20 && hudRenderer.getHp().getHpPercentage() <= 40){
      System.out.printf("!warning!");
    }

    if(hudRenderer.getMp().getMpPercentage() <= 20){
      System.out.printf("!!!DRINK YOUR MP POTION!!!");
    }
    else if(hudRenderer.getMp().getMpPercentage() > 20 && hudRenderer.getMp().getMpPercentage() <= 40){
      System.out.printf("!drink your mp potion!");
    }
    
    System.out.println();
  }
}
