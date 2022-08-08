package DesignPattern.example.command;

public class Window {
  enum CurtainState{
    OPEN,
    CLOSE;
  }
  
  private Curtain curtain = new Curtain();
  
  public void openWindow() {
    System.out.println("Open Window...");
    curtain.setCurtainState(CurtainState.OPEN);
  }
  
  public void closeWindow() {
    System.out.println("Close Window...");
    curtain.setCurtainState(CurtainState.CLOSE);
  }
  
  public static class Curtain{    
    private CurtainState curtainState = CurtainState.CLOSE;
    
    public void setCurtainState(CurtainState curtainState) {
      this.curtainState = curtainState;
      
      if(curtainState == CurtainState.CLOSE) {
        System.out.println("Curtain Close Now");
      }
      else if(curtainState == CurtainState.OPEN) {
        System.out.println("Curtain Open Now");
      }
    }
  }
}
