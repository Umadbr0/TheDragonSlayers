package Main.Mob;

import java.awt.Graphics;
import java.awt.Image;

import Flash.Button.FFunc;
import Flash.Input.Keyboard;
import FrameWork.Screen;
import Main.World.Level;

public class Mob {

	public int x, y;
	public int speed = 2;
	public Keyboard key;
	public Image sprite;
	public boolean moving = false;
	public int dir = 0;
	public String type;
	public Hitbox box;
	public Level level;
	public int id;
	public boolean shoot = false;

	public Mob(int x, int y, Keyboard key, Level l) {
		this.x = x;
		this.y = y;
		this.key = key;
		level = l;
	}

	public Mob(int x, int y, Level l, int id) {
		this.x = x;
		this.y = y;
		level = l;
		this.id = id;
	}

	public void updateShooting() {

		if (shoot) {
			
			
			
			shoot = false;
		}
	}

	public void update() {

	}

	public void remove() {
		level.mobs.remove(this);
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
