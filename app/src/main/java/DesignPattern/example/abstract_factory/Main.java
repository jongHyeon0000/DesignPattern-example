package DesignPattern.example.abstract_factory;

import DesignPattern.example.abstract_factory.computer.Computer;
import DesignPattern.example.abstract_factory.computer_factory.ComputerFactory;
import DesignPattern.example.abstract_factory.computer_factory.HansungComputerFactory;
import DesignPattern.example.abstract_factory.computer_factory.MsiComputerFactory;
import DesignPattern.example.abstract_factory.monitor.AppleMonitor;
import DesignPattern.example.abstract_factory.monitor.Monitor;

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

    computerFactory = new HansungComputerFactory();
    Computer myHansungComputer2 = new Computer(computerFactory.CreateKeyboard("SM-122"),
        computerFactory.CreateMouse(5500, 35, "MAC-35"),
        computerFactory.CreateMonitor("GGA-123", 400000, 230, 40));

    Computer myHansungComputer3 = new Computer(computerFactory.CreateKeyboard("SM-129"),
        computerFactory.CreateMouse(7500, 42, "MAC_S-35"),
        computerFactory.CreateMonitor("GGAb-12", 1450000, 70, 41));

    System.out.println(Monitor.LowestPriceMonitorManager.getLowestPriceMonitorMap(AppleMonitor.class));
    
    System.out.println(Monitor.LowestPriceMonitorManager.getLowestPriceMonitor(AppleMonitor.class,
        myHansungComputer1.getMonitor()));
    
    System.out.println(
        Monitor.LowestPriceMonitorManager.getLowestPriceMonitor(AppleMonitor.class, "GGA-123"));
    
    
  }
}
