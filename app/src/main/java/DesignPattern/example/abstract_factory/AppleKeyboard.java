package DesignPattern.example.abstract_factory;

public class AppleKeyboard extends Keyboard{
  AppleKeyboard(String name){
    super(name);
  }
  
  @Override
  public String toString() {
    return "I'm AppleKeyboard";
  }
}
