package pacman.commands;

import pacman.base.CommandBase;
import pacman.robot.Robot;

public class FindDotsWithDotSensor extends CommandBase {
	
	@Override
	protected void execute() {
		int targetX = 0;
		int targetY = 0;
		int[][] dotLocations = Robot.dotSensor.getDotLocations();
		
		// find a dot (should get the closest but this will do for now)
		System.out.println("dot "+dotLocations[0][0]+" "+dotLocations[0][1]);
		targetX = dotLocations[0][0];
		targetY = dotLocations[0][1];
		
		// up
		if (Robot.driveTrain.getAngle() == 0) {
			if (targetY < Robot.driveTrain.getPositionY()) {
				Robot.driveTrain.tankDrive(1,1);
			}
			if (targetY >= Robot.driveTrain.getPositionY()) {
				Robot.driveTrain.tankDrive(1,0);
			}
		}
		
		// down
		if (Robot.driveTrain.getAngle() == 180) {
			if (targetY > Robot.driveTrain.getPositionY()) {
				Robot.driveTrain.tankDrive(1,1);
			}
			if (targetY <= Robot.driveTrain.getPositionY()) {
				Robot.driveTrain.tankDrive(1,0);
			}
		}

		// left
		if (Robot.driveTrain.getAngle() == 270) {
			if (targetX < Robot.driveTrain.getPositionX()) {
				Robot.driveTrain.tankDrive(1,1);
			}
			if (targetX >= Robot.driveTrain.getPositionX()) {
				Robot.driveTrain.tankDrive(1,0);
			}
		}
		
		// right
		if (Robot.driveTrain.getAngle() == 90) {
			if (targetX > Robot.driveTrain.getPositionX()) {
				Robot.driveTrain.tankDrive(1,1);
			}
			if (targetX <= Robot.driveTrain.getPositionX()) {
				Robot.driveTrain.tankDrive(1,0);
			}
		}
		
	}
	
	@Override
	protected boolean isFinished() {
		return Robot.dotSensor.getDotLocations().length == 0 ? true : false;
	}

}
