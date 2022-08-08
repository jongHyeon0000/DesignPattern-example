package DesignPattern.example.command;

import DesignPattern.example.command.Lamp.LampState;

public class SleepMode implements RemoteCommand{
  Lamp lamp;
  Speacker speacker;
  Window window;
  
  SleepMode(HouseHandler houseHandler){
    this.lamp = houseHandler.getLamp();
    this.speacker = houseHandler.getSpeacker();
    this.window = houseHandler.getWindow();
  }
  
  @Override
  public void excute() {
    lampSleepMode();
    speackerSleepMode();
    windowSleepMode();
  }
  
  private void lampSleepMode() {
    if(lamp.getLampState() != LampState.LOW) {
      lamp.setLightOffSetting();
    }
    else {
      lamp.setLowLightSetting();
    }
  }
  
  private void speackerSleepMode() {    
    if(speacker.getVolume() < 20) {
      speacker.setVolume(0);
    }
    else {
      speacker.lowVolumeSetting();;
    }
  }
  
  private void windowSleepMode() {
    window.closeWindow();
  }

  @Override
  public String toString() {
    return "Now Mode is SleepMode";
  }
}
