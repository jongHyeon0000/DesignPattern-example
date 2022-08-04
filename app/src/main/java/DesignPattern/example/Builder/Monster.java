package DesignPattern.example.Builder;

enum MonsterType{
  TYPE1, TYPE2, TYPE3
}

enum MonsterModifier{
  MODIFIER1, MODIFIER2, MODIFIER3
}

public class Monster {  
  private Monster(Builder builder){
    this.name = builder.name;
    this.monsterType = builder.monsterType;
    this.cost = builder.cost;
    this.helper = builder.helper;
    this.monsterModifier = builder.monsterModifier;
  }
  
  private String name;
  private MonsterType monsterType;
  
  private int cost;
  private boolean helper;
  private MonsterModifier monsterModifier;
  
  @Override
  public String toString() {
    return "Monster [name=" + name + ", monsterType=" + monsterType + ", cost=" + cost + ", helper="
        + helper + ", monsterModifier=" + monsterModifier + "]";
  }

  public static class Builder{
    private String name;
    private MonsterType monsterType;
    
    private int cost;
    private boolean helper;
    private MonsterModifier monsterModifier;
    
    Builder(String name, MonsterType monsterType){
      this.name = name;
      this.monsterType = monsterType;
    }
    
    public Builder setCost(int cost) {
      this.cost = cost;
      return this;
    }
    
    public Builder setHelper(boolean helper) {
      this.helper = helper;
      return this;
    }
    
    public Builder setMonsterModifier(MonsterModifier monsterModifier){
      this.monsterModifier = monsterModifier;
      return this;
    }
    
    public Monster Build(){
      return new Monster(this);
    }
  }
}
