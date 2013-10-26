package Main.Inventory;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import Flash.Button.FFunc;
import Main.Items.*;

public class Slot {

	public int x;
	public int y;
	public int xa;
	public int ya;
	public Inventory inv;
	public int slotNr;
	public static int slots;
	public Item item;

	public Slot(int x, int y, int xa, int ya, Inventory i) {
		this.x = x;
		this.y = y;
		this.xa = xa;
		this.ya = ya;
		inv = i;
		slotNr = slots;
		slots++;
	}
	
	public void addItem(Item item) {
		this.item = item;
	}

	public void render(Graphics g, int slotNr) {
		if (FFunc.mouseCheckPos(x, y, xa, ya))
			g.setColor(Color.LIGHT_GRAY);
		else
			g.setColor(Color.GRAY);

		g.fill3DRect(x, y, xa, ya, true);
//		if (item.loadedItems.get(slotNr) != null) {
//			g.drawImage(item.loadedItems.get(slotNr).texture, x + 4, y + 4, 41, 41, null);
//			g.setColor(Color.WHITE);
//			g.setFont(new Font("Verdana", 1, 15));
//			if (item.loadedItems.get(slotNr).amount != 0) g.drawString(item.loadedItems.get(slotNr).amount + "", x + 25, y + 45);
//		}
	}
}
