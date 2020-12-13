package pacman.commands;

import java.util.Arrays;

import pacman.base.CommandBase;
import pacman.robot.Robot;

public class FindDots extends CommandBase {

	private int[][] dotLocations;
	private int[][] ghostLocations;
	private int[] robotCoords = new int[2];
	private int[] nextTarget;
	private int[] closestGhost;
	private int[] farPoint = {100, 100}; // creates a point the findClosestToRobot function can use as an extreme starting value
	private int ghostSensitivity = 3;
	
	
	private double distanceForm(int[] a, int[] b) { // finds distance between to coordinates in array form (x, y)
		return Math.sqrt(Math.pow(b[0]-a[0], 2) + Math.pow(b[1]-a[1], 2));
	}

	private int[] findClosestToRobot(int[][] dotArr, int minDistance) {
		int[] coords = farPoint;
		for (int[] point: dotArr) {
			coords = (distanceForm(robotCoords, point) <= minDistance) ? (distanceForm(robotCoords, point) < distanceForm(robotCoords, coords)) ? point : coords : coords;
		}
		return (coords == farPoint) ? new int[] {} : coords;
	}
	
	private void goStraightOrTurn(int desiredAngle) {
		Robot.driveTrain.tankDrive((Robot.driveTrain.getAngle() == desiredAngle) ? 1 : (desiredAngle == 270 || desiredAngle == 0) ? (Robot.driveTrain.getAngle()-180 < 0) ? 0 : 1 : (Robot.driveTrain.getAngle()-180 >= 0) ? 0 : 1, (Robot.driveTrain.getAngle() == desiredAngle) ? 1 : (desiredAngle == 270 || desiredAngle == 0) ? (Robot.driveTrain.getAngle()-180 >= 0) ? 0 : 1 : (Robot.driveTrain.getAngle()-180 < 0) ? 0 : 1);
	}

	private void goTo(int[] desiredPosition) {
		goStraightOrTurn((desiredPosition[0] < robotCoords[0]) ? 270 : (desiredPosition[0] > robotCoords[0]) ? 90 : (desiredPosition[1] < robotCoords[1]) ? 0 : 180);
	}

	@Override
	protected void initialize() {
		System.out.println("FindDots.initialize");
		this.dotLocations = Robot.dotSensor.getDotLocations();
		this.ghostLocations = Robot.ghostSensor.getGhostLocations();
		this.robotCoords[0] = Robot.driveTrain.getPositionX(); 
		this.robotCoords[1] = Robot.driveTrain.getPositionY();
	}

	@Override
	protected void execute() {
		dotLocations = Robot.dotSensor.getDotLocations();
		ghostLocations = Robot.ghostSensor.getGhostLocations();
		robotCoords[0] = Robot.driveTrain.getPositionX(); 
		robotCoords[1] = Robot.driveTrain.getPositionY();
		nextTarget = findClosestToRobot(dotLocations, farPoint[0]);
		closestGhost = findClosestToRobot(ghostLocations, ghostSensitivity);
		if (closestGhost.length == 0) {
			goTo(nextTarget);
		}
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
