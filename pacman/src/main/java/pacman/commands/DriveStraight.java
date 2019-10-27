package pacman.commands;

import pacman.base.CommandBase;
import pacman.robot.Robot;

public class DriveStraight extends CommandBase {
private double travelDistance ;
	public DriveStraight(double distance){
		travelDistance = distance;
	}

	@Override
	protected void initialize() {
		
		System.out.println("DriveStraight.initialize");
	}

	@Override
	protected void execute() {
		Robot.driveTrain.tankDrive(1, 1);
	}
	
	@Override
	protected boolean isFinished() {
		if (Robot.driveTrain.getDistance() >= travelDistance) return true;
		return false;
	}

}
 