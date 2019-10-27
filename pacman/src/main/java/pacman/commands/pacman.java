package pacman.commands;

import pacman.base.CommandBase;
import pacman.robot.Robot;

public class pacman extends CommandBase {

	@Override
	protected void initialize() {
		System.out.println("SpinForever.initialize");
	}

	@Override
	protected void execute() {
		Robot.driveTrain.tankDrive(1, 0);
	}
	@Override
	protected void move(m) {
		Robot.driveTrain.tankDrive(m, m);
	}
	@Override
	protected boolean isFinished() {
		return true;
	}

}
