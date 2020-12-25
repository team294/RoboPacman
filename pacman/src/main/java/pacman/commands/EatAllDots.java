package pacman.commands;

import pacman.base.CommandBase;
import pacman.robot.Robot;
import java.util.Arrays;

public class EatAllDots extends CommandBase {

	private static int[][] dotsArray;

	// Debug variable to turn on print statements
	static boolean printDEBUG = false;

	// Number of execute() called
	private int numExec;

	// Maximum number of execute() is a fail-safe to not execute forever
	private static int maxExec = 100;

	// new constructor
	public EatAllDots() {
		super();
	}

	@Override
	protected void initialize() {
		dotsArray = Robot.dotSensor.getDotLocations();
		numExec = 0;
	}

	private void turn(int turnAngle) {
		// Find the fewest tankDrive commands to the specified angle
		int angle;

		angle = Robot.driveTrain.getAngle();
		if (printDEBUG) {
			System.out.println(">>>>>>>> 1. Current Angle: " + angle + "    turnAngle: " + turnAngle);
		}

		if ( (angle - turnAngle) == 90 ) {
			Robot.driveTrain.tankDrive(0, 1);
			if (printDEBUG) {
				System.out.println(">>>>>>>> turn Left");
			}
		} else if (angle == 0 && turnAngle == 270) {
			Robot.driveTrain.tankDrive(0, 1);
			if (printDEBUG) {
				System.out.println(">>>>>>>> turn Left");
			}
		} else {
			Robot.driveTrain.tankDrive(1, 0);
			if (printDEBUG) {
				System.out.println(">>>>>>>> turn Right");
			}
		}
		angle = Robot.driveTrain.getAngle();
		if (printDEBUG) {
			System.out.println(">>>>>>>> 2. Current Angle: " + angle + "    turnAngle: " + turnAngle);
		}
	}

	private void moveForward() {
		Robot.driveTrain.tankDrive(1, 1);
	}

	private int manhattanDistance(int fromX, int fromY, int toX, int toY) {
		// Returns the Manhattan Distance between 2 coordinates
		return Math.abs(fromX - toX) + Math.abs(fromY - toY);
	}

	private int findNearestDotIndex(int robotX, int robotY) {
		// Find the index of the nearest dot from the dotsArray to the robot

		dotsArray = Robot.dotSensor.getDotLocations();
		int minDistance = 999;
		int minDistanceIdx = 0;
		int dotX, dotY;
		int currentDistance;

		// Loop through all the dots to find the nearest dot to the robot
		for (int i = 0; i < dotsArray.length; i++) {
			dotX = dotsArray[i][0];
			dotY = dotsArray[i][1];

			// Find the distance from robot to the dot
			currentDistance = manhattanDistance(robotX, robotY, dotX, dotY);
			if (printDEBUG) {
				System.out.println("in Destination Angle input Robot(x,y): " + robotX + " " + robotY + 
							"  dot(x,y): " + dotX + " " + dotY + " distance: " + currentDistance);
			}
			// Check to see if the currentDistance beats the minimum distance so far
			if (currentDistance < minDistance) {
				minDistance = currentDistance;
				minDistanceIdx = i;
			}
		}
		return minDistanceIdx;
	}

	private int destinationAngle(int robotX, int robotY, int dotX, int dotY) {
		// Given the robot's and the dot's coordinates
		// Find the angle the robot should be facing

		int deltaX = robotX - dotX;
		int deltaY = robotY - dotY;
		int destAngle = 0;

		if (printDEBUG) {
			System.out.println("in Destination Angle input Robot(x,y): " + robotX + " " + robotY + 
								"  dot(x,y): " + dotX + " " + dotY);
		}

		if (deltaX == 0) {
			// in the same column, robot should be faced up or down
			if (printDEBUG) {
				System.out.println("     Robot & Dot are in the same column");
			}
			if (deltaY > 0) {
				destAngle = 0;
			} else {
				destAngle = 180;
			}
		} else if (deltaY == 0) {
			// in the same row, robot should be faced left or right
			if (printDEBUG) {
				System.out.println("     Robot & Dot are in the same row");
			}
			if (deltaX > 0) {
				destAngle = 270;
			} else {
				destAngle = 90;
			}
		} else {
			if (printDEBUG) {
				System.out.println("     Robot & Dot are NOT in the same row or column");
			}
			if (deltaX > 0 && deltaY < 0) {
				// Robot is northeast of dot
				destAngle = 270;
			} else if (deltaX > 0 && deltaY > 0) {
				// Robot is southeast of dot
				destAngle = 0;
			} else if (deltaX < 0 && deltaY > 0) {
				// Robot is southwest of dot
				destAngle = 90;
			} else if (deltaX < 0 && deltaY < 0) {
				// Robot is northwest of dot
				destAngle = 180;
			}
		}
		if (printDEBUG) {
			System.out.println("in Destination Angle before turning: " + destAngle);
		}
		return destAngle;
	}

	private void turnOrMove(int pacmanX, int pacmanY, int dotX, int dotY) {
		// Determine if it's time to move forward or turn based on robot and the dot's position
		int destAngle;
		int angle = Robot.driveTrain.getAngle();

		destAngle = destinationAngle(pacmanX, pacmanY, dotX, dotY);
		if (printDEBUG) {
			System.out.println("turnOrMove: destAngle before turning: " + destAngle);
		}

		if (angle == destAngle) {
			// robot is facing the destination angle, it can move forward
			if (printDEBUG) {
				System.out.println("turnOrMove: moving forward ");
			}
			moveForward();
		} else {
			// robot is NOT facing the destination angle, it needs to turn
			if (printDEBUG) {
				System.out.println("turnOrMove: Need to turn to " + destAngle);
			}
			turn(destAngle);
		}
	}

	@Override
	protected void execute() {

		int nearestDotIdx;

		dotsArray = Robot.dotSensor.getDotLocations();
		if (printDEBUG) {
			System.out.println(">>> In MoveForwardEatAllDots EXECUTE: " + Arrays.deepToString(dotsArray));
		}

		int robotX = Robot.driveTrain.getPositionX();
		int robotY = Robot.driveTrain.getPositionY();

		// Simpler Logic is to go to the last dot's location from the dotsArray
		// int last_element = dotsArray.length - 1;
		// int dotX = dotsArray[last_element][0];
		// int dotY = dotsArray[last_element][1];

		// Nearest dot's location
		nearestDotIdx = findNearestDotIndex(robotX, robotY);
		int dotX = dotsArray[nearestDotIdx][0];
		int dotY = dotsArray[nearestDotIdx][1];

		// Go to the nearest dot
		turnOrMove(robotX, robotY, dotX, dotY);
		numExec += 1;

		if (printDEBUG) {
			System.out.println(">>> In execute(), number of execution is:" + numExec);
		}
	}

	@Override
	protected boolean isFinished() {
		boolean isComplete = false;
		dotsArray = Robot.dotSensor.getDotLocations();

		// When no more dots to eat or you've reached maximum execution, you're done
		if (dotsArray.length == 0 || numExec > maxExec) {
			isComplete = true;
		}

		return isComplete;
	}
}