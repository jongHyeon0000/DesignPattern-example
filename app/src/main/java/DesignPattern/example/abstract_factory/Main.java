package DesignPattern.example.abstract_factory;

public class Main {
  public static void main(String[] args) {
    ComputerFactory computerFactory = new HansungComputerFactory();
    Computer myHansungComputer = new Computer(computerFactory.CreateKeyboard("SM-123"),
                                              computerFactory.CreateMouse(12000, 20, "SAM-33"),
                                              computerFactory.CreateMonitor(400000, 230, 40));
    myHansungComputer.PrintComputerSpecifications();
    
    computerFactory = new MsiComputerFactory();
    Computer myMsiComputer = new Computer(computerFactory.CreateKeyboard("KJ-323"),
                                          computerFactory.CreateMouse(6000, 40, "MAC-34"),
                                          computerFactory.CreateMonitor(1200000, 110, 40));
    myMsiComputer.PrintComputerSpecifications();
  }
}
