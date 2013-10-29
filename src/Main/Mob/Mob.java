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

	// Mob options:
	public boolean hostile = false; // If the mob should damage the player.
	public boolean peaceful = true; // If you can harm the mob.
	public boolean boss = false; // If its a boss.
	public int speed = 2; // The speed in pixels.
	public int health = 100; // The mob health.
	public String type; // The kind of mob, used for texture path and targeting.

	public Mob targeted;
	
	public int x, y;
	public Keyboard key;
	public Image sprite;
	public Image icon;
	public boolean moving = false;
	public int dir = 0;
	public Hitbox box;
	public Level level;
	public int id;
	public boolean shoot = false;
	public projectile selectedProjectile;

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

	public double getRange(Mob m) {
		double dist = 0;
		dist = Math.sqrt(Math.abs((m.x - x) * (m.x - x) + (m.y - y) * (m.y - y)));
		return dist;
	}
	
	public float getAngle(float x, float y) {
		return (float) Math.atan2(x - this.x, y - this.y);
	}

	public void updateShooting() {
		if (shoot) {
			projectile p = new proFire(x - Game.x + 28, y - Game.y + 28, getAngle((float) Mouse.mouseX - 32 + Game.x, (float) Mouse.mouseY - 32 + Game.y), this);
			level.projectiles.add(p);
			shoot = false;
		}
	}

	public void shoot(projectile p) {
		level.addProjectile(p);
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
