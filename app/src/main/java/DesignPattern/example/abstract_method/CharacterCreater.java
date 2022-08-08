package DesignPattern.example.abstract_method;

abstract public class CharacterCreater {
  abstract public Monitor KnifeCreater(int cost, int weight);
  abstract public Mouse RifleCreater(int cost, int weight);
  abstract public Keyboard PistolCreater(int cost, int weight);
}
