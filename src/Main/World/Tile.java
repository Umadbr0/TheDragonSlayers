package Main.World;

import java.awt.Graphics;
import java.awt.Image;

import Flash.Images.FImage;
import FrameWork.Screen;
import Main.World.Tiles.tileGrass;
import Main.World.Tiles.tileSand;

public class Tile {

	public String name;
	public Image texture;
	public int color;
	
	/*
	 * Register tiles below.
	 */
	public static Tile air = new Tile("Air");
	public static Tile grass = new tileGrass("Grass",
			"/textures/Tiles/grass.png", 0xff4CFF00);
	public static Tile sand = new tileSand("Sand", "/textures/Tiles/sand.png", 0xffFFD800);
	
	
	
	
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
