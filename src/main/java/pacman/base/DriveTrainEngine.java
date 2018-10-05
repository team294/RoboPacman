package pacman.base;

public class DriveTrainEngine {

	public static int MAX_SPEED = 5;
	
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
				Util.log("tankDrive right to angle:"+angle);
			} else {
				Util.log("tankDrive cant turn while moving");
				speed = 0;
			}
		} else if (right >= 0 && right > left) {
			if (speed == 0) {
				angle -= 90;	
				Util.log("tankDrive left to angle:"+angle);
			} else {
				Util.log("tankDrive cant turn while moving");
				speed = 0;
			}
			
		}

		Util.log("tankDrive speed:"+speed);

		// keep angle [-179,180]
		if (angle < -179) angle += 360;
		if (angle > 180) angle -= 360;

		// update position
		if (angle == 0) {
			posY = posY - speed;
		} else if (angle == 180 || angle == -180) {
			posY = posY + speed;
		} else if (angle == 90) {
			posX = posX + speed;
		} else if (angle == -90) {
			posX = posX - speed;
		}

		// update distance
		distance += Math.abs(speed);

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
			posY = 50;
		}
		if (startingPosition == 2) {
			posX = 550;
			posY = 500;
		}
		if (startingPosition == 3) {
			posX = 50;
			posY = 450;
		}
		if (startingPosition == 4) {
			posX = 550;
			posY = 450;
		}	
		if (startingPosition == 5) {
			posX = 550;
			posY = 400;
		}	

	}

}
