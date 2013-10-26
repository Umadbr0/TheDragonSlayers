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
	public boolean moving = false;
	public int dir = 0;
	public String type;

	public Mob(int x, int y, Keyboard key) {
		this.x = x;
		this.y = y;
		this.key = key;
	}

	public Mob(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void update() {

	}

	public void move(int xv, int yv) {

		if (xv > 0)
			dir = 2;
		if (xv < 0)
			dir = 3;
		if (yv > 0)
			dir = 1;
		if (yv < 0)
			dir = 0;

		x += xv;
		y += yv;

	}

	public void render(Screen s, Graphics g) {

	}

}
