package pacman.commands;

import pacman.base.CommandBase;
import pacman.robot.Robot;

import java.util.Arrays;

import pacman.subsystems.Coord;
import pacman.subsystems.astar.Field;
import pacman.subsystems.astar.AStar;

public class FollowAStarPath extends CommandBase {
    private Coord[] dotLocations;
	private Coord[] ghostLocations;
	private Coord robotCoords = new Coord();
	private Coord nextTarget;
    private Field field; //TODO set field value */
    
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
		if (currentPos.x > desiredPos.x) {
			return 270;
		} else if (currentPos.x < desiredPos.x) { 
			return 90;
		} else {
			if (currentPos.y > desiredPos.y) {
				return 0;
			} else if (currentPos.y < desiredPos.y) {
				return 180;
			} else {
				return Robot.driveTrain.getAngle();
			}
		}
	}
    
    public void follow(Coord nextOnPath) {
        goStraightOrTurn(findDesiredAngle(robotCoords, nextOnPath));
    }

    @Override
	protected void initialize() {
		System.out.println("FollowAStarPath.initialize");
		this.dotLocations =  Coord.intArrToCoord(Robot.dotSensor.getDotLocations());
		this.ghostLocations =  Coord.intArrToCoord(Robot.ghostSensor.getGhostLocations());
		this.robotCoords.set(Robot.driveTrain.getPositionX(), Robot.driveTrain.getPositionY());
	}

	@Override
	protected void execute() {
		dotLocations = Coord.intArrToCoord(Robot.dotSensor.getDotLocations());
		ghostLocations = Coord.intArrToCoord(Robot.ghostSensor.getGhostLocations());
		robotCoords.set(Robot.driveTrain.getPositionX(), Robot.driveTrain.getPositionY());
		nextTarget = robotCoords.closestTo(dotLocations);
        field.update(ghostLocations);
        
        AStar path = new AStar(field, robotCoords, nextTarget);

        // follow(path.calculate()[0]);

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
