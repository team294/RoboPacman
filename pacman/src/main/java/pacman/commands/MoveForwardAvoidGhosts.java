package pacman.commands;

import pacman.base.CommandBase;
import pacman.robot.Robot;
import java.util.Arrays;

public class MoveForwardAvoidGhosts extends CommandBase {
	int moveStep;  // steps you want to move forward
	int numStepsMoved;

	// new constructor
	public MoveForwardAvoidGhosts(int step) {
		super();
		moveStep = step;
	}

	public MoveForwardAvoidGhosts() {

	}

	@Override
	protected void initialize() {
		// count how many steps it moved used to compare the moveStep
		numStepsMoved = 0;
		// System.out.println("MoveForward.initialize");
	}

	@Override
	protected void execute() {
		// System.out.println("   MoveForward.executing");

		int ping = Robot.ghostSensor.getDirectionalRadar();

		int xPos = Robot.driveTrain.getPositionX();
		int yPos = Robot.driveTrain.getPositionY();

		int [][] ghostsArray = Robot.ghostSensor.getGhostLocations();

		// Print out the ghost positions
		System.out.println(Arrays.deepToString(ghostsArray));

		// This is the position of pacman
		System.out.println("   *** ping ***: " + ping);
		System.out.println("Pacman position: (xPos, yPos) (" + xPos + "," + yPos + ")");
		if (ping == 0) {
			// no ghosts ahead drive forward
			Robot.driveTrain.tankDrive(1, 1);
			numStepsMoved += 1;
		} else {
			// ghost ahead drive backward
			Robot.driveTrain.tankDrive(-1, -1);
			numStepsMoved -= 1;
		}
	}
	
	@Override
	protected boolean isFinished() {
		boolean isComplete = false;

		// numStepsMoved += 1;
		if (numStepsMoved >= moveStep){
			isComplete = true;
		}

		return isComplete;

	}

}
