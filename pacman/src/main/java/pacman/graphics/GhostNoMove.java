package pacman.graphics;

public class GhostNoMove extends Ghost{
	
	public GhostNoMove(int x, int y) {
		super(x,y);
	}

	public void move(int pacmanX, int pacmanY) {
		// don't move
	}
}
