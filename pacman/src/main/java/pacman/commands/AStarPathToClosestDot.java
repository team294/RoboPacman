package pacman.commands;

import pacman.base.CommandBase;
import pacman.robot.Robot;

import pacman.subsystems.Coord;
import pacman.subsystems.astar.*;

public class AStarPathToClosestDot extends CommandBase {
	Coord start;
	Coord[] obstacles;
	Field map = new Field(5, 5, Coord.intArrToCoord(new int[][] {{1, 4}, {2, 3}, {3, 0}}));

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
        goStraightOrTurn(findDesiredAngle(start, nextOnPath));
    }

	@Override
	protected void initialize() {
		System.out.println("AStarPathTest.initialize");
		start = new Coord(Robot.driveTrain.getPositionX(), Robot.driveTrain.getPositionY());
		obstacles = Coord.intArrToCoord(Robot.ghostSensor.getGhostLocations());

		map = new Field(11, 8, obstacles);

		map.setExtremes(1, 1, 11, 8);
	}
	
	@Override
	protected void execute() {
		start.set(Robot.driveTrain.getPositionX(), Robot.driveTrain.getPositionY());
		obstacles = Coord.intArrToCoord(Robot.ghostSensor.getGhostLocations());
		map.update(obstacles);
		System.out.println(Coord.toString(map.obstacles));

		Node res = new Node();

		for (Coord goal: Coord.intArrToCoord(Robot.dotSensor.getDotLocations())) {
			System.out.println("Start: " + Coord.toString(start));
			System.out.println("Goal: " + Coord.toString(goal));
			System.out.println("Obstacles" + Coord.toString(map.obstacles));

			AStar route = new AStar(map, start, goal);
			Node path = route.calculate();

			if (path.pathLength < res.pathLength) {
				res = path;
			}

			System.out.println("Res: " + path.pathLength);
			System.out.println(Coord.toString(path));
		}

		Node last = res;
		for (int i = 1; i < res.pathLength; i++) {
			System.out.println(Coord.toString(last.parent));
			last = last.parent;
		}

		follow(last);
	}
	
	@Override
	protected boolean isFinished() {
		return (Robot.dotSensor.getDotLocations().length == 0) ? true : false;
	}

}
