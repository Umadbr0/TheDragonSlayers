package Main.Mob;

import java.awt.Graphics;

import Flash.Images.FImage;
import Flash.Input.Keyboard;
import FrameWork.Screen;

public class Player extends Mob {

	public Player(int x, int y, Keyboard key) {
		super(x, y, key);
		sprite = FImage.loadImage("/textures/player/playerD1.png");
	}

	int xa, ya;

	public void update() {

		if (key.key.get(4))
			ya = -speed;
		if (key.key.get(5))
			ya = speed;
		if (key.key.get(6))
			xa = -speed;
		if (key.key.get(7))
			xa = speed;

		if (xa != 0 || ya != 0) {
			move(xa, ya);
		}

		ya = 0;
		xa = 0;

	}

	public void render(Screen s, Graphics g) {
		s.renderPlayer(g, x, y, sprite);
	}

}
