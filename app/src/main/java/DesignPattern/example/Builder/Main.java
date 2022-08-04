package DesignPattern.example.Builder;

public class Main {
  public static void main(String[] args) {
     Monster monster = new Monster.Builder("monster", MonsterType.TYPE1)
         .setCost(10).setHelper(true).setMonsterModifier(MonsterModifier.MODIFIER2).Build();
     
     System.out.println(monster);
  }
}
