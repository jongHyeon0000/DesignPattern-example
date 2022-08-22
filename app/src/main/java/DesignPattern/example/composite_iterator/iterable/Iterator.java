package DesignPattern.example.composite_iterator.iterable;

public interface Iterator<E> {
  boolean hasNext();
  E next();
  
  default void remove() {
    throw new UnsupportedOperationException("remove");
  }
}
