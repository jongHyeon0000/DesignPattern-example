package DesignPattern.example.abstract_factory.computer_factory;

import DesignPattern.example.abstract_factory.keyboard.Keyboard;
import DesignPattern.example.abstract_factory.monitor.Monitor;
import DesignPattern.example.abstract_factory.mouse.Mouse;

public interface ComputerFactory {
  public enum Compony {
    HANSUNG, MSI
  }

  ComputerFactory HANSUNG_COMPUTER_FACTORY = new HansungComputerFactory();
  ComputerFactory MSI_COMPUTER_FACTORY = new MsiComputerFactory();

  Keyboard CreateKeyboard(String name);

  Monitor CreateMonitor(String name, int cost, int weight, int inch);

  Mouse CreateMouse(int cost, int weight, String modelNumber);

  static ComputerFactory getComputerFactory(Compony compony) {
    switch (compony) {
      case HANSUNG:
        return HANSUNG_COMPUTER_FACTORY;
      case MSI:
        return MSI_COMPUTER_FACTORY;
      default:
        throw new IllegalStateException();
    }
  }
}
