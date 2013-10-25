package Main.Inventory;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import Flash.Button.FFunc;

public class Slot {

	public int x;
	public int y;
	public int xa;
	public int ya;
	public Inventory inv;
	public int slotNr;
	public static int slots;

	public Slot(int x, int y, int xa, int ya, Inventory i) {
		this.x = x;
		this.y = y;
		this.xa = xa;
		this.ya = ya;
		inv = i;
		slotNr = slots;
		slots++;
	}

	public void render(Graphics g, int slotNr) {
		if (FFunc.mouseCheckPos(x, y, xa, ya))
			g.setColor(Color.LIGHT_GRAY);
		else
			g.setColor(Color.GRAY);

		g.fill3DRect(x, y, xa, ya, true);
		if (inv.items[slotNr] != null) {
			g.drawImage(inv.items[slotNr].texture, x + 4, y + 4, 41, 41, null);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Verdana", 1, 15));
			if (inv.items[slotNr].amount != 0) g.drawString(inv.items[slotNr].amount + "", x + 25, y + 45);
		}
	}
}
