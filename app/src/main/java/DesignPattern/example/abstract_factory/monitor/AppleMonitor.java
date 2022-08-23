package DesignPattern.example.abstract_factory.monitor;

import java.util.Arrays;
import java.util.Collection;
import java.util.EmptyStackException;
import java.util.Iterator;

public class AppleMonitor extends Monitor {
  public AppleMonitor(String name, int cost, int weight, int inch) {
    super(name, cost, weight, inch);
  }

  @Override
  public String toString() {
    return "I'm AppleMonitor";
  }
}


class Stack<T> {
  private int size; // 스택의 현재 사이즈
  private T[] elements; // 스택 내부 배열

  Stack(int capacity) {
    size = 0;
    elements = (T) new Object[capacity];
  }

  public void push(T t) {
    ensureCapacity();
    elements[++size] = t;
  }

  // 내부 배열이 가득 차면 현재 배열의 크기를 2배로 늘린다.
  private void ensureCapacity() {
    if (size <= 0) {
      elements = Arrays.copyOf(elements, elements.length * 2 + 1);
    }
  }

  public T pop() {
    if (!isEmpty()) {
      @SuppressWarnings("unchecked")
      T result = elements[--size];
      elements[size] = null;

      return result;
    }
    throw new EmptyStackException();
  }

  public void pushAll(Iterator<? extends T> src) {
    while (src.hasNext()) {
      push(src.next());
    }
  }

  public boolean isEmpty() {
    return (size <= 0) ? true : false;
  }

  public void popAll(Collection<T> dst) {
    while (!isEmpty()) {
      dst.add(pop());
    }
  }
}


abstract class People {

}


class Man extends People implements Iterator<Man> {
  @Override
  public boolean hasNext() {
    return true;
  }

  @Override
  public Man next() {
    return new Man();
  }
  
  
}
