package Main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import Flash.Button.FFunc;
import Flash.Button.FGrid;
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

	public Screen screen;
	public Keyboard key;
	public Level level;

	public Mob player;

	public Inventory inv;

	public String camMode = "mouse";

	int x, y;
	
	public Item item;

	public Game(Keyboard key) {
		screen = new Screen(1280, 720, 64);

		
		level = new Level(screen);

		hud = FImage.loadImage("/textures/hud.png");

		player = new Player(5, 5, key);

		for (int i = 0; i < 5; i++) {
			Mob mob = new mobOne(64, 64 * i);
			level.addMob(mob);
		}

		inv = new Inventory(993, 28, 5, 4); 

		item = new Item();
		
		inv.addItem(5, Item.getItem("Test"), 1);
		inv.addItem(1, Item.getItem("Stick"), 28);
		inv.addItem(9, Item.getItem("Stick"), 46);
		inv.addItem(15, Item.getItem("Test"), 11);

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

		g.drawImage(hud, 0, 0, null);
		g.setColor(Color.yellow);
		g.setFont(new Font("Verdana", 1, 20));
		g.drawString("Cam mode: " + camMode, 970, 510);

		// FGrid.addRandomGrid(g, 993, 28, 49, 49, 5, 4, 5, false);

		inv.render(g);

	}

}
