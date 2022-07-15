package DesignPattern.example.observer;

public class Server_A_Player implements NoticeObserver{
  @Override
  public void Update(EventStrategy eventStrategy) {
    System.out.println("Observer Logic..");
  }
}
