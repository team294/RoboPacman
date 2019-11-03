package pacman.commands;

import pacman.base.CommandBase;
import pacman.robot.Robot;

public class DriveOnce extends InstantCommand {

	@Override
	protected void initialize() {
        System.out.println("DriveOnce.initialize");
	}

	@Override
	protected void execute() {
        System.out.println("DriveOnce.execute");
		Robot.driveTrain.tankDrive(1, 1);
    }

}
