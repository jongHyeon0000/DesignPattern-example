package DesignPattern.example.abstract_factory;

import java.io.IOException;

public class AppleMouse extends Mouse<AppleMouse>{
  AppleMouse(int cost, int weight, String modelNumber){
    super(cost, weight, modelNumber);
  }

	@Override
	public AppleMouse Pairing() throws IOException {
		// Pairing Logic...
		System.out.println("Pairing Logic...");
		// Pairing Logic...
			
		return this;
	}

	@Override
	public void unpair() {
		// Unpairing Logic...
	}
	
	@Override
	public AppleMouse autoPairing() throws IOException, IllegalAccessException{
		// Autopairing Logic...
		
		return this;
  }
}
