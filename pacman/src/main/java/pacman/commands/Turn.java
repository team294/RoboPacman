package pacman.commands;

import pacman.base.CommandBase;
import pacman.robot.Robot;

public class Turn extends CommandBase {

    double angle = 0;
    int currentDir;
    int desiredDir;

    public Turn(double angle) {
        this.angle = angle;
    }

    @Override
	protected void initialize() {
        System.out.println("Turn.initialize");
	}

	@Override
	protected void execute() {
        Robot.driveTrain.tankDrive(1, 0);
	}
	
	@Override
	protected boolean isFinished() {
		return Robot.driveTrain.getAngle() == angle;
	}
}
