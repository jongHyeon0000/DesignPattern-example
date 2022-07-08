package DesignPattern.example.factory_method.minion;

public class RedTeamMinionCreater extends MinionCreater{
  @Override
  protected Minion MinionFactoryMethod(MINION_TYPE minionType){
    switch(minionType){
      case SUPER:
          return new RedTeamSuperMinion();

      case WARRIOR:
          return new RedTeamWarriorMinion();
      
      case WIZARD:
          return new RedTeamWizardMinion();

      default:
          return null;
    }
  }
}
