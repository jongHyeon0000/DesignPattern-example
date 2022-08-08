package DesignPattern.example.abstract_method;

public class CharacterACreater extends CharacterCreater{
  @Override
  public Monitor KnifeCreater(int cost, int weight){
    return new SamsungMonitor(cost, weight);
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
