package pacman.commands;

import pacman.base.CommandBase;
import pacman.robot.Robot;

public class DriveForward extends CommandBase {
	private double position; 
	private double user_position;
	public DriveForward(int DriveDistance){
		user_position = DriveDistance;

	}
	@Override
	protected void initialize() {
		position = Robot.driveTrain.getDistance(); 

	}
	
	@Override
	protected void execute() {
		Robot.driveTrain.tankDrive(1, 1);
		



	}
	
	@Override
	protected boolean isFinished() {
		if (Robot.driveTrain.getDistance() == user_position){
			return true;
		}
		return false;




	}

}
