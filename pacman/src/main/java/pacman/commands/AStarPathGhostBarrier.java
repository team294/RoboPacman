package pacman.commands;

import pacman.base.CommandBase;
import pacman.robot.Robot;

import pacman.subsystems.Coord;
import pacman.subsystems.astar.*;

import java.util.ArrayList;
import java.util.Arrays;

// TODO add 1 wide not traversable barrier around ghosts
// TODO check to see if closest dot is traversable, if not choose next one, etc.
// TODO add boolean parameter that will tell whether to turn on ghost barrier option
// TODO maybe check to see if ghost has moved, if yes add barrier, if not do nothing
public class AStarPathGhostBarrier extends CommandBase {
	Coord start;
	Coord[] obstacles;
	Coord[] dotLocations;
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

	private Coord[] getBarrierObstacles(Coord[] obstacles) {
		ArrayList<Coord> list = new ArrayList<Coord>(Arrays.asList(obstacles));
		for (Coord obstacle: obstacles) {
			list.addAll(Arrays.asList(obstacle.findNeighbors()));
			list.add(obstacle);
		}
		return list.toArray(new Coord[0]);
	}

	@Override
	protected void initialize() {
		System.out.println("AStarPathGhostBarrier.initialize");
		start = new Coord(Robot.driveTrain.getPositionX(), Robot.driveTrain.getPositionY());

		// get obstacles and add barrier
		obstacles = Coord.intArrToCoord(Robot.ghostSensor.getGhostLocations());
		obstacles = getBarrierObstacles(obstacles);
		
		// make map
		map = new Field(11, 8, obstacles);
		map.setExtremes(1, 1, 11, 8);
	}
	
	@Override
	protected void execute() {
		start.set(Robot.driveTrain.getPositionX(), Robot.driveTrain.getPositionY());
		obstacles = Coord.intArrToCoord(Robot.ghostSensor.getGhostLocations());
		obstacles = getBarrierObstacles(obstacles);
		map.update(obstacles);

		System.out.println(Coord.toString(map.obstacles));

		dotLocations = Arrays.asList(Coord.intArrToCoord(Robot.dotSensor.getDotLocations()))
			.stream()
			.filter(x -> x.traversable(map))
			.toArray(Coord[]::new);

		if (dotLocations.length == 0) {
			return;
		}

		Coord goal = start.closestTo(dotLocations);

		System.out.println("Start: " + Coord.toString(start));
		System.out.println("Goal: " + Coord.toString(goal));
		System.out.println("Obstacles" + Coord.toString(map.obstacles));

		AStar route = new AStar(map, start, goal);
		Node res = route.calculate();

		System.out.println("Res: " + res.pathLength);
		System.out.println(Coord.toString(res));
		Node last = res;
		for (int i = 1; i < res.pathLength; i++) {
			System.out.println(Coord.toString(last.parent));
			last = last.parent;
		}
		follow(last);
	}
	
	@Override
	protected boolean isFinished() {
		return (dotLocations.length == 0) ? true : false;
	}

}
