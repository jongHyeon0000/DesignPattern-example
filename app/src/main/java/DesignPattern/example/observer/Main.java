package DesignPattern.example.observer;

public class Main {
  public static void main(String[] args) {
    NoticeController server_A_Notice = new Server_A_Notice();
    NoticeObserver server_A_Player = new Server_A_Player();
    
    server_A_Notice.Attach(server_A_Player);
    server_A_Notice.sendMessage(10);
    
  }
}
