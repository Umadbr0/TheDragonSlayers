package FrameWork;

import java.awt.Graphics;
import java.awt.Image;

public class Screen {

	public int WIDTH, HEIGHT;
	public int xOffset, yOffset;
	public int spriteSize;
	
	public int playerWidth, playerHeight;

	public Screen(int width, int height, int spriteSize) {
		WIDTH = width;
		HEIGHT = height;
		this.spriteSize = spriteSize;
	}

	public void render(int x, int y, Image sprite, Graphics g) {

		g.drawImage(sprite, x * spriteSize + xOffset, y * spriteSize + yOffset, spriteSize, spriteSize, null);
	}

	public void renderPlayer(Graphics g, int x, int y, Image sprite) {
		
		int scale = 1;
		
		playerWidth = 64 * scale;
		playerHeight = 64 * scale;
		
		x += xOffset;
		y += yOffset;
		if (x < 1280 && y < 480)
			if (x + playerWidth > 0 && y + playerHeight > 0)
				g.drawImage(sprite, x, y, playerWidth, playerHeight, null);
	}

	public void setOffset(int x, int y) {
		xOffset = x;
		yOffset = y;
	}

	public void renderTile(int x, int y, Image texture, Graphics g) {
		g.drawImage(texture, x * spriteSize + xOffset, y * spriteSize + yOffset, spriteSize, spriteSize, null);
	}

}
