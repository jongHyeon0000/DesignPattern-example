package DesignPattern.example.decorator;

public class HudDecorator extends HudRenderer {
  private HudRenderer hudRenderer;

  public HudDecorator(HudRenderer hudRenderer) {
    super(hudRenderer.getHp(), hudRenderer.getMp());
    this.hudRenderer = hudRenderer;
  }

  @Override
  public void render() {
    hudRenderer.render();
  }
}
