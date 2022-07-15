package DesignPattern.example.decorator;

public class Main {
  public static void main(String[] args) {
    HudRenderer hudRenderer = new HudRenderer(new Hp(300, 15), new Mp(400, 15));
    hudRenderer.Rendering();

    System.out.println();
    System.out.println();

    HudRenderingDecorator hudRenderingWithPercent = new HudRenderingWithPercent(hudRenderer);
    hudRenderingWithPercent.Rendering();

    System.out.println();
    System.out.println();

    HudRenderingDecorator hudRenderingWithWarning = new HudRenderingWithWarning(hudRenderer);
    hudRenderingWithWarning.Rendering();
    
    System.out.println();
    System.out.println();
    
    HudRenderingDecorator hudRenderingWithPercentAndWarning =
        new HudRenderingWithWarning(new HudRenderingWithPercent(hudRenderer));
    hudRenderingWithPercentAndWarning.Rendering();
  }
}
