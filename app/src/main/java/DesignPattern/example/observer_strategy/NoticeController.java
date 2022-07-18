package DesignPattern.example.observer_strategy;

interface NoticeController {
  void Attach(NoticeObserver noticeObserver);
  void Detach(NoticeObserver noticeObserver);
  void NotifyObserver();
}
