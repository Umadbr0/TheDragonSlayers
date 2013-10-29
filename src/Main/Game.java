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
import Main.GUI.Mouse.option.optTarget;
import Main.GUI.Mouse.option.options;
import Main.GUI.Target.targetThing;
import Main.Mob.Mob;
import Main.Mob.Player;
import Main.Mob.NPCs.guide;
import Main.World.Level;
import Main.projectile.projectile;

@SuppressWarnings("serial")
public class Game extends Canvas {

	Image hud;
	Image pointer1;

	public Screen screen;
	public Keyboard key;
	public static Level level;

	public Mob player;

	public FastOption o;

	public static int fps; // Frames Per Second.

	public String camMode = "follow"; // The mode for the camera.
	public static boolean Running = false;
	public static boolean walkWithMouse = false; // If the player is controlled
													// by mouse or keyboard.
	public static boolean debug = false;

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

		//Init the guide.
		Mob m = new guide(3 * 64, 3 * 64, level, 1);
		level.addMob(m);
		
		/*
		 * Creates the fast options menu, first an array with all options and
		 * then add them to the menu.
		 */
		options[] oo = new options[4];

		oo[3] = new optSpawnMob(FImage.loadImage("/textures/s.png"));
		oo[2] = new optClearMob(FImage.loadImage("/textures/c.png"));
		oo[1] = new optTarget(FImage.loadImage("/textures/pointer1.png"));

		o = new FastOption(oo, key);

		new targetThing();
		// Init the key.
		this.key = key;

	}

	int mox, moy;

	public void update() {
		key.update();
		targetThing.update();

		if (key.key.get(10)) {
			Running = true;
		} else
			Running = false;

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

		if (projectile.targeted != null)
			if (projectile.firedBy.getRange(projectile.targeted) > 500)
				projectile.targeted = null;
		
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
		targetThing.render(g);
		g.drawImage(hud, 0, 0, 1274, 672, null);
		if (debug) {
			g.setColor(new Color(25, 25, 25));
			g.setFont(new Font("Verdana", 1, 10));
			g.drawString("Cam mode: " + camMode, 1010, 510);
			g.drawString("Mobs: " + (level.mobs.size() + 1), 1010, 525);
			g.drawString("FPS: " + fps, 1010, 540);
			g.drawString("Mobs rendered: " + Level.renderedMobs, 1010, 555);
			g.drawString("Entitys Rendered: " + Level.renderedEntitys, 1010, 570);
			g.drawString("Running: " + Running, 1010, 585);
			g.drawString("Targeted: " + projectile.targeted, 1010, 600);
		} else {
			
		}
	}

}
