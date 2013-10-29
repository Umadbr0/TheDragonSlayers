package Main.Mob;

import java.awt.Graphics;
import java.awt.Image;

import Flash.Button.Mouse;
import Flash.Input.Keyboard;
import FrameWork.Screen;
import Main.Game;
import Main.World.Level;
import Main.projectile.proFire;
import Main.projectile.projectile;

public class Mob {

	
	public int x, y;
	public int speed = 2;
	public Keyboard key;
	public Image sprite;
	public Image icon;
	public boolean moving = false;
	public int dir = 0;
	public String type;
	public Hitbox box;
	public Level level;
	public int id;
	public boolean shoot = false;
	public projectile selectedProjectile;
	public int health = 100;

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

	public float getAngle(float x, float y) {
		return (float) Math.atan2(x - this.x, y - this.y);
	}

	public void updateShooting() {
		if (shoot) {
			projectile p = new proFire(x - Game.x + 28, y - Game.y + 28, getAngle((float) Mouse.mouseX - 32  + Game.x, (float) Mouse.mouseY - 32 + Game.y), this);
			level.projectiles.add(p);
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
