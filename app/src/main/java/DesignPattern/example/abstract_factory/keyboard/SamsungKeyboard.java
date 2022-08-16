package DesignPattern.example.abstract_factory.keyboard;

public class SamsungKeyboard extends Keyboard{
  public SamsungKeyboard(String name){
    super(name);
  }

  @Override
  public String toString() {
    return "I'm SamsungKeyboard";
  }
}
