package pacman.commands;

import pacman.base.CommandBase;
import pacman.robot.Robot;

public class DriveStraight10 extends CommandBase {
	private static int start;
	private static int target;
	//private int distance;
	public DriveStraight10(int distance){
		//this.distance=distance;
		target=start+distance;
	}
	
	@Override
	protected void initialize() {
		System.out.println("DriveStraight10.initialize");
		start=Robot.driveTrain.getPositionY();
		
	}

	@Override
	protected void execute() {
		Robot.driveTrain.tankDrive(1, 1);
	}
	
	@Override
	protected boolean isFinished() {
		if(target == Robot.driveTrain.getPositionY()){
			return true;
		} else return false;
	}

}
