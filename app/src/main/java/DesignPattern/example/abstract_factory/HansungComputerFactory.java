package DesignPattern.example.abstract_factory;

public class HansungComputerFactory implements ComputerFactory{

  @Override
  public Keyboard CreateKeyboard(String name) {
    return new SamsungKeyboard(name);
  }

  @Override
  public Mouse CreateMouse(int cost, int weight, String modelNumber) {
    return new AppleMouse(cost, weight, modelNumber);
  }

  @Override
  public Monitor CreateMonitor(int cost, int weight, int inch) {
    return new AppleMonitor(cost, weight, inch);
  }

}
