package DesignPattern.example.factory_method.minion;

public class BlueTeamWarriorMinion extends Minion{
  @Override
	public String toString() { return "I'm Warrior Minion"; }

  @Override
  public void MinionInit() { System.out.println("Load BlueTeamWarriorMinion texture and AI..."); }
}
