package DesignPattern.example.abstract_factory;

public class Computer{
  private Keyboard keyboard;
  private Mouse mouse;
  private Monitor monitor;
  
  Computer(Keyboard keyboard, Mouse mouse, Monitor monitor){
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
