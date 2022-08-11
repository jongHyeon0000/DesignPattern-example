package DesignPattern.example.abstract_factory;

import java.io.IOException;

public interface BluetoothSupport<T> {
	public static final String BLUETOOTH_VERSION = "BT5.1";
	public static final String BLUETOOTH_PROTOCOL = "HID";
	public static final int BLUETOOTH_DISTANCE = 75;  	
	
  T Pairing() throws IOException;
  void unpair();
  
  default T autoPairing() throws IOException, IllegalAccessException{
    throw new UnsupportedOperationException();
  }
}
