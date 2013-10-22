package Main.World;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import FrameWork.Screen;

public class Level {

	// The array that stores every tile.
	public int[] tiles;

	private final Screen s;

	public int width, height;

	public Level(Screen screen) {
		loadLevel("/level.png");
		s = screen;
	}

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

	public void render(int xScroll, int yScroll, Graphics g) {

//		System.out.println(-xScroll / 64 + ", " + -yScroll / 64);

		int x0 = -xScroll / 64;
		int y0 = -yScroll / 64;
		

		for (int y = y0; y < y0 + 9; y++) {
			for (int x = x0; x < x0 + 16; x++) {
				getTile(x, y).render(x, y, g, s);
			}
		}
	}

	public Tile getTile(int x, int y) {
		if ((x < 0) || (x > width) || (y < 0) || (y > height))
			return Tile.air;

//		System.out.println(tiles[x + y * width] + ", " + x + ", " + y);

		try {
		if (tiles[x + y * width] == Tile.grass.color)
			return Tile.grass;
		if (tiles[x + y * width] == Tile.sand.color)
			return Tile.sand;
		} catch(Exception e) {
//			System.err.println("Render fail!");
		}
		return Tile.air;

	}

}
