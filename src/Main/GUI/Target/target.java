package Main.GUI.Target;

import java.awt.Color;
import java.awt.Graphics;

import Main.Mob.Mob;

public class target {

	public boolean render;
	public String name;
	public Mob m;

	public target(String name, Mob m) {
		this.name = name;
		this.m = m;
	}

	public void render(Graphics g) {
		g.drawImage(targetThing.texture, 0, 0, 160 * 2, 80 * 2, null);

		g.setColor(Color.red);
		g.fillRect(154, 24, (int) (m.health * 1.20), 12);
		
		g.drawImage(m.icon, 37, 37, 70, 70, null);
		
	}
}
