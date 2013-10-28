package Main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import Flash.Button.FFunc;
import Flash.Button.Mouse;
import Flash.Images.FImage;
import Flash.Input.Keyboard;
import FrameWork.Screen;
import Main.GUI.Mouse.option.FastOption;
import Main.GUI.Mouse.option.optClearMob;
import Main.GUI.Mouse.option.optSpawnMob;
import Main.GUI.Mouse.option.optWalkThere;
import Main.GUI.Mouse.option.options;
import Main.Inventory.Inventory;
import Main.Mob.Mob;
import Main.Mob.Player;
import Main.Mob.mobOne;
import Main.World.Level;

@SuppressWarnings("serial")
public class Game extends Canvas {

	Image hud;
	Image pointer1;

	public Screen screen;
	public Keyboard key;
	public static Level level;

	public Mob player;

	public FastOption o;

	public Inventory inv;

	public static int fps;

	public String camMode = "follow"; // The mode for the camera.
	public static boolean walkWithMouse = false; // If the player is controlled
													// by mouse or keyboard.

	public boolean clearMobsWithC = true; // If the player should be able to
											// clear all mobs by pressing c.

	public static int reqX; // The x as the mouse requests when you right click.
	public static int reqY; // The y as the mouse requests when you right click.
	public static Image waypoint; // The Image that appear when the player is
									// walking to a location.

	public static int x; // X Offset for the screen and mobs.
	public static int y; // Y Offset for the screen and mobs.

	public Game(Keyboard key) {
		screen = new Screen(1280, 720, 64); // Init the screen so we can render
											// stuff.
		level = new Level(screen); // Creates a new level.

		/*
		 * Load all random sprites here:
		 */
		hud = FImage.loadImage("/textures/hud.png");
		pointer1 = FImage.loadImage("/textures/pointer1.png");
		waypoint = FImage.loadImage("/textures/waypoint.png");

		// Init the player.
		player = new Player(5 * 64, 5 * 64, key, level);

		for (int i = 0; i < 1; i++) {
			Mob m = new mobOne(5 * 64, 5 * 64, level, level.mobs.size());
			level.addMob(m);
			System.out.println("Added mob: " + i);
		}

		/*
		 * Creates the fast options menu, first an array with all options and
		 * then add them to the menu.
		 */
		options[] oo = new options[4];
		if (walkWithMouse)
			oo[1] = new optWalkThere(FImage.loadImage("/textures/arrow.png"));
		oo[3] = new optSpawnMob(FImage.loadImage("/textures/s.png"));
		oo[2] = new optClearMob(FImage.loadImage("/textures/c.png"));

		o = new FastOption(oo, key);

		// Init the key.
		this.key = key;

	}

	int mox, moy;

	public void update() {
		key.update();

		if (clearMobsWithC)
			if (key.key.get(9))
				level.mobs.clear();

		if (camMode.equalsIgnoreCase("followHalf")) {
			if (player.x < x)
				x -= player.speed;
			if (player.y < y)
				y -= player.speed;
			if (player.x + 64 > x + 1280)
				x += player.speed;
			if (player.y + 64 > y + 475)
				y += player.speed;
		}

		if (camMode.equalsIgnoreCase("follow")) {
			x = player.x - Start.width / 2;
			y = player.y - 180;
		}

		if (camMode.equalsIgnoreCase("mouse"))
			if (FFunc.mouseCheckRight(0, 0, 1280, 480)) {
				if (Mouse.mouseX < mox)
					x -= (Math.abs(mox - Mouse.mouseX) / 10);
				if (Mouse.mouseX > mox)
					x += (Math.abs(mox - Mouse.mouseX) / 10);
				if (Mouse.mouseY < moy)
					y -= (Math.abs(moy - Mouse.mouseY) / 10);
				if (Mouse.mouseY > moy)
					y += (Math.abs(moy - Mouse.mouseY) / 10);

			}

		level.update();

		player.update();
		o.update();

	}

	public void render(Graphics g) {

		screen.setOffset(-x, -y);
		level.render(-x, -y, g);
		player.render(screen, g);

		if (camMode.equalsIgnoreCase("mouse"))
			if (FFunc.mouseCheckRight(0, 0, 1280, 480)) {
				g.setColor(Color.blue);
				g.fillOval(mox - 15, moy - 15, 30, 30);
				g.drawLine(mox, moy, Mouse.mouseX, Mouse.mouseY);
				g.fillOval(Mouse.getX() - 5, Mouse.getY() - 5, 10, 10);
			} else {
				mox = Mouse.mouseX;
				moy = Mouse.mouseY;
			}

		o.render(g);
		g.drawImage(hud, 0, 0, 1274, 672, null);
		g.setColor(new Color(25, 25, 25));
		g.setFont(new Font("Verdana", 1, 20));
		g.drawString("Cam mode: " + camMode, 1010, 510);
		g.drawString("Mobs: " + (level.mobs.size() + 1), 1010, 540);
		g.drawString("FPS: " + fps, 1010, 570);
		g.drawString("Mobs rendered: " + Level.renderedMobs, 1010, 600);
//		g.drawString("Tiles rendered: " + Level.renderedTiles, 1010, 630);

	}

}
