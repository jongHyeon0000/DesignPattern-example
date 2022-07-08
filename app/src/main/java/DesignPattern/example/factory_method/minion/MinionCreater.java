package DesignPattern.example.factory_method.minion;

abstract public class MinionCreater {
  public enum MINION_TYPE {
    SUPER,
    WARRIOR,
    WIZARD;
  }

  public Minion Operation(MINION_TYPE minionType){
    Minion minion = MinionFactoryMethod(minionType);
    
    minion.toString();
    minion.MinionInit();

    return minion;
  }

  abstract protected Minion MinionFactoryMethod(MINION_TYPE minionType); 
}
