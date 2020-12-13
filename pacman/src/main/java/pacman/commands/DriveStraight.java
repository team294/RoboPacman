package pacman.commands;

import pacman.base.CommandBase;
import pacman.robot.Robot;

public class DriveStraight extends CommandBase {
	
	private double targetDistance;
	private double startDistance;
	private double currentDistance;
	
	public DriveStraight(double distance) {
		// save the target distance for later
		targetDistance = distance;
	}

	@Override
	protected void initialize() {
		// save the starting point for later
		startDistance = Robot.driveTrain.getDistance();
		currentDistance = 0;
	}

	@Override
	protected void execute() {
		// calc distance traveled since this command started
		currentDistance = Robot.driveTrain.getDistance() - startDistance;
		
		// if we have gone far enough then stop
		if (currentDistance >= targetDistance) {
			Robot.driveTrain.tankDrive(0, 0);
		} else {
			Robot.driveTrain.tankDrive(1, 1);
		}
	}

	@Override
	protected boolean isFinished() {
		return (currentDistance >= targetDistance) ? true : false;
	}

}
