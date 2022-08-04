package DesignPattern.example.abstract_builder;

public class Monster2 extends Monster{
  public Monster2(Builder builder) {
    super(builder);
  }

  static public class Builder extends Monster.Builder<Builder>{
    Builder(String name, MonsterType monsterType) {
      super(name, monsterType);
    }
    
    @Override
    protected Monster Build() {
      return new Monster2(this);
    }
    
    @Override
    protected Builder Self() {
      return this;
    }
  }
}
