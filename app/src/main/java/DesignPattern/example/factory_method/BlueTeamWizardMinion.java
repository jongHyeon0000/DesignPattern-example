package DesignPattern.example.factory_method;

public class BlueTeamWizardMinion extends Minion{
  @Override
	public String toString() { return "I'm Wizard Minion"; }

  @Override
  public void MinionInit() { System.out.println("Load BlueTeamWizardMinion texture and AI..."); }
}
