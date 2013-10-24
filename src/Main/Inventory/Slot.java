package Main.Inventory;

import java.awt.Color;
import java.awt.Graphics;

import Flash.Button.FFunc;

public class Slot {

	public int x;
	public int y;
	public int xa;
	public int ya;

	public Slot(int x, int y, int xa, int ya) {
		this.x = x;
		this.y = y;
		this.xa = xa;
		this.ya = ya;
	}

	public void render(Graphics g) {
		if (FFunc.mouseCheckPos(x, y, xa, ya))
			g.setColor(Color.LIGHT_GRAY);
		else
			g.setColor(Color.BLACK);
		
		g.fillRect(x, y, xa, ya);
	}

}
