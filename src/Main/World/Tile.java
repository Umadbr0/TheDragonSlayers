package Main.World;

import java.awt.Graphics;
import java.awt.Image;

import Flash.Images.FImage;
import FrameWork.Screen;
import Main.World.Tiles.tileGrass;
import Main.World.Tiles.tileSand;
import Main.World.Tiles.tileWater;
import Main.World.Tiles.tilePlank;
import Main.World.Tiles.tileDoor;

public class Tile {

	public String name;
	public Image texture;
	public int color;
	
	/*
	 * Register tiles below.
	 */
	public static Tile air = new Tile("Air");
	public static Tile grass = new tileGrass("Grass", "/textures/Tiles/grass.png", 0xff4CFF00);
	public static Tile sand = new tileSand("Sand", "/textures/Tiles/sand.png", 0xffFFD800);
	public static Tile water = new tileWater("Water","/textures/Tiles/water.png", 0xff0094FF);
	public static Tile plank = new tilePlank("Water","/textures/Tiles/plank.png", 0xff7F3300);
	public static Tile door = new tilePlank("Door","/textures/Tiles/door.png", 0xff7F0000);
	
	
	public Tile(String n, String path, int color) {
		texture = FImage.loadImage(path);
		this.name = n;
		this.color = color;
	}
	
	public Tile(String n) {
		this.name = n;
	}
	
	
	public void render(Graphics g, Screen s) {
		
	}
	
	public void render(int x, int y,Graphics g, Screen s) {
		
	}
	
	
	
}
