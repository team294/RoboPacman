package pacman.commands;

import pacman.base.CommandBase;
import pacman.robot.Robot;

public class TurnToAngle extends CommandBase {
	double angleTarget;

	/**
	 * Turn pacman to target angle
	 * @param angle = 0, 90, 180, or 270
	 */
	public TurnToAngle(double angle) {
		angleTarget = angle;
	}

	@Override
	protected void initialize() {
		System.out.println("TurnToAngle.initialize");
	}

	@Override
	protected void execute() {
		Robot.driveTrain.tankDrive(1, 0);
	}
	
	@Override
	protected boolean isFinished() {
		return (Robot.driveTrain.getAngle()==angleTarget);
	}

}
