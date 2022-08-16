package DesignPattern.example.abstract_factory.keyboard;

public class AppleKeyboard extends Keyboard{
  public AppleKeyboard(String name){
    super(name);
  }
  
  @Override
  public String toString() {
    return "I'm AppleKeyboard";
  }
}
