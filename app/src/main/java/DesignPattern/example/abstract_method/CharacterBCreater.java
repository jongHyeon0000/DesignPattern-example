package DesignPattern.example.abstract_method;

public class CharacterBCreater extends CharacterCreater{
  @Override
  public Knife KnifeCreater(int cost, int weight){
    return new UsKnife(cost, weight);
  }

  @Override
  public Rifle RifleCreater(int cost, int weight){
    return new UsRifle(cost, weight);
  }

  @Override
  public Pistol PistolCreater(int cost, int weight){
    return new KoreaPistol(cost, weight);
  }
}
