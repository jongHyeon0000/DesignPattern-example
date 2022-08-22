package DesignPattern.example.abstract_factory.monitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class AppleMonitor extends Monitor {
  public AppleMonitor(String name, int cost, int weight, int inch) {
    super(name, cost, weight, inch);
  }

  @Override
  public String toString() {
    return "I'm AppleMonitor";
  }

  public static void main(String[] args) {
    Stack<People> peopleStack = new Stack<>(10);
    List<Man> manlist = new ArrayList<>();
    
    
    Iterator<Man> iter = manlist.iterator();
    peopleStack.pushAll(iter);
  }
}

class People implements Iterator<People>{

  @Override
  public boolean hasNext() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public People next() {
    // TODO Auto-generated method stub
    return null;
  }
  
}

class Man extends People{

}

class Stack<E>{
  private int size; // 스택의 현재 사이즈
  private Object[] elements; // 스택 내부 배열
  
  Stack(int capacity) {
    size = 0;
    elements = new Object[capacity];
  }
  
  public void push(E t) {
    ensureCapacity();
    elements[++size] = t;
  }
  
  // 내부 배열이 가득 차면 현재 배열의 크기를 2배로 늘린다.
  private void ensureCapacity() {
    if(size <= 0) {
      elements = Arrays.copyOf(elements, elements.length * 2 + 1);
    }
  }
  
  public E pop(){    
    @SuppressWarnings("unchecked") E result = (E) elements[--size];
    elements[size] = null;
    
    return result;
  }
  
  public void pushAll(Iterator<? extends E> src){
    for (; src.hasNext();){
      push(src.next());
    }
  }
}