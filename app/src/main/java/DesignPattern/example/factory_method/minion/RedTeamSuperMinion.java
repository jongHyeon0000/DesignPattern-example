package DesignPattern.example.factory_method.minion;

public class RedTeamSuperMinion extends Minion{
    @Override
	public String toString() { return "I'm Super Minion"; }

    @Override
    public void MinionInit() { System.out.println("Load RedTeamSuperMinion texture and AI..."); }
}
