package DesignPattern.example.decorator;

public class HudRenderingDecorator extends HudRenderer{
  protected HudRenderer hudRenderer;
  
  HudRenderingDecorator(HudRenderer hudRenderer){
    super(hudRenderer.getHp(), hudRenderer.getMp());
    this.hudRenderer = hudRenderer;
  }
  
  @Override
  public void Rendering(){
    hudRenderer.Rendering();
  }
}
