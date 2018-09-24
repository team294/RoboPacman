package pacman.commands;

import pacman.base.CommandBase;
import pacman.robot.Robot;

public class SpinForever extends CommandBase {

	@Override
	protected void initialize() {
		System.out.println("init");
	}

	@Override
	protected void execute() {
		System.out.println("execute");
		Robot.driveTrain.tankDrive(1, 0);
	}
	
	@Override
	protected boolean isFinished() {
		System.out.println("isFinished");
		return false;
	}

}
