package DesignPattern.example.abstract_factory;

abstract public class Monitor<T extends Monitor<T>> extends Computer implements Comparable<Monitor<? super T>>{
  private int inch;
  
  Monitor(int cost, int weight, int inch){
    super(cost, weight);
    
    this.inch = inch;
  }
  
  public int getStar() {
    int star = cost - (weight * 10) + (inch * 50);
    
    if(star < 0) {
      throw new IllegalStateException();
    }
    
    return star;
  }
}
