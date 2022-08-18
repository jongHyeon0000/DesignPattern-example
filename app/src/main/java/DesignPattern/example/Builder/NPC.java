package DesignPattern.example.Builder;

import java.util.Date;

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

//  private String name;
//  private NPC_Type npcType;
//  
//  private int cost;
//  private boolean helper;
//  private NPC_Modifier npcModifier;
  
  private String name;
  private NPC_Type npcType;
  
  private int cost = 0;
  private boolean helper = false;
  private NPC_Modifier npcModifier = NPC_Modifier.FRIENDLY;
  
  public void setName(String name) {
    this.name = name;
  }

  public void setNpcType(NPC_Type npcType) {
    this.npcType = npcType;
  }

  public void setCost(int cost) {
    this.cost = cost;
  }

  public void setHelper(boolean helper) {
    this.helper = helper;
  }

  public void setNpcModifier(NPC_Modifier npcModifier) {
    this.npcModifier = npcModifier;
  }

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

final class DiscountEventPeriod{
  private final Date start;
  private final Date end;
  
  public DiscountEventPeriod(Date start, Date end) {
    if(start.compareTo(end) > 0) {
      throw new IllegalArgumentException("종료시간(" + end + ") 가 시작시간(" + start + ")보다 빠를 수 없습니다.");
    }
    
    this.start = start;
    this.end = end;
  }
}
