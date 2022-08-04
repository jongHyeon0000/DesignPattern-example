package DesignPattern.example.abstract_builder;

enum MonsterType{
  TYPE1, TYPE2, TYPE3
}

enum MonsterModifier{
  MODIFIER1, MODIFIER2, MODIFIER3
}

abstract public class Monster {  
  public Monster(Builder<?> builder){
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

  abstract public static class Builder<T extends Builder<T>>{
    private String name;
    private MonsterType monsterType;
    
    private int cost;
    private boolean helper;
    private MonsterModifier monsterModifier;
    
    Builder(String name, MonsterType monsterType){
      this.name = name;
      this.monsterType = monsterType;
    }
    
    public T setCost(int cost) {
      this.cost = cost;
      return Self();
    }
    
    public T setHelper(boolean helper) {
      this.helper = helper;
      return Self();
    }
    
    public T setMonsterModifier(MonsterModifier monsterModifier){
      this.monsterModifier = monsterModifier;
      return Self();
    }
    
    abstract protected Monster Build();
    abstract protected T Self();
  }
}
