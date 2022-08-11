package DesignPattern.example.abstract_factory;

public interface ComputerFactory {
  Keyboard CreateKeyboard(String name);
  Monitor CreateMonitor(int cost, int weight, int inch);
  Mouse CreateMouse(int cost, int weight, String modelNumber);
}
