package pacman.commands;

import pacman.base.CommandBase;
import pacman.robot.Robot;

public class FindDots extends CommandBase {

	// private int[] turnDirection = new int[2]; // turn left or turn right to get there quickest 
	
	// public FindDots(int degrees) {
	// }

	@Override
	protected void initialize() {
		System.out.println("FindDots.initialize");
	}

	@Override
	protected void execute() {
        System.out.println("GhostSensor: " + Robot.ghostSensor.getGhostLocations()[0][0]);
		// Robot.driveTrain.tankDrive(1, 0);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
}
