package DesignPattern.example.abstract_factory;

abstract public class Mouse<T extends Mouse<? extends T>> extends Computer implements BluetoothSupport<T>{
  private String modelNumber;
  
  Mouse(int cost, int weight, String modelNumber){
    super(cost, weight);
    this.modelNumber = modelNumber;
  }

  @Override
  public String toString() {
    return "Mouse [modelNumber=" + modelNumber + "]";
  }
}
