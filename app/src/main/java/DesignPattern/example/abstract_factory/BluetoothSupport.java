package DesignPattern.example.abstract_factory;

import java.io.IOException;

public interface BluetoothSupport<T> {
  T Pairing() throws IOException;
  void unpair();
  
  default T autoPairing() throws IOException, IllegalAccessException{
    throw new UnsupportedOperationException();
  }
}
