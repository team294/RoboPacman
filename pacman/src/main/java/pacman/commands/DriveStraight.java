package pacman.commands;

import pacman.base.CommandBase;
import pacman.robot.Robot;

public class DriveStraight extends CommandBase {
private double initial;
private double target=6; 
	@Override
	protected void initialize() {
	initial= Robot.driveTrain.getDistance();	

	}

	@Override
	protected void execute() {
		Robot.driveTrain.tankDrive(1, 1);
	}
	
	@Override
	protected boolean isFinished() {
		if (Robot.driveTrain.getDistance() >= initial+target) {
			return true;
		}
		return false;
	}

}
