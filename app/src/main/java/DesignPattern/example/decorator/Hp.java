package DesignPattern.example.decorator;

public class Hp{
  private int maxHp;
  private int nowHp;
  private int hpPercentage;

  Hp(int maxHp, int nowHp){
    this.maxHp = maxHp;
    this.nowHp = nowHp;
    hpPercentage = (maxHp / nowHp) * 100;
  }

  public int getMaxHp(){
    return maxHp;
  }

  public void setMaxHp(int maxHp){
    this.maxHp = maxHp;
    hpPercentage = (maxHp / nowHp) * 100;
  }

  public int getNowHp(){
    return nowHp;
  }

  public void setNowHp(int nowHp){
    this.nowHp = nowHp;
    hpPercentage = (maxHp / nowHp) * 100;
  }

  public int getHpPercentage(){
    return hpPercentage;
  }
}
