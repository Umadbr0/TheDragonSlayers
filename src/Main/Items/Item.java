package Main.Items;

import java.awt.Image;

import Flash.Images.FImage;

public class Item {
	
	public String name;
	public Image texture;
	public int amount = 0;
	public int inWorldId = 0;
	
	
	
	//Register items here:
	
	public static Item saphire = new Item("Saphire", "/textures/Items/test.png");
	
	
	
	
	
	public Item(String name, String path) {
		this.name = name;
		texture = FImage.loadImage(path);
	}
	
}
