package DesignPattern.example.abstract_factory.computer_factory;

import DesignPattern.example.abstract_factory.keyboard.AppleKeyboard;
import DesignPattern.example.abstract_factory.keyboard.Keyboard;
import DesignPattern.example.abstract_factory.monitor.AppleMonitor;
import DesignPattern.example.abstract_factory.monitor.Monitor;
import DesignPattern.example.abstract_factory.mouse.Mouse;
import DesignPattern.example.abstract_factory.mouse.SamsungMouse;

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
