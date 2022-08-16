package DesignPattern.example.decorator;

public class PercentHudRenderer extends HudDecorator{
	public PercentHudRenderer(HudRenderer hudRenderer) {
		super(hudRenderer);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render() {
		super.render();
		percentHudRendering();
	}
	
	private void percentHudRendering() {
		
	}
}
