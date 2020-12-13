package pacman.commands;

import pacman.base.CommandBase;
import pacman.robot.Robot;

public class TurnToAngle extends CommandBase {
	
	private int targetAngle;
	
	public TurnToAngle(int angle) {
		if (angle < 0) {
			targetAngle = angle + 360;
		} else {
			targetAngle = angle;
		}
		System.out.println("target angle is "+targetAngle);
	}
	
	@Override
	protected void execute() {
		int currentAngle = Robot.driveTrain.getAngle();
		
		// if we have turned far enough then stop
		if (currentAngle == targetAngle) {
			Robot.driveTrain.tankDrive(0, 0);
		} else {
			// calculate angle to my right
			int myRight = currentAngle + 90;
			
			// translate 360 to zero
			if (myRight == 360) myRight = 0;

			// if my right is the target angle then turn right otherwise left
			if (targetAngle == myRight) {
				Robot.driveTrain.tankDrive(1, -1);	
			} else {
				Robot.driveTrain.tankDrive(-1, 1);
			}
		}
	}

	@Override
	protected boolean isFinished() {
		return (Robot.driveTrain.getAngle() == targetAngle) ? true: false;	
	}

}
