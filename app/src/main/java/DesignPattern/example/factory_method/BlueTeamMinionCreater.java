package DesignPattern.example.factory_method;

public class BlueTeamMinionCreater extends MinionCreater{
  @Override
  protected Minion MinionFactoryMethod(MINION_TYPE minionType){
    switch(minionType){
      case SUPER:
          return new BlueTeamSuperMinion();

      case WARRIOR:
          return new BlueTeamWarriorMinion();
      
      case WIZARD:
          return new BlueTeamWizardMinion();

      default:
          return null;
    }
  }
}
