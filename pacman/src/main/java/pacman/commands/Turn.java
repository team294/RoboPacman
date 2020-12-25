package pacman.commands;

import pacman.base.CommandBase;
import pacman.robot.Robot;

public class Turn extends CommandBase {
	int turnAngle;

	// new constructor
	public Turn(int angle) {
		super();
		turnAngle = angle;

		System.out.println("turnAngle:" + turnAngle);
	}

	public Turn() {

	}

	@Override
	protected void initialize() {
		// System.out.println("Turn.initialize");
	}

	@Override
	protected void execute() {
		int angle;

		angle = Robot.driveTrain.getAngle();
		System.out.println(">>>>>>>> Current Angle: " + angle + "    turnAngle: " + turnAngle);

		// Find the fewest turns to the specified angle
		if ( (angle - turnAngle) == 90 ) {
			Robot.driveTrain.tankDrive(0, 1);
			// System.out.println(">>>>>>>> turn Left");
		} else if (angle == 0 && turnAngle == 270) {
			Robot.driveTrain.tankDrive(0, 1);
			// System.out.println(">>>>>>>> turn Left");
		} else {
			Robot.driveTrain.tankDrive(1, 0);
			// System.out.println(">>>>>>>> turn Right");
		}

	}
	
	@Override
	protected boolean isFinished() {
		boolean isComplete = false;

		if (Robot.driveTrain.getAngle() == turnAngle) {
			isComplete = true;
		}

		return isComplete;
	}

}
