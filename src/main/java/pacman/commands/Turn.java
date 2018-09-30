package pacman.commands;

import pacman.base.CommandBase;
import pacman.robot.Robot;

public class Turn extends CommandBase {
	
	private boolean success = false;
	private int targetAngle;
	
	public Turn(int angle) {
		// special case for -180 as it is same as 180
		if (angle == -180) {
			targetAngle = 180;
		} else {
			targetAngle = angle;
		}
	}
	
	@Override
	protected void execute() {
		int currentAngle = Robot.driveTrain.getAngle();
		
		// if we have turned far enough then stop
		if (currentAngle == targetAngle) {
			Robot.driveTrain.tankDrive(0, 0);
			success = true;
		} else {
			// compare the current to the target angle to see which way to turn
			if (currentAngle < targetAngle) {
				Robot.driveTrain.tankDrive(1, -1);	
			} else {
				Robot.driveTrain.tankDrive(-1, 1);
			}
		}
	}

	@Override
	protected boolean isFinished() {
		return success;	
	}

}
