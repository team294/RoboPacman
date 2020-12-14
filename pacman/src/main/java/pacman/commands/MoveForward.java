package pacman.commands;

import pacman.base.CommandBase;
import pacman.robot.Robot;

public class MoveForward extends CommandBase {
	int moveStep;  // steps you want to move forward
	int numStepsMoved = 0;

	// new constructor
	public MoveForward(int step) {
		super();
		moveStep = step;
	}

	public MoveForward() {

	}

	@Override
	protected void initialize() {
		// System.out.println("MoveForward.initialize");
	}

	@Override
	protected void execute() {
		System.out.println("   MoveForward.executing");
		Robot.driveTrain.tankDrive(1, 1);
	}
	
	@Override
	protected boolean isFinished() {
		boolean isComplete = false;

		numStepsMoved += 1;
		if (numStepsMoved >= moveStep){
			isComplete = true;
		}

		return isComplete;

	}

}
