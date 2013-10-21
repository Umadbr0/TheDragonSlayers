package Main;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Game extends Canvas implements Runnable {

	/*
	 * This class is made by v7_FlasH. Last updated 2013-06-14.
	 */

	//Changed it!
	
	public static int width = 1280;
	public static int height = 720;
	public final static String TITLE = "The Dragon Slayers ALPHA";
	public final static String VERSION = "0.1";

	//Booleans
	private boolean running = false;
	//Classes
	private JFrame frame;
	private Thread gameThread;


	


	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	@SuppressWarnings("unused")
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	
	
	public Game() {
		frame = new JFrame();

		

		
	


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
				System.out.println("UPS: " + updates + ", FPS: " + frames);
				updates = 0;
				frames = 0;
			}

		}

		stop();
	}
	
	

	public void update() {
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		//Graphics here:

		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setSize(width, height);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		game.frame.setTitle(TITLE + "   |   " + VERSION);
		game.start();

	}

}