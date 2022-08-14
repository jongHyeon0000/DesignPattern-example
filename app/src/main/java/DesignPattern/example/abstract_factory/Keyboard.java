package DesignPattern.example.abstract_factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

abstract public class Keyboard{
  protected String name;
  
  Keyboard(String name){
    this.name = name;
  }

  // item 1
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Keyboard other = (Keyboard) obj;
    return Objects.equals(name, other.name);
  }
  
  // item 2
  @Override
  public int hashCode() {
    if (name == null) {
      return 0;
    }
    
    int i = Objects.hashCode(name);
    int result = (i << 5) - i; // int result = 31 * Objects.hashCode(name);
  
    return result;
  }
  
  @Override
  public String toString() {
    return "Keyboard [name=" + name + "]";
  }
}


class Test{
	public static void testMethod() {
		Map<Keyboard, String> m = new HashMap<>();
		m.put(new SamsungKeyboard("FC-770"), "JongHyeon");
		m.get(new SamsungKeyboard("FC-770"));
	}
}