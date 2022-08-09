package DesignPattern.example.abstract_factory;

import java.util.Objects;

abstract public class Keyboard extends Computer{
  private String name;
  
  Keyboard(int cost, int weight, String name){
    super(cost, weight);
    this.name = name;
  }

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

  @Override
  public int hashCode() {
    if (name == null) {
      return 0;
    }
    
    // int result = 31 * Objects.hashCode(name);
    int i = Objects.hashCode(name);
    int result = (i << 5) - i;
  
    return result;
  }

  @Override
  public String toString() {
    return "Keyboard [name=" + name + "]";
  }
}
 