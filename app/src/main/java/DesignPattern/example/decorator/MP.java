package DesignPattern.example.decorator;

public class MP {
  private int maxMp;
  private int nowMp;

  public MP(int maxMp, int nowMp) {
    if (maxMp < nowMp && maxMp <= 0 && nowMp < 0) {
      throw new IllegalArgumentException();
    }

    this.maxMp = maxMp;
    this.nowMp = nowMp;
  }

  public int getMaxMp() {
    return maxMp;
  }

  public int getNowMp() {
    return nowMp;
  }
  
  public int getMpPercentage() {
    return Math.round((nowMp / (float) maxMp) * 100);
  }
}
