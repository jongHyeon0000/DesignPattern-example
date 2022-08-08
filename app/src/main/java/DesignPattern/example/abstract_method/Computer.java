package DesignPattern.example.abstract_method;

abstract public class Computer {
  private int cost;
  private int weight;

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
