package DesignPattern.example.factory_method;

public class BlueTeamSuperMinion extends Minion{
  @Override
	public String toString() { return "I'm Super Minion"; }

  @Override
  public void MinionInit() { System.out.println("Load BlueTeamSuperMinion texture and AI..."); }
}
