package pacman.test;

import pacman.base.CommandBase;

public class TestCommand extends CommandBase {
	
	protected void initialize() {
		
	}
	
	protected void execute() {
		int ghosts[][] = Robot.ghostSensor.getGhostLocations();
		System.out.println("ghosts: "+ghosts.length);

		int dots[][] = Robot.dotSensor.getDotLocations();
		System.out.println("dots: "+dots.length);

		int angle = Robot.driveTrain.getAngle();
		if (angle == 270) {
			Robot.driveTrain.tankDrive(1,1);
		} else {
			Robot.driveTrain.tankDrive(1,0);
		}
	}
	
	protected boolean isFinished() {
		
		return false;
	}
	
}
