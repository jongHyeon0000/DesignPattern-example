package DesignPattern.example.observer;

abstract public class PlayerManager implements NoticeObserver{
  protected String playerName;
  
  abstract public void GainXp(float Xp);
  abstract public void GainGold(float Gold);
}
