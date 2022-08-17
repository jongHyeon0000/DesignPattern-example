package DesignPattern.example.decorator;

public class PercentHudRenderer extends HudDecorator {
  public PercentHudRenderer(HudRenderer hudRenderer) {
    super(hudRenderer);
  }

  @Override
  public void render() {
    super.render();
    percentHudRendering();
  }

  private void percentHudRendering() {
    System.out.println();
    System.out.printf("HP : %s  /  %d        MP : %s  /  %d",
        Integer.toString(hp.getHpPercentage()) + "%", hp.getNowHp(),
        Integer.toString(mp.getMpPercentage()) + "%", mp.getNowMp());
  }
}
