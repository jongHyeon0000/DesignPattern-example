package DesignPattern.example.abstract_factory.computer_factory;

import DesignPattern.example.abstract_factory.keyboard.Keyboard;
import DesignPattern.example.abstract_factory.monitor.Monitor;
import DesignPattern.example.abstract_factory.mouse.Mouse;

public interface ComputerFactory {
  Keyboard CreateKeyboard(String name);
  Monitor CreateMonitor(int cost, int weight, int inch);
  Mouse CreateMouse(int cost, int weight, String modelNumber);
}
