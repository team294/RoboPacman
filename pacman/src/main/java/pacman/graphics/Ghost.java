package pacman.graphics;

import java.util.concurrent.ThreadLocalRandom;

public class Ghost {
	protected int x;
	protected int y;
	protected int direction = 0;
	protected int moveY = 0;
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
		if (x >= 1 && x < PacmanGraphics.COLS) {
			this.x = x;
		}
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		if (y >= 1 && y < PacmanGraphics.ROWS) {
			this.y = y;
		}
	}

	public void move(int pacmanX, int pacmanY) {
		if (duration == 0) {
			duration = ThreadLocalRandom.current().nextInt(1, 6);
			direction = ThreadLocalRandom.current().nextInt(-1, 2);
			moveY = ThreadLocalRandom.current().nextInt(0, 2);
			//System.out.printf("ghost duration:%d direction x:%d y:%d %n",duration,direction, directionY);
		} else {
			duration--;
		}

		// check to see if we should move on Y axis or X axis
		if (moveY > 0) {
			setY(getY()+direction);
		} else {
			setX(getX()+direction);
		}
		
	}

}
