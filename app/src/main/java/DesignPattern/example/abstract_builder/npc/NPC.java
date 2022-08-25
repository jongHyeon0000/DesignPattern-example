package DesignPattern.example.abstract_builder.npc;

import java.util.List;

abstract public class NPC {
  public NPC(Builder<?> builder) {
    this.name = builder.name;
    this.npcType = builder.npcType;
    this.cost = builder.cost;
    this.helper = builder.helper;
    this.npcModifier = builder.npcModifier;
  }

  private String name;
  private NPC_Type npcType;

  private int cost;
  private boolean helper;
  private NPC_Modifier npcModifier;

  @Override
  public String toString() {
    return "NPC [name=" + name + ", npcType=" + npcType + ", cost=" + cost + ", helper=" + helper
        + ", npcModifier=" + npcModifier + "]";
  }

  abstract public static class Builder<T extends Builder<T>> {
    private String name;
    private NPC_Type npcType;

    private int cost;
    private boolean helper;
    private NPC_Modifier npcModifier;

    Builder(String name, NPC_Type npcType) {
      this.name = name;
      this.npcType = npcType;
    }

    public T setCost(int cost) {
      this.cost = cost;
      return Self();
    }

    public T setHelper(boolean helper) {
      this.helper = helper;
      return Self();
    }

    public T setNpcModifier(NPC_Modifier npcModifier) {
      this.npcModifier = npcModifier;
      return Self();
    }
    
    public static <E extends Comparable> E Max(List<E> list){
      return list.stream().max((e1, e2) -> e1.compareTo(e2)).orElseThrow();
    }

    abstract protected NPC Build();

    abstract protected T Self();
  }
}
