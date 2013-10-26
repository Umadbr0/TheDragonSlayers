package Main.World;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import FrameWork.Screen;
import Main.Items.Item;
import Main.Mob.Mob;

public class Level {

	
	//An array of all mobs currently in that level.
	public ArrayList<Mob> mobs = new ArrayList<Mob>();
	
	//An array with all items loaded in the world.
	public ArrayList<Item> items = new ArrayList<Item>();
	
	// The array that stores every tile.
	public int[] tiles;

	
	//The screen as is used for rendering.
	private final Screen s;

	//The level dimensions.
	public int width, height;

	
	/*
	 * Add a level.
	 */
	public Level(Screen screen) {
		loadLevel("/level.png");
		s = screen;
	}

	
	/*
	 * Loads the level from a png file by passing in the path.
	 * The map resizes to what ever the png file is.
	 */
	private void loadLevel(String string) {
		try {
			BufferedImage image = ImageIO.read(Level.class.getResource(string));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
			System.out.println("[GAME] Level loaded");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Exception! Could not load Level file!");
		}
	}

	/*
	 * Use this to add a mob to the level.
	 */
	public void addMob(Mob m) {
		mobs.add(m);
	}
	
	/*
	 * Use this to add a item to the level.
	 */
	public void addItem(Item i) {
		int j = items.size();
		items.add(i);
		items.get(items.size() - 1).inWorldId = j;
	}
	
	
	
	/*
	 * The main render method for the level and all the mobs in it.
	 * Might add some config for what mobs to render.
	 */
	public void render(int xScroll, int yScroll, Graphics g) {


		int x0 = -xScroll / 64;
		int y0 = -yScroll / 64;
		

		for (int y = y0; y < y0 + 9; y++) {
			for (int x = x0; x < x0 + 16; x++) {
				getTile(x, y).render(x, y, g, s);
			}
		}
		
		for (int i = 0; i < mobs.size(); i++) {
			mobs.get(i).render(s, g);
		}
		
		
	}
	
	/*
	 * Call this at around 60 times a second to update the level and all mobs in it
	 * except the player witch has its own update method.
	 */
	public void update() {
		for (int i = 0; i < mobs.size(); i++) {
			mobs.get(i).update();
		}
	}

	
	
	/*
	 * Use this to get a spesific tile by its coords.
	 */
	public Tile getTile(int x, int y) {
		if ((x < 0) || (x > width) || (y < 0) || (y > height))
			return Tile.air;


		try {
		if (tiles[x + y * width] == Tile.door.color)
			return Tile.door;
		if (tiles[x + y * width] == Tile.plank.color)
			return Tile.plank;
		if (tiles[x + y * width] == Tile.water.color)
			return Tile.water;
		if (tiles[x + y * width] == Tile.grass.color)
			return Tile.grass;
		if (tiles[x + y * width] == Tile.sand.color)
			return Tile.sand;
		} catch(Exception e) {
		}
		return Tile.air;

	}

}
