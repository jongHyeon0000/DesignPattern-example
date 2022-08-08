package DesignPattern.example.command;

import java.util.concurrent.atomic.AtomicBoolean;

public class HouseHandler {
  private Lamp lamp = new Lamp();
  private Speacker speacker = new Speacker();
  private Window window = new Window();
  private AirConditioner airConditioner = new AirConditioner();
  
  public Lamp getLamp() {
    return lamp;
  }
  public Speacker getSpeacker() {
    return speacker;
  }
  public Window getWindow() {
    return window;
  }
  public AirConditioner getAirConditioner() {
    return airConditioner;
  }
  
  
  private AtomicBoolean isCreated = new AtomicBoolean(false);
  
  private HouseHandler() {
    if(isCreated.get()) {
      throw new IllegalStateException();
    }
    
    isCreated.compareAndSet(false, true);
  }
  
  public static HouseHandler getInstance() {
    return LazyHolder.instance;
  }
  
  private static class LazyHolder{
    private static final HouseHandler instance = new HouseHandler();
  }
}
