package DesignPattern.example.command;

public class Main {
  public static void main(String[] args) {
    Remote remote = new Remote();
    HouseHandler houseHandler = HouseHandler.getInstance();
    
    remote.setMode(new WorkingMode(houseHandler));
    remote.nowMode();
    
    System.out.println();
    
    remote.setMode(new VentilationMode(houseHandler));
    remote.nowMode();
  }   
}
