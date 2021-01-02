package pacman.commands;

import pacman.base.CommandBase;
import pacman.robot.Robot;
import java.util.Arrays;

public class EatAllDotsAvoidGhosts extends CommandBase {

	private static int[][] dotsArray;
	private static int[][] ghostArray;

	// Keep track of last turn
	private int lastTurnAngle;

	// Debug variable to turn on print statements
	static boolean printDEBUG = false;

	// Number of execute() called
	private int numExec;

	// Maximum number of execute() is a fail-safe to not execute forever
	private static int maxExec = 100;

	// new constructor
	public EatAllDotsAvoidGhosts() {
		super();
	}

	@Override
	protected void initialize() {
		dotsArray = Robot.dotSensor.getDotLocations();
		ghostArray = Robot.ghostSensor.getGhostLocations();
		numExec = 0;
		lastTurnAngle = 0;
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

	private boolean willHitGhost(int robotX, int robotY, int turnAngle) {
		// Returns true if you make that turn, go forward 1 step will result in hiting a ghost
		int ghostX, ghostY;
		int currRobotX;
		int currRobotY;

		// Check if you will hit any of the ghosts
		for (int i = 0; i < ghostArray.length; i++) {
			ghostX = ghostArray[i][0];
			ghostY = ghostArray[i][1];

			currRobotX = robotX;
			currRobotY = robotY;

			if (turnAngle == 0) {
				// go up one step
				currRobotY -= 1;
			} else if (turnAngle == 90) {
				// go right one step
				currRobotX += 1;
			} else if (turnAngle == 180) {
				// go down one step
				currRobotY += 1;
			} else {
				// go left one step
				currRobotX -= 1;
			}

			if (printDEBUG) {
				System.out.println("in willHitGhost - new robot(x,y) " + currRobotX + " " + currRobotY + "   Ghost (x,y): " + ghostX + " " + ghostY );
			}
			if (currRobotX == ghostX && currRobotY == ghostY) {
				return true;
			}
		}
		return false;
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
			System.out.println(">>> In EXECUTE: ghostArray" + Arrays.deepToString(ghostArray));
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
				// ideally turn left
				destAngle = 270;
				System.out.println("     Robot is in the same row of dot. Last turn angel: " + lastTurnAngle);
				System.out.println("           if turn 270 will hit ghost? " + willHitGhost(robotX, robotY, destAngle));

				if (willHitGhost(robotX, robotY, destAngle)) {
					if (robotY == 8) {
						// at the bottom of field, point up
						destAngle = 0;
					} else if (robotY == 1) {
						// point down
						destAngle = 180;
					} else {
						destAngle = lastTurnAngle;
					}
				}

			} else {
				destAngle = 90;
			}
		} else {
			if (printDEBUG) {
				System.out.println("     Robot & Dot are NOT in the same row or column");
			}
			if ((deltaX > 0 && deltaY < 0) || (deltaX > 0 && deltaY > 0)) {
				// Robot is northeast or southeast of dot
				destAngle = 270;

				if (willHitGhost(robotX, robotY, destAngle)) {
					if (robotY == 8) {
						// at the bottom of field, point up
						destAngle = 0;
					} else if (robotY == 1) {
						// point down
						destAngle = 180;
					} else {
						if (lastTurnAngle == 270) {
							destAngle = 0;
						} else {
							destAngle = lastTurnAngle;
						}
					}
				}
			} else if (deltaX < 0 && deltaY > 0) {
				// Robot is southwest of dot
				// ideally turn right
				destAngle = 90;

				if (willHitGhost(robotX, robotY, destAngle)) {
					// turn up
					destAngle = 0;
				}
			} else if (deltaX < 0 && deltaY < 0) {
				// Robot is northwest of dot
				// ideally turn down
				destAngle = 180;

				if (willHitGhost(robotX, robotY, destAngle)) {
					// turn right
					destAngle = 90;
				}
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
			System.out.println("turnOrMove: Will turning " + destAngle + " hit the ghost? " + willHitGhost(pacmanX, pacmanY, destAngle));
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

			// keep track of last turn so we don't get stuck in a loop
			lastTurnAngle = destAngle;
		}
	}

	@Override
	protected void execute() {

		dotsArray = Robot.dotSensor.getDotLocations();

		if (printDEBUG) {
			System.out.println(">>> In EXECUTE: dotsArray" + Arrays.deepToString(dotsArray));
			System.out.println(">>> In EXECUTE: ghostArray" + Arrays.deepToString(ghostArray));
		}

		int robotX = Robot.driveTrain.getPositionX();
		int robotY = Robot.driveTrain.getPositionY();

		// Simpler Logic is to go to the last dot's location from the dotsArray
		int last_element = dotsArray.length - 1;
		int dotX = dotsArray[last_element][0];
		int dotY = dotsArray[last_element][1];

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