package Main;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;

import Flash.Images.FImage;
import Flash.Input.Keyboard;
import FrameWork.Screen;
import Main.Mob.Mob;
import Main.Mob.Player;
import Main.World.Level;

@SuppressWarnings("serial")
public class Game extends Canvas {

	Image test;

	public Screen screen;
	public Keyboard key;
	public Level level;

	public Mob player;

	int x, y;

	public Game(Keyboard key) {
		screen = new Screen(1280, 720, 64);

		level = new Level(screen);

		test = FImage.loadImage("/textures/menu.png");

		player = new Player(5, 5, key);

		this.key = key;

	}

	public void update() {
		key.update();
		if (key.key.get(0))
			y += 5;
		if (key.key.get(1))
			y -= 5;
		if (key.key.get(2))
			x += 5;
		if (key.key.get(3))
			x -= 5;
		
		player.update();
	}

	public void render(Graphics g) {
		screen.setOffset(x, y);
		level.render(g);
		player.render(screen, g);

	}

}
