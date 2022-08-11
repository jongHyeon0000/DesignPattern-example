package DesignPattern.example.abstract_factory;

import java.io.IOException;

public class SamsungMouse extends Mouse {
  SamsungMouse(int cost, int weight, String modelNumber) {
    super(cost, weight, modelNumber);
  }

  @Override
  public SamsungMouse pairing() throws IOException {
    // Pairing Logic...
    System.out.println("Pairing Logic...");
    // Pairing Logic...

    return this;
  }

  @Override
  public void unpair() {
    // Unpairing Logic...
    System.out.println("Unpairing Logic...");
    // Unpairing Logic...
  }

  @Override
  @Deprecated
  public SamsungMouse autoPairing() throws IOException, IllegalAccessException {
    throw new UnsupportedOperationException();
  }
}
