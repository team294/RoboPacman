package pacman.commands;

import java.util.Arrays;

import pacman.base.CommandBase;
import pacman.robot.Robot;
import pacman.subsystems.Coord;

public class FindDots extends CommandBase {

	private Coord[] dotLocations;
	private Coord[] ghostLocations;
	private Coord[] prevGhostPos;
	private Coord robotCoords = new Coord();
	private Coord nextTarget;
	private Coord closestGhost;
	private int ghostSensitivity = 1;
	
	// private double distanceForm(int[] a, int[] b) { // finds distance between to coordinates in array form (x, y)
	// 	return Math.sqrt(Math.pow(b[0]-a[0], 2) + Math.pow(b[1]-a[1], 2));
	// }

	// private int[] findClosestToRobot(int[][] dotArr) {
	// 	int[] coords = farPoint;
	// 	for (int[] point: dotArr) {
	// 		coords = (distanceForm(robotCoords, point) < distanceForm(robotCoords, coords)) ? point : coords;
	// 	}
	// 	return (coords == farPoint) ? new int[] {} : coords;
	// }

	// private int[] findClosestToRobot(int[][] dotArr, int minDistance) {
	// 	int[] coords = farPoint;
	// 	for (int[] point: dotArr) {
	// 		coords = (distanceForm(robotCoords, point) <= minDistance) ? (distanceForm(robotCoords, point) < distanceForm(robotCoords, coords)) ? point : coords : coords;
	// 	}
	// 	return (coords == farPoint) ? new int[] {} : coords;
	// }
	
	private void goStraightOrTurn(int desiredAngle) {
		if (Robot.driveTrain.getAngle() == desiredAngle) {
			Robot.driveTrain.tankDrive(1, 1);
		} else {
			double leftVal = (Robot.driveTrain.getAngle()-180 < 0) ? 0 : 1;
			double rightVal = (Robot.driveTrain.getAngle()-180 >= 0) ? 0 : 1;
			Robot.driveTrain.tankDrive((desiredAngle == 270 || desiredAngle == 0) ? leftVal : rightVal, (desiredAngle == 270 || desiredAngle == 0) ? rightVal : leftVal);
		}
	}

	private int findDesiredAngle(Coord currentPos, Coord desiredPos) {
		if (desiredPos.x < currentPos.x) {
			return 270;
		} else if (desiredPos.x > currentPos.x) { 
			return 90;
		} else {
			if (desiredPos.y < currentPos.y) {
				return 0;
			} else if (desiredPos.y > currentPos.y) {
				return 180;
			} else {
				return Robot.driveTrain.getAngle();
			}
		}
	}

	private void goTo(Coord desiredPosition) {
		goStraightOrTurn(findDesiredAngle(robotCoords, desiredPosition));
		// if (desiredPosition.x < robotCoords.x) {
		// 	goStraightOrTurn(270);
		// } else if (desiredPosition.x > robotCoords.x) { 
		// 	goStraightOrTurn(90);
		// } else if (desiredPosition.x == robotCoords.x) {
		// 	if (desiredPosition.y < robotCoords.y) {
		// 		goStraightOrTurn(0);
		// 	} else if (desiredPosition.y > robotCoords.y) {
		// 		goStraightOrTurn(180);
		// 	} 
		// }
	}

	private double[][] findGhostVelocity(Coord[] currentPos, Coord[] previousPos) {
		double[][] velocities = new double[currentPos.length][2];
		for (int i = 0; i < currentPos.length; i++) {
			velocities[i][0] = currentPos[i].distance(previousPos[i]);
		}
		return velocities;
	}

	private void goAwayFrom(Coord point) {
		if (point.y < robotCoords.y) {
			goStraightOrTurn(180);
		} else if (point.y > robotCoords.y) {
			goStraightOrTurn(0);
		} else if (point.y == robotCoords.y) {
			if (point.x < robotCoords.x) {
				goStraightOrTurn(90);
			} else if (point.x > robotCoords.x) { 
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
		this.dotLocations =  Coord.intArrToCoord(Robot.dotSensor.getDotLocations());
		this.ghostLocations =  Coord.intArrToCoord(Robot.ghostSensor.getGhostLocations());
		this.prevGhostPos = this.ghostLocations;
		this.robotCoords.set(Robot.driveTrain.getPositionX(), Robot.driveTrain.getPositionY());
	}

	@Override
	protected void execute() {
		dotLocations = Coord.intArrToCoord(Robot.dotSensor.getDotLocations());
		ghostLocations = Coord.intArrToCoord(Robot.ghostSensor.getGhostLocations());
		robotCoords.set(Robot.driveTrain.getPositionX(), Robot.driveTrain.getPositionY());
		nextTarget = robotCoords.closestTo(dotLocations);
		findGhostVelocity(ghostLocations, prevGhostPos);
		closestGhost = robotCoords.closestTo(ghostLocations, ghostSensitivity);
		if (!closestGhost.exists()) {
			goTo(nextTarget);
			System.out.println("Going to");
		} else {
			goAwayFrom(closestGhost);
			System.out.println("Going away from");
		}
		prevGhostPos = ghostLocations;
		System.out.println("Robot Coords: " + Coord.toString(robotCoords));
		System.out.println("Closest Ball: " + Coord.toString(nextTarget));
		System.out.println("Ghosts: " + Arrays.deepToString(Robot.ghostSensor.getGhostLocations()));
		System.out.println("Closest Ghost: " + Coord.toString(robotCoords.closestTo(ghostLocations)));
	}
	
	@Override
	protected boolean isFinished() {
		return (dotLocations.length == 0) ? true : false;
	}
}
