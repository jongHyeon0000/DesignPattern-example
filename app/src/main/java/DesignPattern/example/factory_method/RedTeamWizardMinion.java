package DesignPattern.example.factory_method;

public class RedTeamWizardMinion extends Minion{
    @Override
	public String toString() { return "I'm Wizard Minion"; }

  @Override
  public void MinionInit() { System.out.println("Load RedTeamWizardMinion texture and AI..."); }
}
