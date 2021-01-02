package pacman.robot;

import pacman.base.RobotBase;
import pacman.base.RobotRunner;

import pacman.commands.*;

public class Robot extends RobotBase {

	public static long RUN_NORMAL = 250;
	public static long RUN_FAST = 50;
	public static long RUN_SLOW = 500;

	public static void main(String[] args) {

		// create a robot runner
		RobotRunner robotRunner = new RobotRunner();

		// create a robot
		Robot robot = new Robot();
		
		// Challenge 1
		// robotRunner.run(robot, new AutoGroup(), RUN_SLOW, 1);

		// Challenge 2
		// robotRunner.run(robot, new AutoGroup(), RUN_NORMAL, 2);

		// Challenge 3
		// robotRunner.run(robot, new AutoGroup(), RUN_SLOW, 5);

		// Challenge 4
		robotRunner.run(robot, new AutoGroup(), RUN_FAST, 6);
	}

}