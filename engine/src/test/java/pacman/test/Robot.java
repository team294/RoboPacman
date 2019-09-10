package pacman.test;

import pacman.base.RobotBase;
import pacman.base.RobotRunner;


public class Robot extends RobotBase {

	public static long RUN_NORMAL = 50;
	public static long RUN_FAST = 10;
	public static long RUN_SLOW = 250;
	
	public static void main(String[] args) {

		// create a robot runner
		RobotRunner robotRunner = new RobotRunner();

		// create a robot
		Robot robot = new Robot();

		// run the command group
		robotRunner.run(robot, new TestGroup(), RUN_SLOW, 6);

	}

}
