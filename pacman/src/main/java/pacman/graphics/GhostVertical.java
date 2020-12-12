package pacman.graphics;

import java.util.concurrent.ThreadLocalRandom;

import pacman.base.Util;

public class GhostVertical extends Ghost{
	
	public GhostVertical(int x, int y) {
		super(x,y);
	}

	public void move(int pacmanX, int pacmanY) {
		// wait until pacman approaches then run away
		int distance = Util.getDistance(pacmanX, pacmanY, getX(), getY());
		
		if (distance > 0 && distance <= 50) {
			if (direction == 0)	direction = ThreadLocalRandom.current().nextInt(-2, 2);
			setY(getY()+direction);
		} 
	}
}
