package pacman.commands;

import pacman.base.CommandBase;
import pacman.robot.Robot;

public class DriveForward extends CommandBase {
    
    @Override
	protected void initialize() {
		System.out.println("DriveForward.initialize");
	}

	@Override
	protected void execute() {
        Robot.driveTrain.tankDrive(1, 1);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
