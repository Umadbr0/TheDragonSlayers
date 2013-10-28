package Main.GUI.Mouse.option;

import java.awt.Image;

import Main.Game;
import Main.Mob.Mob;
import Main.Mob.mobOne;

public class optSpawnMob extends options {
	

	public optSpawnMob(Image icon) {
		super(icon);
	}

	public void ifSelected() {
		Mob mob = new mobOne(FastOption.x + Game.x - 32, FastOption.y + Game.y - 32, Game.level, Game.level.mobs.size());
		Game.level.addMob(mob);
	}

	public void update() {

	}


}
