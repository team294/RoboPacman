package pacman.commands;

import javax.print.event.PrintJobListener;
import java.util.Arrays;

import pacman.base.CommandBase;
import pacman.robot.Robot;
import pacman.commands.*;

public class GoToDots extends CommandBase{

    private int[][] dotArr;
    private int[] robotPos = new int[2];
    private int[] nextDot = new int[2];
    private int desiredAngle;
    private int currentAngle;
    private int axis;

    private static double findDist(int[] loc1, int[] loc2) {
        return Math.sqrt(Math.pow(loc2[0] - loc1[0], 2) + Math.pow(loc2[1] - loc1[1], 2));
    }

    private static int[] findDot(int[][] dotArr, int[] robotPos) {
        int[] closestPoint = dotArr[0];
        double closest = findDist(robotPos, closestPoint);
        for (int[] dot : dotArr) {
            double dist = findDist(robotPos, dot);
            if (dist < closest) closest = dist;
        }
        for (int[] dot : dotArr) {
            double dist = findDist(robotPos, dot);
            if (dist == closest) {
                closestPoint = dot;
            }
        }
        return closestPoint;
    }

    public int getAngle(int[] point, int[] robotPos) {
        int xDist = point[0] - robotPos[0];
        int yDist = point[1] - robotPos[1];
        int angle = 0;
        if (axis == 0 && xDist != 0) {
            if (xDist < 0) angle = 270;
            else if (xDist > 0) angle = 90;
        }
        else if (axis == 1 && yDist != 0) {
            if (yDist < 0) angle = 0;
            else if (yDist > 0) angle = 180;
        }
        else {
            if (Math.abs(xDist) > Math.abs(yDist)) {
                this.axis = 0;
                if (xDist < 0) angle = 270;
                else if (xDist > 0) angle = 90;
            }
            else if (Math.abs(yDist) > Math.abs(xDist)) {
                this.axis = 1;
                if (yDist < 0) angle = 0;
                else if (yDist > 0) angle = 180;
            }
            else {
                this.axis = 0;
                if (xDist < 0) angle = 270;
                else if (xDist > 0) angle = 90;
            }
        }
        
        return angle;
    }

    @Override
	protected void initialize() {
        this.dotArr = Robot.dotSensor.getDotLocations();
        this.robotPos[0] = Robot.driveTrain.getPositionX();
        this.robotPos[1] = Robot.driveTrain.getPositionY();
        this.currentAngle = Robot.driveTrain.getAngle();
	}

	@Override
	protected void execute() {
        dotArr = Robot.dotSensor.getDotLocations();
        nextDot = findDot(dotArr, robotPos);
        robotPos[0] = Robot.driveTrain.getPositionX();
        robotPos[1] = Robot.driveTrain.getPositionY();
        desiredAngle = getAngle(nextDot, robotPos);
        currentAngle = Robot.driveTrain.getAngle();
        if (desiredAngle != currentAngle) {
            if ((desiredAngle - currentAngle + 360) % 360 < 180) Robot.driveTrain.tankDrive(1, 0);
            else Robot.driveTrain.tankDrive(0, 1);
        }
        else {
            Robot.driveTrain.tankDrive(1, 1);
        }
        System.out.println("Next Dot: " + Arrays.toString(nextDot));
        System.out.println("Robot Pos: " + Arrays.toString(robotPos));
        System.out.println("Desired Angle: " + desiredAngle);
	}
	
	@Override
	protected boolean isFinished() {
		return dotArr.length == 0;
	}
}
