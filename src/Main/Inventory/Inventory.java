package Main.Inventory;

import java.awt.Graphics;
import java.util.ArrayList;

public class Inventory {

	public ArrayList<Slot> slots = new ArrayList<Slot>();
	public int x;
	public int y;
	public int xSlots;
	public int ySlots;

	public Inventory(int x, int y, int xSlots, int ySlots) {
		this.x = x;
		this.y = y;
		this.xSlots = xSlots;
		this.ySlots = ySlots;
		int tempX = x;
		int tempY = y;
		for (int b = 0; b < ySlots; b++) {
			for (int a = 0; a < xSlots; a++) {
				Slot s = new Slot(tempX, tempY, 50, 50);
				slots.add(s);
				tempX = tempX + (50 + 5);
			}
			tempX = x;
			tempY = tempY + (50 + 5);
		}
	}
	
	public void render(Graphics g) {
		for (int i = 0; i < slots.size(); i++) {
			slots.get(i).render(g);
		}
	}

}
