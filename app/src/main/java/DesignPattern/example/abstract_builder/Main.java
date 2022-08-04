package DesignPattern.example.abstract_builder;

public class Main {
  public static void main(String[] args) {
    Monster monster = new Monster1.Builder("monster", MonsterType.TYPE1)
         .setCost(10).setHelper(true).setMonsterModifier(MonsterModifier.MODIFIER2).Build();
    
    monster.toString();
  }
}
