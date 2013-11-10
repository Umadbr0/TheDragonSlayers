package Main.Mob.Spawner;

import java.util.Random;

import Main.Game;
import Main.Mob.Mob;
import Main.Mob.dragons.dragonBaby;

public class WorldSpawner {

	public static Random r = new Random();

	static int i = 0;

	static int x, y;

	public static void update() {
		i++;

		if (i > 10) {
			i = 0;

			x = r.nextInt(Game.level.width * 64);
			y = r.nextInt(Game.level.height * 64);
			if (!Game.level.getTile(x / 64, y / 64).solid()) {


//						System.out.println(x / 64 + ", " + y / 64);

						Mob m = new dragonBaby(x, y, Game.level, 4);
						Game.level.addMob(m);


			}
		}
	}
}
