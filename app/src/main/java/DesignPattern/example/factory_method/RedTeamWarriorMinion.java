package DesignPattern.example.factory_method;

public class RedTeamWarriorMinion extends Minion{
    @Override
	public String toString() { return "I'm Wizard Minion"; }

  @Override
  public void MinionInit() { System.out.println("Load RedTeamWarriorMinion texture and AI..."); }
}