package DesignPattern.example.command;

public class Lamp {
  enum LampState{
    OFF,
    LOW,
    MEDIUM,
    HIGH;
  }
  
  LampState lampState = LampState.OFF;
  
  public void setLightOffSetting(){
    lampState = LampState.OFF;
  }
  
  public void setLowLightSetting(){
    lampState = LampState.LOW;
  }
  
  public void setMediumLightSetting(){
    lampState = LampState.MEDIUM;
  }

  public void setHighLightSetting(){
    lampState = LampState.HIGH;
  }
  
  public LampState getLampState(){
    return lampState;
  }
}
