package Main.World;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import FrameWork.Screen;
import Main.Mob.Mob;
import Main.projectile.projectile;

public class Level {

	
	//An array of all mobs currently in that level.
	public ArrayList<Mob> mobs = new ArrayList<Mob>();
	
	//An array with all projectiles loaded in the world.
	public ArrayList<projectile> projectiles = new ArrayList<projectile>();
	
	// The array that stores every tile.
	public int[] tiles;

	
	//The screen as is used for rendering.
	private final Screen s;

	//The level dimensions.
	public int width, height;
	
	// Rendered mobs in the level.
	public static int renderedMobs;

	// Rendered tiles in the level.
	public static int renderedTiles;
	
	// Rendered projectiles in the level.
	public static int renderedEntitys;
	
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
	public void addProjectile(projectile p) {
		projectiles.add(p);
	}
	
	
	public boolean isMobAlive(Mob m) {
		for (int i = 0; i < mobs.size(); i++) {
			if (mobs.get(i) == m) return true;
		}
		return false;
	}
	
	
	/*
	 * The main render method for the level and all the mobs in it.
	 * Might add some config for what mobs to render.
	 */
	public void render(int xScroll, int yScroll, Graphics g) {

		renderedMobs = 0;
		renderedTiles = 0;
		renderedEntitys = 0;
		
		int x0 = -xScroll / 64;
		int y0 = -yScroll / 64;
		

		for (int y = y0; y < y0 + 9; y++) {
			for (int x = x0; x < x0 + 21; x++) {
				getTile(x, y).render(x, y, g, s);
				renderedTiles++;
			}
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).render(s, g);
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
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
		}
		for (int i = 0; i < mobs.size(); i++) {
			mobs.get(i).update();
		}
	}

	
	
	/*
	 * Use this to get a specific tile by its coords.
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
		if (tiles[x + y * width] == Tile.houseOne.color)
			return Tile.houseOne;
		} catch(Exception e) {
		}
		return Tile.air;

	}

}
