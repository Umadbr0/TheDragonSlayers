package Main.World.Tiles;

import java.awt.Graphics;

import FrameWork.Screen;
import Main.World.Tile;

public class tilePlank extends Tile {

	public tilePlank(String n, String path, int color) {
		super(n, path, color);
	}
	
	

	@Override
	public void render(int x, int y, Graphics g, Screen s) {
		s.renderTile(x, y, this.texture, g);
	}
	
	public boolean solid() {
		return true;
	}

}
