package DesignPattern.example.abstract_factory.computer;

import DesignPattern.example.abstract_factory.keyboard.Keyboard;
import DesignPattern.example.abstract_factory.monitor.Monitor;
import DesignPattern.example.abstract_factory.mouse.Mouse;

public class Computer{
  private Keyboard keyboard;
  private Mouse mouse;
  private Monitor monitor;
  
  public Computer(Keyboard keyboard, Mouse mouse, Monitor monitor){
    this.keyboard = keyboard;
    this.mouse = mouse;
    this.monitor = monitor;
  }
  
  public void PrintComputerSpecifications() {
    System.out.println(keyboard);
    System.out.println(mouse);
    System.out.println(monitor);
  }

  public Keyboard getKeyboard() {
    return keyboard;
  }

  public Mouse getMouse() {
    return mouse;
  }

  public Monitor getMonitor() {
    return monitor;
  }
}
