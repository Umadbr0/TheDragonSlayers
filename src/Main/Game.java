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
import Main.Inventory.Inventory;
import Main.Items.Item;
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
	public Level level;

	public Mob player;

	public Inventory inv;

	public String camMode = "mouse"; // The mode for the camera.
	public static boolean walkWithMouse = true; // If the player is controlled by mouse or keyboard.
	public static int reqX; // The x as the mouse requests when you right click.
	public static int reqY; // The y as the mouse requests when you right click.
	public static Image waypoint; // The Image that appear when the player is walking to a location.

	public static int x; // X Offset for the screen and mobs.
	public static int y; // Y Offset for the screen and mobs.
	

	public Game(Keyboard key) {
		screen = new Screen(1280, 720, 64);

		
		level = new Level(screen);

		hud = FImage.loadImage("/textures/hud.png");

		pointer1 = FImage.loadImage("/textures/pointer1.png");
		waypoint = FImage.loadImage("/textures/waypoint.png");
		
		
		player = new Player(5, 5, key);

		for (int i = 0; i < 5; i++) {
			Mob mob = new mobOne(64, 64 * i);
			level.addMob(mob);
		}

		inv = new Inventory(993, 28, 5, 4); 


		level.addItem(Item.saphire);
		

		inv.addItem(4, level.items.get(0));
		
		
		this.key = key;

	}

	int mox, moy;

	public void update() {
		key.update();

		if (camMode.equalsIgnoreCase("followHalf")) {
			if (player.x < x)
				x -= player.speed;
			if (player.y < y)
				y -= player.speed;
			if (player.x + 50 * 2 > x + 960)
				x += player.speed;
			if (player.y + 75 * 2 > y + 480)
				y += player.speed;
		}

		if (camMode.equalsIgnoreCase("follow")) {
			x = player.x - 450;
			y = player.y - 180;
		}

		if (camMode.equalsIgnoreCase("mouse"))
			if (FFunc.mouseCheckLeft(0, 0, 960, 480)) {
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
		
		if (walkWithMouse) {
			if (FFunc.mouseCheckRight(0, 0, 960, 480)) {
				reqX = Mouse.getX() + x - 32;
				reqY = Mouse.getY() + y - 32;
			}
		}
		
		
		
	}

	public void render(Graphics g) {

		screen.setOffset(-x, -y);
		level.render(-x, -y, g);
		player.render(screen, g);

		if (camMode.equalsIgnoreCase("mouse"))
			if (FFunc.mouseCheckLeft(0, 0, 960, 480)) {
				g.setColor(Color.blue);
				g.fillOval(mox - 15, moy - 15, 30, 30);
				g.drawLine(mox, moy, Mouse.mouseX, Mouse.mouseY);
			} else {
				mox = Mouse.mouseX;
				moy = Mouse.mouseY;
			}

		if (walkWithMouse) {
			if (FFunc.mouseCheckRight(0, 0, 960, 480)) {
				g.drawImage(pointer1, Mouse.getX() - 32, Mouse.getY() - 32, null);
			}
		}
		g.drawImage(hud, 0, 0, null);
		g.setColor(Color.yellow);
		g.setFont(new Font("Verdana", 1, 20));
		g.drawString("Cam mode: " + camMode, 970, 510);

		// FGrid.addRandomGrid(g, 993, 28, 49, 49, 5, 4, 5, false);

		inv.render(g);

	}

}
