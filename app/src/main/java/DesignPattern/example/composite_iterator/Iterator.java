package DesignPattern.example.composite_iterator;

public interface Iterator<E> {
  boolean hasNext();
  E next();
  
  default void remove() {
    throw new UnsupportedOperationException("remove");
  }
}
