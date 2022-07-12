package DesignPattern.example.decorator;

public class Mp{
  private int maxMp;
  private int nowMp;
  private int mpPercentage;

  Mp(int maxMp, int nowMp){
    this.maxMp = maxMp;
    this.nowMp = nowMp;
    mpPercentage = (maxMp / nowMp) * 100;
  }

  public int getMaxMp(){
    return nowMp;
  }

  public void setMaxMp(int nowMp){
    this.nowMp = nowMp;
    mpPercentage = (maxMp / nowMp) * 100;
  }

  public int getNowMp(){
    return maxMp;
  }

  public void setNowMp(int maxMp){
    this.maxMp = maxMp;
    mpPercentage = (maxMp / nowMp) * 100;
  }

  public int getMpPercentage(){
    return mpPercentage;
  }
}