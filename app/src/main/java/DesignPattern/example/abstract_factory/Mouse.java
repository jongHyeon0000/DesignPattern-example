package DesignPattern.example.abstract_factory;

abstract public class Mouse implements BluetoothSupport<Mouse>{
  protected String modelNumber;
  
  Mouse(int cost, int weight, String modelNumber){
    this.modelNumber = modelNumber;
  }

  @Override
  public String toString() {
    return "Mouse [modelNumber=" + modelNumber + "]";
  }
}
