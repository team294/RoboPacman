package pacman.commands;

import pacman.base.CommandBase;
import pacman.robot.Robot;

public class Turn extends CommandBase {
	private int start;
	private int target;
	//private int distance;
	public Turn(int angle){
		//this.distance=distance;
		this.target=start+angle;
	}
	
	@Override
	protected void initialize() {
		System.out.println("Turn.initialize");
		start=Robot.driveTrain.getAngle();
		
	}

	@Override
	protected void execute() {
		Robot.driveTrain.tankDrive(-1, 1);
	}
	
	@Override
	protected boolean isFinished() {
		if( target== Robot.driveTrain.getAngle()){
			return true;
		}
		return false;
	}

}
