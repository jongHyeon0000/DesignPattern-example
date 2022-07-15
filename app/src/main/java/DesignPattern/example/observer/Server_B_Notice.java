package DesignPattern.example.observer;

public class Server_B_Notice extends NoticeController{
  Server_B_Notice(EventStrategy eventStrategy) {
    super(eventStrategy);
  }

  @Override
  protected void sendMessage(int state) {
    // TODO Auto-generated method stub
    
  }
}
