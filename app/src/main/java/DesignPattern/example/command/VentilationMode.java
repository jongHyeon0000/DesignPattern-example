package DesignPattern.example.command;

public class VentilationMode implements RemoteCommand{
  Window window;
  AirConditioner airConditioner;
  
  VentilationMode(HouseHandler houseHandler){
    this.window = houseHandler.getWindow();
    this.airConditioner = houseHandler.getAirConditioner();
  }
  
  @Override
  public void excute() {
    windowVentilationMode();
    airConditionerVentilationMode();
  }
  
  private void windowVentilationMode() {
    window.closeWindow();
  }
  
  private void airConditionerVentilationMode() {
    airConditioner.turnOff();
  }

  @Override
  public String toString() {
    return "Now Mode is VentilationMode";
  }
}
