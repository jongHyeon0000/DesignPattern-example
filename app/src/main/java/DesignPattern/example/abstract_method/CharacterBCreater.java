package DesignPattern.example.abstract_method;

public class CharacterBCreater extends CharacterCreater{
  @Override
  public Monitor KnifeCreater(int cost, int weight){
    return new AppleMonitor(cost, weight);
  }

  @Override
  public Mouse RifleCreater(int cost, int weight){
    return new AppleMouse(cost, weight);
  }

  @Override
  public Keyboard PistolCreater(int cost, int weight){
    return new SamsungKeyboard(cost, weight);
  }
}
