package pacman.robot;

import pacman.base.RobotBase;
import pacman.base.RobotRunner;
import pacman.commands.*;

public class Robot extends RobotBase {

	public static long RUN_NORMAL = 250;
	public static long RUN_FAST = 50;
	public static long RUN_SLOW = 500;
	public static int LEVEL = 1;
	
	public static void main(String[] args) {

		// create a robot runner
		RobotRunner robotRunner = new RobotRunner();

		// create a robot
		Robot robot = new Robot();

		// run the command group
		robotRunner.run(robot, new AutoGroup(), RUN_NORMAL, LEVEL);

	}

}
