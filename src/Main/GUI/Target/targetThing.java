package Main.GUI.Target;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import Flash.Images.FImage;
import Main.Mob.Mob;
import Main.projectile.projectile;

public class targetThing {

	public static ArrayList<target> t = new ArrayList<target>();

	public static Image texture;

	public targetThing() {
		texture = FImage.loadImage("/textures/targetDisplay.png");
	}

	public static void addTarget(String name, Mob m) {
		target targ = new target(name, m);
		t.add(targ);
	}

	public static void update() {
		for (int i = 0; i < t.size(); i++) {
//			System.out.println(projectile.targeted + ", " + t.get(i).m);
			if (projectile.targeted == t.get(i).m) {
				
				t.get(i).render = true;
			} else {
				t.get(i).render = false;
			}
		}
	}

	public static void render(Graphics g) {
		for (int i = 0; i < t.size(); i++) {
			if (t.get(i).render) {
				t.get(i).render(g);
			}

		}
	}

}
