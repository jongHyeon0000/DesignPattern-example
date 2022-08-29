package DesignPattern.example.abstract_builder.npc;

public class Field_NPC extends NPC{
  public Field_NPC(Builder builder) {
    super(builder);
  }

  static public class Builder extends NPC.Builder<Builder>{
    public Builder(String name, NPC_Type monsterType) {
      super(name, monsterType);
    }
    
    @Override
    public NPC Build() {
      return new Field_NPC(this);
    }
    
    @Override
    protected Builder self() {
      return this;
    }
  }
}
