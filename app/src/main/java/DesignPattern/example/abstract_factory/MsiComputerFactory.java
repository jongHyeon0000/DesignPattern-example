package DesignPattern.example.abstract_factory;

public class MsiComputerFactory implements ComputerFactory{

  @Override
  public Keyboard CreateKeyboard(String name) {
    return new AppleKeyboard(name);
  }

  @Override
  public Mouse CreateMouse(int cost, int weight, String modelNumber) {
    return new SamsungMouse(cost, weight, modelNumber);
  }

  @Override
  public Monitor CreateMonitor(int cost, int weight, int inch) {
    return new AppleMonitor(cost, weight, inch);
  }

}
