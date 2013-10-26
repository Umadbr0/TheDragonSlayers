package Main.Items;

import java.awt.Image;
import java.util.ArrayList;

import Flash.Images.FImage;

public class Item {

	public static ArrayList<Item> registerdItems = new ArrayList<Item>();

	public String name;
	public Image texture;
	public int amount = 0;

	

	public Item() {
		addItem("Test", "/textures/Items/test.png");
		addItem("Stick", "/textures/Items/stick.png");
	}
	
	
	public static void addItem(String name, String tex) {
		Item i = new Item(name, tex);
		registerdItems.add(i);
	}
	
	
	
	public Item(String name, String tex) {
		this.name = name;
		texture = FImage.loadImage(tex);
	}
	
	

	public static Item getItem(String itemName) {
		for (int i = 0; i < registerdItems.size(); i++) {
			if (registerdItems.get(i).name.equals(itemName))
				return registerdItems.get(i);
		}
		return null;
	}

}
