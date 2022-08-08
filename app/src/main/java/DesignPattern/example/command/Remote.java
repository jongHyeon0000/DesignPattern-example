package DesignPattern.example.command;

public class Remote {
  RemoteCommand command;
  
  public void setMode(RemoteCommand command) {
    this.command = command;
    command.excute();
  }
  
  public void nowMode() {
    System.out.println(command);
  }
}
