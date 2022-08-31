package DesignPattern.example.decorator;

import java.util.Objects;

public class Main {
  public static void main(String[] args) {
    Renderable hudRenderer = new CommonHudRenderer(new HP(1000, 100), new MP(2000, 540));
    hudRenderer.render();

    System.out.printf("\n\n");

    hudRenderer =
        new PercentHudRenderer(new CommonHudRenderer(new HP(1000, 100), new MP(2000, 540)));
    hudRenderer.render();

    System.out.printf("\n\n");

    hudRenderer =
        new WarningHudRenderer(new CommonHudRenderer(new HP(1000, 100), new MP(2000, 540)));
    hudRenderer.render();

    System.out.printf("\n\n");

    hudRenderer = new PercentHudRenderer(
        new WarningHudRenderer(new CommonHudRenderer(new HP(1000, 100), new MP(2000, 540))));
    hudRenderer.render();
  }
}

class Car{
  private static final int MAX_OIL_VALUE = 1000;
  private int oilValue;
  //지붕을 접거나 펼 수 있는 자동차 외형인지의 여부
  private boolean convertible = false;

  Car(int oilValue){
    // 불변식(Invariant)를 유지하기 위한 방어적 아규먼트 체크
    if(oilValue < 0 && oilValue > MAX_OIL_VALUE){ 
      // 불변 클래스(Immutable Class)가 아니라면 예외를 던질 때 
      // 실패 원자성(Failure Atomicity)을 고려해야 한다.
      throw new IllegalArgumentException();
    }
    this.oilValue = oilValue;
  }
  
  public void setOilValue(int oilValue){
    if(oilValue < 0 && oilValue > MAX_OIL_VALUE){ 
      throw new IllegalArgumentException();
    }
    
    this.oilValue = oilValue;
  }
  
  public void setConvertible(boolean convertible){
    this.convertible = Objects.requireNonNull(convertible);
  }
}

class OpenCar extends Car{
  OpenCar(int oilValue){
    super(oilValue);
    super.setConvertible(false);
  }
  
  @Override
  public void setOilValue(int oilValue){
    super.setOilValue(oilValue);
  }
}