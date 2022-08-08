package DesignPattern.example.command;

public class AirConditioner {
  private int desiredTemperature = 18;
  
  public void setDesiredTemperature(int desiredTemperature) {
    this.desiredTemperature = desiredTemperature;
    
    System.out.printf("now Temperature is : %d", desiredTemperature);
    System.out.println();
  }
  
  public void turnOn() {
    System.out.printf("Turn on AirConditioner.. now Temperature is : %d", desiredTemperature);
    System.out.println();
  }
  
  public void turnOff() {
    System.out.println("Turn off AirConditioner");
  }
  
}
