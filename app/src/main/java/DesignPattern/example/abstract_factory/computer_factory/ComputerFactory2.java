package DesignPattern.example.abstract_factory.computer_factory;

import java.util.Objects;

public final class ComputerFactory2 {
  public enum Compony {
    HANSUNG, MSI
  }
  
  private static volatile ComputerFactory HANSUNG_COMPUTER_FACTORY;
  private static volatile ComputerFactory MSI_COMPUTER_FACTORY;

  public static ComputerFactory getComputerFactory(Compony compony) { 
    switch (compony) {
      case HANSUNG:
        synchronized(HansungComputerFactory.class) {          
          if (Objects.isNull(HANSUNG_COMPUTER_FACTORY)) {
            HANSUNG_COMPUTER_FACTORY = new HansungComputerFactory();
          }
        }
        return HANSUNG_COMPUTER_FACTORY;

      case MSI:
        synchronized(MsiComputerFactory.class) {          
          if (Objects.isNull(MSI_COMPUTER_FACTORY)) {
            MSI_COMPUTER_FACTORY = new MsiComputerFactory();
          }
        }
        return MSI_COMPUTER_FACTORY;

      default:
        throw new IllegalStateException();
    }
  }
}
