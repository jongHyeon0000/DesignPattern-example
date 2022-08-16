package DesignPattern.example.abstract_factory.mouse;

import java.io.IOException;

public class AppleMouse extends Mouse {
  public AppleMouse(int cost, int weight, String modelNumber) {
    super(cost, weight, modelNumber);
  }

  @Override
  public AppleMouse pairing() throws IOException {
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
  public AppleMouse autoPairing() throws IOException, IllegalAccessException {
    // Autopairing Logic...

    return this;
  }
}
