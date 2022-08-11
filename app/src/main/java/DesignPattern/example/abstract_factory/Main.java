package DesignPattern.example.abstract_factory;

import java.util.Map;

public class Main {
  public static void main(String[] args) {
    ComputerFactory computerFactory = new HansungComputerFactory();
    Computer myHansungComputer1 = new Computer(computerFactory.CreateKeyboard("SM-123"),
                                              computerFactory.CreateMouse(12000, 20, "SAM-33"),
                                              computerFactory.CreateMonitor(400000, 230, 40));
    
    computerFactory = new MsiComputerFactory();
    Computer myMsiComputer1 = new Computer(computerFactory.CreateKeyboard("KJ-323"),
                                          computerFactory.CreateMouse(6000, 40, "MAC-34"),
                                          computerFactory.CreateMonitor(1200000, 110, 40));
    
    computerFactory = new HansungComputerFactory();
    Computer myHansungComputer2 = new Computer(computerFactory.CreateKeyboard("SM-122"),
                                          computerFactory.CreateMouse(5500, 35, "MAC-35"),
                                          computerFactory.CreateMonitor(1250000, 116, 41));
    
    Computer myHansungComputer3 = new Computer(computerFactory.CreateKeyboard("SM-129"),
                                          computerFactory.CreateMouse(7500, 42, "MAC_S-35"),
                                          computerFactory.CreateMonitor(1450000, 70, 41));
    
    Map<Integer, Monitor> MonitorRank = Monitor.CreateMonitorRank.getRanking(myHansungComputer1.getMonitor(),
                                         myHansungComputer2.getMonitor(),
                                         myHansungComputer3.getMonitor(),
                                         myMsiComputer1.getMonitor());
    
    System.out.println(MonitorRank);
  }
}
