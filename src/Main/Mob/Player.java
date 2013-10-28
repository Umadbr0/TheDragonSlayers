package Main.Mob;

import java.awt.Graphics;
import java.awt.Image;

import Flash.Graphics.Animation.Animation;
import Flash.Images.FImage;
import Flash.Input.Keyboard;
import FrameWork.Screen;
import Main.Game;
import Main.World.Level;

public class Player extends Mob {

	public Animation a;
	int anim;
	public boolean walkingDone = true;

	Image d1;
	Image d2;
	Image d3;
	Image l1;
	Image l2;
	Image l3;
	Image r1;
	Image r2;
	Image r3;
	Image u1;
	Image u2;
	Image u3;

	public Player(int x, int y, Keyboard key, Level l) {
		super(x, y, key, l);
		type = "player";
		sprite = FImage.loadImage("/textures/mobs/" + type + "/Down2.png");
		d1 = FImage.loadImage("/textures/mobs/" + type + "/Down1.png");
		d2 = FImage.loadImage("/textures/mobs/" + type + "/Down2.png");
		d3 = FImage.loadImage("/textures/mobs/" + type + "/Down3.png");
		l1 = FImage.loadImage("/textures/mobs/" + type + "/Left1.png");
		l2 = FImage.loadImage("/textures/mobs/" + type + "/Left2.png");
		l3 = FImage.loadImage("/textures/mobs/" + type + "/Left3.png");
		r1 = FImage.loadImage("/textures/mobs/" + type + "/Right1.png");
		r2 = FImage.loadImage("/textures/mobs/" + type + "/Right2.png");
		r3 = FImage.loadImage("/textures/mobs/" + type + "/Right3.png");
		u1 = FImage.loadImage("/textures/mobs/" + type + "/Up1.png");
		u2 = FImage.loadImage("/textures/mobs/" + type + "/Up2.png");
		u3 = FImage.loadImage("/textures/mobs/" + type + "/Up3.png");
		speed = 5;

		box = new Hitbox(x, y, 64, 64);

	}

	int xa, ya;
	int i = 1;

	public void update() {

		updateShooting();
		
		for (int i = 0; i < level.mobs.size(); i++) {
			if (box.collision(level.mobs.get(i).box)) {
				level.mobs.get(i).remove();
			}
		}

		box.set(x - Game.x, y - Game.y);

		if (i > 8) {
			if (anim < 3) {
				anim++;
			} else
				anim = 1;
			i = 0;
		}
		i++;

		if (!Game.walkWithMouse) {
			if (key.key.get(4))
				ya = -speed;
			if (key.key.get(5))
				ya = speed;
			if (key.key.get(6))
				xa = -speed;
			if (key.key.get(7))
				xa = speed;
		} else {
			if (Game.reqX < x - speed)
				xa = -speed;
			if (Game.reqX > x + speed)
				xa = speed;
			if (Game.reqY < y - speed)
				ya = -speed;
			if (Game.reqY > y + speed)
				ya = speed;
			if (x + 32 > Game.reqX && x - 32 < Game.reqX && y + 32 > Game.reqY && y - 32 < Game.reqY)
				walkingDone = true;
			else
				walkingDone = false;
		}
			if(key.key.get(10)){
				speed = 8;
		}
		else
		{
				speed = 5;
		}
		

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			moving = true;
		} else {
			moving = false;
			anim = 2;
			i = 0;
		}

		ya = 0;
		xa = 0;

	}
	
	
	

	public void render(Screen s, Graphics g) {
		if (dir == 1) {
			if (anim == 1)
				sprite = d1;
			if (anim == 2)
				sprite = d2;
			if (anim == 3)
				sprite = d3;
		}
		if (dir == 3) {
			if (anim == 1)
				sprite = l1;
			if (anim == 2)
				sprite = l2;
			if (anim == 3)
				sprite = l3;
		}
		if (dir == 2) {
			if (anim == 1)
				sprite = r1;
			if (anim == 2)
				sprite = r2;
			if (anim == 3)
				sprite = r3;
		}
		if (dir == 0) {
			if (anim == 1)
				sprite = u1;
			if (anim == 2)
				sprite = u2;
			if (anim == 3)
				sprite = u3;
		}
		

		s.renderPlayer(g, x, y, sprite);
		if (!walkingDone)
			g.drawImage(Game.waypoint, Game.reqX - Game.x, Game.reqY - Game.y - 64, null);

		box.render(g);

	}

}
