package FrameWork;

import java.awt.Graphics;
import java.awt.Image;

public class Screen {

	public int WIDTH, HEIGHT;
	public int xOffset, yOffset;
	public int spriteSize;

	public Screen(int width, int height, int spriteSize) {
		WIDTH = width;
		HEIGHT = height;
		this.spriteSize = spriteSize;
	}

	public void render(int x, int y, Image sprite, Graphics g) {

		
		
		g.drawImage(sprite, x * spriteSize + xOffset, y * spriteSize + yOffset,
				spriteSize, spriteSize, null);
	}
	
	public void renderPlayer(Graphics g, int x, int y, Image sprite) {
		x += xOffset;
		y += yOffset;
		g.drawImage(sprite, x, y, 50 * 2, 75 * 2, null);
	}

	public void setOffset(int x, int y) {
		xOffset = x;
		yOffset = y;
	}

	public void renderTile(int x, int y, Image texture, Graphics g) {
		g.drawImage(texture, x * spriteSize + xOffset, y * spriteSize + yOffset,
				spriteSize, spriteSize, null);
	}



}
