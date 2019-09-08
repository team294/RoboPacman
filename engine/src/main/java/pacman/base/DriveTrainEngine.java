package pacman.base;

import pacman.graphics.PacmanGraphics;

public class DriveTrainEngine {

	public static int MAX_SPEED = 5;
	public static int MAX_X = PacmanGraphics.WIDTH - 50;
	public static int MIN_X = 50;
	public static int MAX_Y = PacmanGraphics.HEIGHT - 50;
	public static int MIN_Y = 50; // leave room for score at top
	
	private double distance = 0;
	private int angle = 0;
	private int posX = 0;
	private int posY = 0;
	private int speed = 0;
	private double left = 0;
	private double right = 0;

	public void tankDrive(DriveTrain driveTrain) {
		left = driveTrain.getLeft();
		right = driveTrain.getRight();
		Util.log("tankDrive left:"+left+" right:"+right);

		// calculate speed and angle based on inputs
		if (left == 0 && right == 0) {
			// NEUTRAL: stop
			speed = 0;
			Util.log("tankDrive neutral");
		} else if (left > 0 && right > 0 && left == right) {
			// FORWARD: increase speed
			speed = MAX_SPEED;
			Util.log("tankDrive forward");
		} else if (left < 0 && right < 0 && left == right) {
			// REVERSE: increase speed
			speed = -MAX_SPEED;
			Util.log("tankDrive reverse");
		} else if (left >= 0 && left > right) {
			// RIGHT TURN
			if (speed == 0) {
				angle += 90;
				if (angle == 360) angle = 0;
				Util.log("tankDrive right to angle:"+angle);
			} else {
				Util.log("tankDrive cant turn while moving");
				speed = 0;
			}
		} else if (right >= 0 && right > left) {
			if (speed == 0) {
				angle -= 90;
				if (angle == -90) angle = 270;
				Util.log("tankDrive left to angle:"+angle);
			} else {
				Util.log("tankDrive cant turn while moving");
				speed = 0;
			}
			
		}

		Util.log("tankDrive speed:"+speed);

		// update position
		int oldX = posX;
		int oldY = posY;

		if (angle == 0) {
			posY = posY - speed;
		} else if (angle == 180) {
			posY = posY + speed;
		} else if (angle == 90) {
			posX = posX + speed;
		} else if (angle == 270) {
			posX = posX - speed;
		}

		// make sure position stays within max and min values
		if (posY > MAX_Y || posY < MIN_Y || posX > MAX_X || posX < MIN_X) {
			// revert position back
			posX = oldX; 
			posY = oldY;
		} else {
			// update distance
			distance += Math.abs(speed);
		}

		// update the drive train info with latest data
		update(driveTrain);
		
		Util.log("tankDrive posX:"+posX+" posY:"+posY+" angle:"+angle+" dist:"+distance);
		
	}	

	public void update(DriveTrain driveTrain) {
		driveTrain.update(posX,posY,speed,distance,angle);
	}

	public void setup(int startingPosition) {
		posY =500;

		if (startingPosition == 1) {
			posX = 50;
			posY = 100;
		}
		if (startingPosition == 2) {
			posX = 550;
			posY = 100;
		}
		if (startingPosition == 3) {
			posX = 50;
			posY = 400;
		}
		if (startingPosition == 4) {
			posX = 550;
			posY = 400;
		}	

	}

}
