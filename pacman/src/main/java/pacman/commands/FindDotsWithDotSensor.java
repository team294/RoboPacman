package pacman.commands;

import pacman.base.CommandBase;
import pacman.robot.Robot;

public class FindDotsWithDotSensor extends CommandBase {
	
	@Override
	protected void execute() {
		// get all the dots from the sensor
		int[][] dotLocations = Robot.dotSensor.getDotLocations();
		
		// find a dot (should get the closest but this will do for now)
		System.out.println("dot "+dotLocations[0][0]+" "+dotLocations[0][1]);
		int targetX = dotLocations[0][0];
		int targetY = dotLocations[0][1];

		// if facing up/down then drive forward until Y coord is correct
		// if facing left/right then drive forward until X coord is correct
		// if it is already correct then turn to go the other direction
		
		// up
		if (Robot.driveTrain.getAngle() == 0) {
			if (targetY < Robot.driveTrain.getPositionY()) {
				Robot.driveTrain.tankDrive(1,1);
			} else {
				Robot.driveTrain.tankDrive(1,-1);
			}
		}
		
		// down
		if (Robot.driveTrain.getAngle() == 180) {
			if (targetY > Robot.driveTrain.getPositionY()) {
				Robot.driveTrain.tankDrive(1,1);
			} else {
				Robot.driveTrain.tankDrive(1,-1);
			}
		}

		// left
		if (Robot.driveTrain.getAngle() == 270) {
			if (targetX < Robot.driveTrain.getPositionX()) {
				Robot.driveTrain.tankDrive(1,1);
			} else {
				Robot.driveTrain.tankDrive(1,-1);
			}
		}
		
		// right
		if (Robot.driveTrain.getAngle() == 90) {
			if (targetX > Robot.driveTrain.getPositionX()) {
				Robot.driveTrain.tankDrive(1,1);
			} else {
				Robot.driveTrain.tankDrive(1,-1);
			}
		}
	}
	
	@Override
	protected boolean isFinished() {
		return Robot.dotSensor.getDotLocations().length == 0 ? true : false;
	}

}
