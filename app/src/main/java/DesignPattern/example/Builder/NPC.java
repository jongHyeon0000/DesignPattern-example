package DesignPattern.example.Builder;


public class NPC {  
	enum NPC_Type{
		MERCHANT, HELPER, QUEST
	}
	
	enum NPC_Modifier{
		FRIENDLY, NEUTRAL, HOSTILE
	}
	
  private NPC(Builder builder){
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
    return "NPC [name=" + name + ", npcType=" + npcType + ", cost=" + cost + ", helper="
        + helper + ", npcModifier=" + npcModifier + "]";
  }

  public static class Builder{
    private String name;
    private NPC_Type npcType;
    
    private int cost;
    private boolean helper;
    private NPC_Modifier npcModifier;
    
    Builder(String name, NPC_Type npcType){
      this.name = name;
      this.npcType = npcType;
    }
    
    public Builder setCost(int cost) {
      this.cost = cost;
      return this;
    }
    
    public Builder setHelper(boolean helper) {
      this.helper = helper;
      return this;
    }
    
    public Builder setNpcModifier(NPC_Modifier npcModifier){
      this.npcModifier = npcModifier;
      return this;
    }
    
    public NPC Build(){
      return new NPC(this);
    }
  }
}
