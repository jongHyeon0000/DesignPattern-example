package DesignPattern.example.Builder.npc;

import java.util.Objects;

public class NPC {
  private static final int MAX_NAME_LENGTH = 10;

  public NPC(Builder builder) {
    if(builder.name.length() > MAX_NAME_LENGTH) {
      throw new IllegalArgumentException(String.format("이름(name)은 10자 미만이여야 합니다. input : %d", name));
    }
    else if(cost < 0) {
      throw new IllegalArgumentException(String.format("비용(cost)은 0 이상이여야 합니다. input : %d", cost));
    }
    
    this.name = builder.name;
    this.npcType = builder.npcType;
    this.cost = builder.cost;
    this.helper = builder.helper;
    this.npcModifier = builder.npcModifier;
  }

  // NPC 객체의 필수 필드, 반드시 초기화 되어야 하기 때문에 기본값이 있을 수 없다. 
  private String name;
  private NPC_Type npcType;

  private int cost = 1;
  private boolean helper = false;
  private NPC_Modifier npcModifier = NPC_Modifier.FRIENDLY;
  
  @Override
  public String toString() {
    return "NPC [name=" + name + ", npcType=" + npcType + ", cost=" + cost + ", helper=" + helper
        + ", npcModifier=" + npcModifier + "]";
  }

  public static class Builder {
    private String name;
    private NPC_Type npcType;

    private int cost;
    private boolean helper;
    private NPC_Modifier npcModifier;

    public Builder(String name, NPC_Type npcType) {
      this.name = Objects.requireNonNull(name);
      this.npcType = Objects.requireNonNull(npcType);
    }

    public Builder setCost(int cost) {
      this.cost = cost;
      return this;
    }

    public Builder setHelper(boolean helper) {
      this.helper = helper;
      return this;
    }

    public Builder setNpcModifier(NPC_Modifier npcModifier) {
      this.npcModifier = Objects.requireNonNull(npcModifier);
      return this;
    }

    public NPC build() {
      return new NPC(this);
    }
  }
}
