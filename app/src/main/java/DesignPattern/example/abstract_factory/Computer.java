package DesignPattern.example.abstract_factory;

abstract public class Computer {
  protected int cost;
  protected int weight;

  Computer(int cost, int weight){
    this.cost = cost;
    this.weight = weight;
  }
  
  public int getCost(){
    return cost;
  }

  public int getWeight(){
    return weight;
  }
}
