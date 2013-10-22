package Main.Mob;

import java.awt.Graphics;
import java.awt.Image;

import Flash.Input.Keyboard;
import FrameWork.Screen;

public class Mob {

	public int x, y;
	public int speed = 2;
	public Keyboard key;
	public Image sprite;

	public Mob(int x, int y, Keyboard key) {
		this.x = x;
		this.y = y;
		this.key = key;
	}

	public void update() {

	}

	public void move(int xv, int yv) {
		x += xv;
		y += yv;
	}

	public void render(Screen s, Graphics g) {

	}

}
