package pacman.commands;

import pacman.base.CommandBase;
import pacman.robot.Robot;

public class FieldTest extends CommandBase {

	@Override
	protected void initialize() {
		System.out.println("SpinForever.initialize");
		Robot.driveTrain.tankDrive(0, 1);
	}

	@Override
	protected void execute() {
		if (Robot.driveTrain.getAngle() == 270) {
			Robot.driveTrain.tankDrive(1, 1);
		} else {
			Robot.driveTrain.tankDrive(0, 1);
		}
		System.out.println("(" + Robot.driveTrain.getPositionX() + ", " + Robot.driveTrain.getPositionY() + ")");
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
