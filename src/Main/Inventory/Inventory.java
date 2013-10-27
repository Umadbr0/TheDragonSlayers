package Main.Inventory;

import java.awt.Graphics;
import java.util.ArrayList;

import Main.Items.Item;

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
				Slot s = new Slot(tempX, tempY, 49, 49, this);
				slots.add(s);
				tempX = tempX + (49 + 5);

			}
			tempX = x;
			tempY = tempY + (49 + 5);
		}
	}
	


	


	public void addItem(int slotNr, Item i, int amount) {
		slots.get(slotNr).item = i;
		slots.get(slotNr).item.amount = amount;
	}
	
	
	public void render(Graphics g) {
		for (int i = 0; i < slots.size(); i++) {
			slots.get(i).render(g, i);		
		}
	}







}
