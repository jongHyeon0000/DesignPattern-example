package DesignPattern.example.decorator;

public class HudRenderer implements Renderable{
  private Hp hp;
  private Mp mp;
  
  HudRenderer(Hp hp, Mp mp){
    this.hp = hp;
    this.mp = mp;
  }

  @Override
  public void Rendering(){
    System.out.printf("HP : %d / %d    MP : %d / %d",
      hp.getNowHp(), hp.getMaxHp(), mp.getNowMp(), mp.getMaxMp());
  }

  public Hp getHp(){
    return new Hp(hp.getMaxHp(), hp.getNowHp());
  }

  public Mp getMp(){
    return new Mp(mp.getMaxMp(), mp.getNowMp());
  }
}
