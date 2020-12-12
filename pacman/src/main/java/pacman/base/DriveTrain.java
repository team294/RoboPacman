package pacman.base;


public class DriveTrain {
	
	private double distance = 0;
	private int angle = 0;
	private int posX = 0;
	private int posY = 0;
	private int speed = 0;
	private double left = 0;
	private double right = 0;

	public void update(int posX, int posY, int speed, double distance, int angle) {
		this.posX = posX;
		this.posY = posY;
		this.speed = speed;
		this.distance = distance;
		this.angle = angle;
	}

	public double getDistance() {
		return distance; 
	}
	
	public void zeroDistance() {
		distance = 0;
	}
	
	public void zeroAngle() {
		angle = 0;
	}
	
	public int getAngle() {
		return angle;
	}
	
	public int getPositionX() {
		return posX;
	}
	
	public int getPositionY() {
		return posY;
	}

	public int getSpeed() {
		return speed;
	}

	public double getLeft() {
		return left;
	}

	public double getRight() {
		return right;
	}
	
	public void tankDrive(double left, double right) {
		this.left = left;
		this.right = right;
	}


}
