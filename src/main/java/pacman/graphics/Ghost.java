package pacman.graphics;

import java.util.concurrent.ThreadLocalRandom;

public class Ghost {
	private int x;
	private int y;
	private int direction = 0;
	private int duration = 0;
	
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

	public int getMove() {
		if (duration == 0) {
			duration = ThreadLocalRandom.current().nextInt(3, 10);
			direction = ThreadLocalRandom.current().nextInt(-5, 5);
		} else {
			duration--;
		}

		return direction;
	}

}
