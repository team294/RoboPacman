package pacman.commands;

import pacman.base.CommandBase;
import pacman.robot.Robot;

public class SpinOnce extends CommandBase {
	boolean first_turn = true;

	@Override
	protected void initialize() {
		// System.out.println("SpinOnce.initialize");
	}

	@Override
	protected void execute() {

		int angle;

		Robot.driveTrain.tankDrive(1, 0);
		angle = Robot.driveTrain.getAngle();
		System.out.println("angle: " + angle);

		first_turn = false;

	}
	
	@Override
	protected boolean isFinished() {

		if (Robot.driveTrain.getAngle() == 0 && !first_turn)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
