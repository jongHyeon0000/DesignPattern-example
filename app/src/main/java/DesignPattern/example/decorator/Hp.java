package DesignPattern.example.decorator;

public class Hp{
  private int maxHp;
  private int nowHp;

  Hp(int maxHp, int nowHp){
    if(maxHp >= nowHp){
      this.maxHp = maxHp;
      this.nowHp = nowHp;
    }
  }

  public int getMaxHp(){
    return maxHp;
  }

  public void setMaxHp(int maxHp){
    this.maxHp = maxHp;
  }

  public int getNowHp(){
    return nowHp;
  }

  public void setNowHp(int nowHp){
    this.nowHp = nowHp;
  }

  public float getHpPercentage(){
    return (nowHp / (float)maxHp) * 100;
  }
}
