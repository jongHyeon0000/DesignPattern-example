package DesignPattern.example.abstract_builder;

public class Monster1 extends Monster{
  public Monster1(Builder builder) {
    super(builder);
  }
  
  static public class Builder extends Monster.Builder<Builder>{
    Builder(String name, MonsterType monsterType) {
      super(name, monsterType);
    }
    
    @Override
    protected Monster Build() {
      return new Monster1(this);
    }
    
    @Override
    protected Builder Self() {
      return this;
    }
  }
}
