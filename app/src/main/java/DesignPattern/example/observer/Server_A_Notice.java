package DesignPattern.example.observer;

public class Server_A_Notice extends NoticeController{
  Server_A_Notice(EventStrategy eventStrategy) {
    super(eventStrategy);
    // TODO Auto-generated constructor stub
  }

  @Override
  protected void sendMessage(int state) {
    // TODO Auto-generated method stub
    
  }
  
}