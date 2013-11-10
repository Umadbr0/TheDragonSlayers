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
import Main.GUI.GUIHandeler;
import Main.GUI.Mouse.option.FastOption;
import Main.GUI.Mouse.option.optSpawnMob;
import Main.GUI.Mouse.option.optTarget;
import Main.GUI.Mouse.option.options;
import Main.GUI.Target.targetThing;
import Main.LoadingAndSaving.SinglePlayerLoading;
import Main.Mob.Mob;
import Main.Mob.Player;
import Main.Mob.NPCs.guide;
import Main.Mob.Spawner.Spawner;
import Main.World.Level;
import Main.projectile.projectile;
import Main.quest.Quest;
import Main.quest.QuestHandeler;
import Main.tools.Setups;

@SuppressWarnings("serial")
public class Game extends Canvas {

	/*
	 * Stuff to be done before 2013-11-17
	 */
	// TODO-MessageBox
	// TODO-Mob spawning - Done
	// TODO-Particles
	// TODO-Saving quests
	// TODO-Add more quests

	/*
	 * Stuff to be done before 2013-11-24
	 */
	// TODO-Multiplayer

	Image pointer1;
	public static Image toolTip;

	public Screen screen;
	public Keyboard key;
	public static Level level;

	public static Mob player;

	public FastOption o;

	public static boolean pressingOnGui = false; // If youre pressing on a gui
													// with the mouse.

	public static int fps; // Frames Per Second.

	public String camMode = "follow"; // The mode for the camera.
	public static boolean Running = false;
	public static boolean walkWithMouse = false; // If the player is controlled
													// by mouse or keyboard.
	public static boolean debug = false;

	public boolean clearMobsWithC = false; // If the player should be able to
											// clear all mobs by pressing c.

	public static int reqX; // The x as the mouse requests when you right click.
	public static int reqY; // The y as the mouse requests when you right click.
	public static Image waypoint; // The Image that appear when the player is
									// walking to a location.

	public static int x; // X Offset for the screen and mobs.
	public static int y; // Y Offset for the screen and mobs.

	public Game(Keyboard key) {

		Setups.setupGUIs(); // Setting up all guis.

		screen = new Screen(1280, 720, 64); // Init the screen so we can render
											// stuff.
		level = new Level(screen); // Creates a new level.

		/*
		 * Load all random sprites here:
		 */
		pointer1 = FImage.loadImage("/textures/pointer1.png");
		waypoint = FImage.loadImage("/textures/waypoint.png");
		toolTip = FImage.loadImage("/textures/GUI/toolTip.png");

		// Init the player.
		player = new Player(12 * 64, 14 * 64, key, level);
		level.addMob(player);

		// Loading the player.
		try {
			String[] s = SinglePlayerLoading.loadPlayer(Start.name);
			player.x = Integer.parseInt(s[4]);
			player.y = Integer.parseInt(s[5]);
			// player.xp = Integer.parseInt(s[3]);
			player.health = Double.parseDouble(s[2]);
			QuestHandeler.addCurrentQuest(Quest.tq1);

		} catch (Exception e) {
			String[] a = new String[8];
			a[0] = Start.name;
			a[1] = "level";
			a[2] = Double.toString(player.health);
			a[3] = Integer.toString((int) player.xp);
			a[4] = Integer.toString(player.x);
			a[5] = Integer.toString(player.y);
			a[6] = "mana";
			a[7] = "true";
			QuestHandeler.addCurrentQuest(Quest.tq1);

			SinglePlayerLoading.savePlayer(a);
		}

		// Init the guide.
		Mob m = new guide(4 * 64, 5 * 64, level, 1);
		level.addMob(m);

		/*
		 * Creates the fast options menu, first an array with all options and
		 * then add them to the menu.
		 */
		options[] oo = new options[4];

		oo[3] = new optSpawnMob(FImage.loadImage("/textures/s.png"));
		oo[1] = new optTarget(FImage.loadImage("/textures/pointer1.png"));

		o = new FastOption(oo, key);

		new targetThing();
		// Init the key.
		this.key = key;

//		Spawner.addSpawner(15 * 64, 15 * 64, 5, 120, 0, "dragonBaby");

	}

	int mox, moy;
	boolean t2;
	boolean t1;

	public void update() {

		Spawner.updateAll();
		QuestHandeler.update();

		if ((key.key.get(11)) && !t2) {
			GUIHandeler.setShow("Quest", !GUIHandeler.getShow("Quest"));
			QuestHandeler.updateCurrentQuestsToGUI();
			t2 = true;
		} else if (!(key.key.get(11)))
			t2 = false;

		if ((key.key.get(12)) && !t1) {
			debug = !debug;
			String[] a = new String[8];
			a[0] = Start.name;
			a[1] = "level";
			a[2] = Double.toString(player.health);
			a[3] = "xp";
			a[4] = Integer.toString(player.x);
			a[5] = Integer.toString(player.y);
			a[6] = "mana";
			a[7] = "false";

			SinglePlayerLoading.savePlayer(a);
			t1 = true;
		} else if (!(key.key.get(12)))
			t1 = false;

		if (key.key.get(9)) {

		}

		GUIHandeler.update();

		if (player.dead)
			Start.state = Start.State.DEAD;
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
			y = player.y + 64 - Start.height / 2;
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

		o.update();

	}

	double barLenght;

	public void render(Graphics g) {

		screen.setOffset(-x, -y);
		level.render(-x, -y, g);

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

		GUIHandeler.render(g);
		
		targetThing.render(g);

		if (debug) {
			g.setColor(new Color(255, 255, 255));
			g.setFont(new Font("Verdana", 1, 10));
			g.drawString("Cam mode: " + camMode, 0, 10);
			g.drawString("Mobs: " + (level.mobs.size() + 1), 0, 20);
			g.drawString("FPS: " + fps, 0, 30);
			g.drawString("Mobs rendered: " + Level.renderedMobs, 0, 40);
			g.drawString("Entitys Rendered: " + Level.renderedEntitys, 0, 50);
			g.drawString("Running: " + Running, 0, 60);
			g.drawString("Targeted: " + targetThing.targeted, 0, 70);
		}
		if (player.health > 0)

			barLenght = 214.0 / 100.0;
		GUIHandeler.fillRect("Bars", 14, 39, (int) (player.xp * (barLenght)), 15, Color.green);
		GUIHandeler.drawString("Bars", (int) (player.xp) + "/" + 100, 14, 50, Color.black);

		barLenght = 214.0 / player.maxHealth;
		GUIHandeler.fillRect("Bars", 14, 15, (int) (player.health * (barLenght)), 15, Color.red);
		GUIHandeler.drawString("Bars", (int) (player.health) + "/" + (int) player.maxHealth, 14, 26, Color.black);
		
		
	}

}
