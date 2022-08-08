package DesignPattern.example.command;

public class WorkingMode implements RemoteCommand{
  Lamp lamp;
  Speacker speacker;
  Window window;
  AirConditioner airConditioner;
  
  public WorkingMode(HouseHandler houseHandler) {
    this.lamp = houseHandler.getLamp();
    this.speacker = houseHandler.getSpeacker();
    this.window = houseHandler.getWindow();
    this.airConditioner = houseHandler.getAirConditioner();
  }

  @Override
  public void excute() {
    lampWorkingMode();
    speackerWorkingMode();
    windowWorkingMode();
    airConditionerWorkingMode();
  }
  
  private void lampWorkingMode() {
    lamp.setMediumLightSetting();
  }
  
  private void speackerWorkingMode() {
    speacker.lowVolumeSetting();
  }
  
  private void windowWorkingMode() {
    window.closeWindow();
  }
  
  private void airConditionerWorkingMode() {
    airConditioner.turnOn();
  }

  @Override
  public String toString() {
    return "Now Mode is WorkingMode";
  }
}
