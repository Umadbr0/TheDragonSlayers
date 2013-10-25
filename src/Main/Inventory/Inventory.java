package Main.Inventory;

import java.awt.Graphics;
import java.util.ArrayList;

import Main.Items.Item;

public class Inventory {

	public ArrayList<Slot> slots = new ArrayList<Slot>();
	public Item[] items;
	public int x;
	public int y;
	public int xSlots;
	public int ySlots;

	public Inventory(int x, int y, int xSlots, int ySlots) {
		items = new Item[xSlots * ySlots];
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
	
	
	
	public void addItem(int slotNr, Item item) {
		items[slotNr] = item;
		items[slotNr].amount = 0;
	}
	
	public void addItem(int slotNr, Item item, int number) {
		items[slotNr] = item;
		items[slotNr].amount = number;
		System.out.println("Item " + item.name + ", " + items[slotNr].amount + " has been added on spot: " + slotNr);
	}
	
	
	public void render(Graphics g) {
		for (int i = 0; i < slots.size(); i++) {
			slots.get(i).render(g, i);		
			String n = items[0].name;
		}
	}

}
