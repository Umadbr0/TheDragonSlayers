package Main;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;

import Flash.Images.FImage;
import Flash.Input.Keyboard;
import FrameWork.Screen;

@SuppressWarnings("serial")
public class Game extends Canvas {

	Image test;
	
	public Screen screen;
	public Keyboard key;

	int x, y;

	public Game(Keyboard key) {
		screen = new Screen(1280, 720, 64);

		test = FImage.loadImage("/textures/menu.png");
		
		this.key = key;

	}

	public void update() {
		key.update();
		if (key.key.get(0))
			y++;
		if (key.key.get(1))
			y--;
		if (key.key.get(2))
			x++;
		if (key.key.get(3))
			x--;

	}

	public void render(Graphics g) {

		screen.setOffset(x, y);

		screen.render(0, 0, test, g);

		screen.render(1, 1, test, g);
		screen.render(2, 1, test, g);
		screen.render(3, 3, test, g);

	}

}
