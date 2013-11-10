package Main.projectile;

import java.awt.Graphics;
import java.awt.Image;

import FrameWork.Screen;
import Main.Game;
import Main.GUI.Target.targetThing;
import Main.Mob.Hitbox;
import Main.Mob.Mob;

public class projectile {

	public double x, y;
	public double xOrigin, yOrigin;
	public double xv, yv;
	public double speed = 8;
	public int damage;
	public double range;
	public int fireRate;
	public Image texture;
	public Hitbox box;
	public static Mob firedBy;
	public static Mob targeted;

	public projectile() {

	}

	@SuppressWarnings("static-access")
	public projectile(int x, int y, double angle, Image tex, Mob firedBy) {
		this.firedBy = firedBy;
		this.x = x;
		this.y = y;
		xOrigin = x;
		yOrigin = y;
		xv = speed * Math.cos(-angle + 1.5);
		yv = speed * Math.sin(-angle + 1.5);
		texture = tex;
		box = new Hitbox(x, y, 32, 32);
	}

	Mob m;

	public void onCollision() {
		targeted = m;
		System.out.println("Shooting at: " + targeted + ", " + targeted.hostile);
		if (!targeted.peaceful)
			targeted.health -= damage;
		remove();
	}

	public void targetIsHostile() {
		targeted.targeted = firedBy;
	}

	public void update() {
		x += xv;
		y += yv;
		box.set(x, y);

		for (int i = 0; i < Game.level.mobs.size(); i++) {
			m = Game.level.mobs.get(i);
			if (m != firedBy) {
				if (box.collision(m.box)) {
					onCollision();

					if (targeted.health <= 0)
						firedBy.MobKills++;

					if (targeted.hostile)
						targetIsHostile();


					if (firedBy == Game.player) 
						targetThing.targeted = targeted;
					
				}
			}
		}

		// // Sets the players targeted to the damaged mob.
		// if (firedBy.type.equals("player"))
		// if (Game.level.mobs.get(i) != null)
		// targeted = Game.level.mobs.get(i);
		//
		// remove(); // Removes the projectile.
		// }
		// }
		// }

		// if (firedBy.type.equals("player"))
		// targetThing.targeted = targeted;
		//
		// if (!Game.level.isMobAlive(targeted))
		// targeted = null;
		//
		// if (distance() > range)
		// remove();
	}

	private void remove() {
		Game.level.projectiles.remove(this);
	}

	public double distance() {
		double dist = 0;
		dist = Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y)));
		return dist;
	}

	public void render(Screen s, Graphics g) {
		s.renderEntity(g, (int) x, (int) y, 32, 32, texture);
		box.render(g);
	}

}
