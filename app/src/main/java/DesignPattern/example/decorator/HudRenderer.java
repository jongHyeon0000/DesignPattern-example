package DesignPattern.example.decorator;

public abstract class HudRenderer implements Renderable {
	protected HP hp;
	protected MP mp;
	
	public HudRenderer(HP hp, MP mp) {
		this.hp = hp;
		this.mp = mp;
	}
	
	public HP getHp() {
		return hp;
	}

	public MP getMp() {
		return mp;
	}

}
