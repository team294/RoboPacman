package pacman.commands;

import java.util.Arrays;

import pacman.base.CommandBase;
import pacman.robot.Robot;

public class FindDots extends CommandBase {

	private int[][] dotLocations;
	private int[][] ghostLocations;
	private int[][] prevGhostPos;
	private int[] robotCoords = new int[2];
	private int[] nextTarget;
	private int[] closestGhost;
	private int[] farPoint = {100, 100}; // creates a point the findClosestToRobot function can use as an extreme starting value
	private int ghostSensitivity = 1;
	
	private double distanceForm(int[] a, int[] b) { // finds distance between to coordinates in array form (x, y)
		return Math.sqrt(Math.pow(b[0]-a[0], 2) + Math.pow(b[1]-a[1], 2));
	}

	private int[] findClosestToRobot(int[][] dotArr) {
		int[] coords = farPoint;
		for (int[] point: dotArr) {
			coords = (distanceForm(robotCoords, point) < distanceForm(robotCoords, coords)) ? point : coords;
		}
		return (coords == farPoint) ? new int[] {} : coords;
	}

	private int[] findClosestToRobot(int[][] dotArr, int minDistance) {
		int[] coords = farPoint;
		for (int[] point: dotArr) {
			coords = (distanceForm(robotCoords, point) <= minDistance) ? (distanceForm(robotCoords, point) < distanceForm(robotCoords, coords)) ? point : coords : coords;
		}
		return (coords == farPoint) ? new int[] {} : coords;
	}
	
	private void goStraightOrTurn(int desiredAngle) {
		if (Robot.driveTrain.getAngle() == desiredAngle) {
			Robot.driveTrain.tankDrive(1, 1);
		} else {
			double leftVal = (Robot.driveTrain.getAngle()-180 < 0) ? 0 : 1;
			double rightVal = (Robot.driveTrain.getAngle()-180 >= 0) ? 0 : 1;
			Robot.driveTrain.tankDrive((desiredAngle == 270 || desiredAngle == 0) ? leftVal : rightVal, (desiredAngle == 270 || desiredAngle == 0) ? rightVal : leftVal);
		}
	}

	private int findDesiredAngle(int[] currentPos, int[] desiredPos, boolean prioritizeVertical, boolean flip) {
		if (prioritizeVertical) {
			if (desiredPos[1] < currentPos[1]) {
				return flip ? 180 : 0;
			} else if (desiredPos[1] > currentPos[1]) {
				return flip ? 0 : 180;
			} else {
				if (desiredPos[0] < currentPos[0]) {
					return flip ? 90 : 270;
				} else if (desiredPos[0] > currentPos[0]) {
					return flip ? 270 : 90;
				} else {
					return Robot.driveTrain.getAngle();
				}
			}
		} else {
			if (desiredPos[0] < currentPos[0]) {
				return flip ? 90 : 270;
			} else if (desiredPos[0] > currentPos[0]) { 
				return flip ? 270 : 90;
			} else {
				if (desiredPos[1] < currentPos[1]) {
					return flip ? 180 : 0;
				} else if (desiredPos[1] > currentPos[1]) {
					return flip ? 0 : 180;
				} else {
					return Robot.driveTrain.getAngle();
				}
			}
		}	
	}

	private void goTo(int[] desiredPosition) {
		goStraightOrTurn(findDesiredAngle(robotCoords, desiredPosition, false, false));
		// if (desiredPosition[0] < robotCoords[0]) {
		// 	goStraightOrTurn(270);
		// } else if (desiredPosition[0] > robotCoords[0]) { 
		// 	goStraightOrTurn(90);
		// } else if (desiredPosition[0] == robotCoords[0]) {
		// 	if (desiredPosition[1] < robotCoords[1]) {
		// 		goStraightOrTurn(0);
		// 	} else if (desiredPosition[1] > robotCoords[1]) {
		// 		goStraightOrTurn(180);
		// 	} 
		// }
	}

	private double[][] findGhostVelocity(int[][] currentPos, int[][] previousPos) {
		double[][] velocities = new double[currentPos.length][2];
		for (int i = 0; i < currentPos.length; i++) {
			velocities[i][0] = distanceForm(currentPos[i], previousPos[i]);
		}
		return velocities;
	}

	private void goAwayFrom(int[] point) {
		if (point[1] < robotCoords[1]) {
			goStraightOrTurn(180);
		} else if (point[1] > robotCoords[1]) {
			goStraightOrTurn(0);
		} else if (point[1] == robotCoords[1]) {
			if (point[0] < robotCoords[0]) {
				goStraightOrTurn(90);
			} else if (point[0] > robotCoords[0]) { 
				goStraightOrTurn(270);
			}
		}
	}

	// private int[][] ghostsNearby(int[][] ghostPos, double minDistance) {
	// 	return Arrays.stream(ghostPos) // converts the int[][] arr to Stream type
	// 		.filter(x -> distanceForm(robotCoords, x) < minDistance) // keeps elements that are less than the minDistance
	// 		.toArray(int[][]::new); // converts type Stream to type int[][]
	// }

	@Override
	protected void initialize() {
		System.out.println("FindDots.initialize");
		this.dotLocations = Robot.dotSensor.getDotLocations();
		this.ghostLocations = Robot.ghostSensor.getGhostLocations();
		this.prevGhostPos = this.ghostLocations;
		this.robotCoords[0] = Robot.driveTrain.getPositionX(); 
		this.robotCoords[1] = Robot.driveTrain.getPositionY();
	}

	@Override
	protected void execute() {
		dotLocations = Robot.dotSensor.getDotLocations();
		ghostLocations = Robot.ghostSensor.getGhostLocations();
		robotCoords[0] = Robot.driveTrain.getPositionX(); 
		robotCoords[1] = Robot.driveTrain.getPositionY();
		nextTarget = findClosestToRobot(dotLocations);
		findGhostVelocity(ghostLocations, prevGhostPos);
		closestGhost = findClosestToRobot(ghostLocations, ghostSensitivity);
		if (closestGhost.length == 0) {
			goTo(nextTarget);
			System.out.println("Going to");
		} else {
			goAwayFrom(closestGhost);
			System.out.println("Going away from");
		}
		prevGhostPos = ghostLocations;
		System.out.println("Robot Coords: " + Arrays.toString(robotCoords));
		System.out.println("Closest Ball: " + Arrays.toString(nextTarget));
		System.out.println("Ghosts: " + Arrays.deepToString(Robot.ghostSensor.getGhostLocations()));
		System.out.println("Closest Ghost: " + Arrays.toString(findClosestToRobot(Robot.ghostSensor.getGhostLocations(), ghostSensitivity)));
	}
	
	@Override
	protected boolean isFinished() {
		return (dotLocations.length == 0) ? true : false;
	}
}
