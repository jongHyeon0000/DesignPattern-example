package DesignPattern.example.abstract_factory;

public class SamsungKeyboard extends Keyboard{
  SamsungKeyboard(String name){
    super(name);
  }

  @Override
  public String toString() {
    return "I'm SamsungKeyboard";
  }
}
