package pacman.commands;

import pacman.base.CommandBase;
import pacman.robot.Robot;

public class Turn extends CommandBase {

private int startingAngle;

private int targetAngle;

public Turn(int turnAngle){
	targetAngle = turnAngle;
}

	@Override
	protected void initialize() {
		startingAngle = Robot.driveTrain.getAngle();
		System.out.println("Turn.initialize");
	}

	@Override
	protected void execute() {
		Robot.driveTrain.tankDrive(-1, 1);
	}
	
	@Override
	protected boolean isFinished() {
		if (Robot.driveTrain.getAngle() == targetAngle) return true;
		return false;
	}

}
 