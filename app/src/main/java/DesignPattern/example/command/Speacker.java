package DesignPattern.example.command;

public class Speacker {
  private int volume = 0;
  
  public void setVolume(int volume) {
    if(volume < 0 && volume > 100) {
      this.volume = volume;
      
      return;
    }
    
    throw new IllegalArgumentException();
  }
  
  public void lowVolumeSetting(){
    volume = 20;
  }
  
  public void mediumVolumeSetting(){
    volume = 60;
  }

  public void highVolumeSetting(){
    volume = 80;
  }
  
  public void speackerState() {
    if(volume < 20) {
      System.out.println("♫♫");
    }
    else if (volume > 20 && volume < 60){
      System.out.println("♫♫♫♫♫♫♫♫");
    }
    else {
      System.out.println("♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫");
    }
  }
  
  public int getVolume() {
    return volume;
  }
}
