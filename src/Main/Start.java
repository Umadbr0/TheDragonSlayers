package Main;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import Flash.Button.Mouse;
import Flash.Input.Keyboard;
import Main.LoadingAndSaving.SinglePlayerLoading;

@SuppressWarnings("serial")
public class Start extends Canvas implements Runnable {

	/*
	 * This class is made by v7_FlasH. Last updated 2013-06-14.
	 */

	public enum State {
		MENU, GAME, DEAD
	}

	public static int width = 1280;
	public static int height = 700;
	public final static String TITLE = "The Dragon Slayers ALPHA";
	public final static String VERSION = "0.1";
	
	public static String name = "robin";

	// Booleans
	private boolean running = false;
	// Classes
	private final JFrame frame;
	private Thread gameThread;
	private final menu m;
	private final Game game;
	private final deadMenu d;
	public Keyboard key;

	public static State state = State.GAME;

	private final BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	@SuppressWarnings("unused")
	private final int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public Start() {

		Mouse mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);

		key = new Keyboard();
		addKeyListener(key);

		/*
		 * To add keys you need to do the key.addKey(KeyEvent.VK_"key"). You
		 * must do it in the right order because it is adding them in to an
		 * array list and then you use key.key.get("key value") witch returns a
		 * boolean if the key is pressed.
		 */

		key.addKey(KeyEvent.VK_UP); // Key Value 0
		key.addKey(KeyEvent.VK_DOWN); // 1
		key.addKey(KeyEvent.VK_LEFT); // 2
		key.addKey(KeyEvent.VK_RIGHT); // 3
		key.addKey(KeyEvent.VK_W); // 4
		key.addKey(KeyEvent.VK_S); // 5
		key.addKey(KeyEvent.VK_A); // 6
		key.addKey(KeyEvent.VK_D); // 7
		key.addKey(KeyEvent.VK_CONTROL); // 8
		key.addKey(KeyEvent.VK_C); // 9
		key.addKey(KeyEvent.VK_SHIFT); // 10
		key.addKey(KeyEvent.VK_L); // 11
		key.addKey(KeyEvent.VK_F3); // 12

		frame = new JFrame();
		m = new menu();
		game = new Game(key);
		d = new deadMenu();

	}

	public synchronized void start() {
		running = true;
		gameThread = new Thread(this, "Display");
		gameThread.start();
		System.out.println("[DEBUG] Start");
	}

	public synchronized void stop() {
		running = false;
		System.out.println("[DEBUG] Stop");
		try {
			gameThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	public static void onExit() {
		String[] a = new String[8];
		a[0] = name;
		a[1] = "level";
		a[2] = Double.toString(Game.player.health);
		a[3] = Integer.toString((int) Game.player.xp);
		a[4] = Integer.toString(Game.player.x);
		a[5] = Integer.toString(Game.player.y);
		a[6] = "mana";
		a[7] = "false";

		SinglePlayerLoading.savePlayer(a);
	}

	@SuppressWarnings("unused")
	@Override
	public void run() {

		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();

			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// System.out.println("UPS: " + updates + ", FPS: " + frames);
				Game.fps = frames;
				updates = 0;
				frames = 0;
			}

		}

		stop();
	}

	public void update() {

		if (state == State.MENU)
			m.update();
		if (state == State.GAME)
			game.update();
		if (state == State.DEAD)
			d.update();

	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		// Graphics here:

		if (state == State.MENU)
			m.render(g);
		if (state == State.GAME)
			game.render(g);
		if (state == State.DEAD)
			d.render(g);

		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		Start game = new Start();
		game.frame.setResizable(false);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setSize(width, height);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		game.frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				onExit();
			}


		});
		game.frame.setTitle(TITLE + "   |   " + VERSION);
		game.start();

	}

}