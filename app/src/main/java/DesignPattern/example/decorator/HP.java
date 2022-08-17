package DesignPattern.example.decorator;

public class HP {
  private int maxHp;
  private int nowHp;

  public HP(int maxHp, int nowHp) {
    if (maxHp < nowHp && maxHp <= 0 && nowHp < 0) {
      throw new IllegalArgumentException();
    }

    this.maxHp = maxHp;
    this.nowHp = nowHp;
  }

  public int getMaxHp() {
    return maxHp;
  }

  public int getNowHp() {
    return nowHp;
  }
  
  public int getHpPercentage() {
    return Math.round((nowHp / (float) maxHp) * 100);
  }
}
