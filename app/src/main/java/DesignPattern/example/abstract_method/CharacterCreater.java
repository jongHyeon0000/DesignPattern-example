package DesignPattern.example.abstract_method;

abstract public class CharacterCreater {
  abstract public Knife KnifeCreater(int cost, int weight);
  abstract public Rifle RifleCreater(int cost, int weight);
  abstract public Pistol PistolCreater(int cost, int weight);
}
