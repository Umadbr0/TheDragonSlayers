package Main.World.Tiles.TileGroups;

import java.awt.Graphics;
import java.awt.Image;

import Flash.Images.FImage;
import FrameWork.Screen;
import Main.World.Tile;

public class houseOne extends Tile {

	public Image left;
	public Image front;
	
	public houseOne(String string, String string2, int i) {
		super(string, string2, i);
		left = FImage.loadImage("/textures/Tiles/houseOne/left1.png");
		front = FImage.loadImage("/textures/Tiles/houseOne/front1.png");

	}

	public void render(int x, int y, Graphics g, Screen s) {
		s.renderTile(x, y, texture, g);
		s.renderTile(x, y - 1, left, g);
		s.renderTile(x-1, y, front, g);
		s.renderTile(x + 2, y, front, g);
	}

}
