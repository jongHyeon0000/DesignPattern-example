package DesignPattern.example.singleton.EnumSingleton;

import java.util.Arrays;

public enum EnumSingleton {
  INSTANCE,
}

class Stack<T>{
  private int size;
  private Object[] elements;
  
  Stack(int capacity) {
    size = 0;
    elements = new Object[capacity];
  }
  
  public void push(T t) {
    ensureCapacity();
    elements[++size] = t;
  }
  
  private void ensureCapacity() {
    if(elements.length <= size - 1) {
      elements = Arrays.copyOf(elements, elements.length * 2 + 1);
    }
  }
  
  public T pop(){    
    @SuppressWarnings("unchecked") T result = (T) elements[size];
    elements[size--] = null;
    
    return result;
  }
}