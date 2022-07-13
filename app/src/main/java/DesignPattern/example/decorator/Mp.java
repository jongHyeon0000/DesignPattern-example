package DesignPattern.example.decorator;

public class Mp{
  private int maxMp;
  private int nowMp;

  Mp(int maxMp, int nowMp){
    if(maxMp >= nowMp){
      this.maxMp = maxMp;
      this.nowMp = nowMp;
    }
  }

  public int getMaxMp(){
    return maxMp;
  }

  public void setMaxMp(int maxMp){
    this.maxMp = maxMp;
  }

  public int getNowMp(){
    return nowMp;
  }

  public void setNowMp(int nowMp){
    this.nowMp = nowMp;
  }

  public float getMpPercentage(){
    return (nowMp / (float)maxMp) * 100;
  }
}