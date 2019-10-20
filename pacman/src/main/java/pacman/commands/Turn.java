package pacman.commands;

import pacman.base.CommandBase;
import pacman.robot.Robot;

public class Turn extends CommandBase {
	private double angle; 
	private double turnAngle; 
	public Turn(int turn_angle){
		turnAngle = turn_angle;
	} 
	@Override
	
	protected void initialize() {
		angle = Robot.driveTrain.getAngle(); 

	}
	
	@Override
	protected void execute() {
		Robot.driveTrain.tankDrive(1, 0);
		



	}
	
	@Override
	protected boolean isFinished() {
		if (Robot.driveTrain.getAngle() == turnAngle){
			return true;
		}
		return false;




	}

}
