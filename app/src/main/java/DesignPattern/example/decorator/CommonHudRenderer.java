package DesignPattern.example.decorator;

public class CommonHudRenderer extends HudRenderer {

  public CommonHudRenderer(HP hp, MP mp) {
    super(hp, mp);
  }

  @Override
  public void render() {
    System.out.printf("HP : %d  /  %d        MP : %d  /  %d", hp.getMaxHp(), hp.getNowHp(),
        mp.getMaxMp(), mp.getNowMp());
  }
}
