package DesignPattern.example.observer;

public class ServerNotice extends NoticeController{
  ServerNotice(EventStrategy eventStrategy) {
    super(eventStrategy);
  }
}