package DesignPattern.example.abstract_factory;

import DesignPattern.example.abstract_factory.computer.Computer;
import DesignPattern.example.abstract_factory.computer_factory.ComputerFactory;
import DesignPattern.example.abstract_factory.computer_factory.ComputerFactory2;
import DesignPattern.example.abstract_factory.computer_factory.ComputerFactory2.Compony;
import DesignPattern.example.abstract_factory.computer_factory.HansungComputerFactory;
import DesignPattern.example.abstract_factory.computer_factory.MsiComputerFactory;

public class Main {
  public static void main(String[] args) {
    ComputerFactory computerFactory = new HansungComputerFactory();
    Computer myHansungComputer1 = new Computer(computerFactory.CreateKeyboard("SM-123"),
        computerFactory.CreateMouse(12000, 20, "SAM-33"),
        computerFactory.CreateMonitor("GGA-123", 400000, 230, 40));

    computerFactory = new MsiComputerFactory();
    Computer myMsiComputer1 = new Computer(computerFactory.CreateKeyboard("KJ-323"),
        computerFactory.CreateMouse(6000, 40, "MAC-34"),
        computerFactory.CreateMonitor("Ms-91", 1200000, 110, 40));

    computerFactory = ComputerFactory2.getComputerFactory(Compony.HANSUNG);
    Computer myHansungComputer2 = new Computer(computerFactory.CreateKeyboard("SM-122"),
        computerFactory.CreateMouse(5500, 35, "MAC-35"),
        computerFactory.CreateMonitor("GGA-123", 400000, 230, 40));

    Computer myHansungComputer3 = new Computer(computerFactory.CreateKeyboard("SM-129"),
        computerFactory.CreateMouse(7500, 42, "MAC_S-35"),
        computerFactory.CreateMonitor("GGAb-12", 1450000, 70, 41));

  }
}
