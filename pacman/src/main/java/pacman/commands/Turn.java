package pacman.commands;

import pacman.base.CommandBase;
import pacman.robot.Robot;

public class Turn extends CommandBase {

    int angle = 0;
    int currentDir;
    int desiredDir;

    public Turn(String dir) {
        if (dir.equals("up")) this.angle = 0;
        else if (dir.equals("left")) this.angle = 270;
        else if (dir.equals("right")) this.angle = 90;
        else if (dir.equals("down")) this.angle = 180;
    }

    @Override
	protected void initialize() {
        System.out.println("Turn.initialize");

        // this.currentDir = Robot.driveTrain.getAngle();

        // if (dir == 0) this.desiredDir = currentDir + 90;
        // else if (dir == 1) this.desiredDir = currentDir - 90;
        // else if (dir == 2) this.desiredDir = currentDir + 180;

        // if (desiredDir == 360) this.desiredDir = 0;
        // else if (desiredDir > 360) this.desiredDir = 90;
        // else if (desiredDir < 0) this.desiredDir = 270;
	}

	@Override
	protected void execute() {
        // if (dir == 0) Robot.driveTrain.tankDrive(1, 0);
        // else if (dir == 1 || dir == 2) Robot.driveTrain.tankDrive(0, 1);
        // this.currentDir = Robot.driveTrain.getAngle();

        Robot.driveTrain.tankDrive(1, 0);
	}
	
	@Override
	protected boolean isFinished() {
		return Robot.driveTrain.getAngle() == angle;
	}
}
