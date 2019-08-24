package pacman.graphics;

import java.util.concurrent.ThreadLocalRandom;

public class Ghost {
	protected int x;
	protected int y;
	protected int direction = 0;
	protected int duration = 0;
	
	public Ghost(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

	public void move(int pacmanX, int pacmanY) {
		int next = 0;

		if (duration == 0) {
			duration = ThreadLocalRandom.current().nextInt(3, 10);
			direction = ThreadLocalRandom.current().nextInt(-5, 5);
		} else {
			duration--;
		}

		next = getX()+direction;

		if (next >= 0) setX(next);
	}

}
