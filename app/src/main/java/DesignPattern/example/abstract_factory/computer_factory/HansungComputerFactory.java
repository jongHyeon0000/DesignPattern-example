package DesignPattern.example.abstract_factory.computer_factory;

import DesignPattern.example.abstract_factory.keyboard.Keyboard;
import DesignPattern.example.abstract_factory.keyboard.SamsungKeyboard;
import DesignPattern.example.abstract_factory.monitor.AppleMonitor;
import DesignPattern.example.abstract_factory.monitor.Monitor;
import DesignPattern.example.abstract_factory.mouse.AppleMouse;
import DesignPattern.example.abstract_factory.mouse.Mouse;

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
  public Monitor CreateMonitor(String name, int cost, int weight, int inch) {
    return new AppleMonitor(name, cost, weight, inch);
  }

}
