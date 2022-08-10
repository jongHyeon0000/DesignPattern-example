package DesignPattern.example.abstract_builder;

public class Village_NPC extends NPC{
  public Village_NPC(Builder builder) {
    super(builder);
  }
  
  static public class Builder extends NPC.Builder<Builder>{
    Builder(String name, NPC_Type monsterType) {
      super(name, monsterType);
    }
    
    @Override
    protected NPC Build() {
      return new Village_NPC(this);
    }
    
    @Override
    protected Builder Self() {
      return this;
    }
  }
}
