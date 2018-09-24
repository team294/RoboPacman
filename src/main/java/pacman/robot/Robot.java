package pacman.robot;

import pacman.base.DriveTrain;
import pacman.base.RobotBase;
import pacman.base.RobotRunner;
import pacman.commands.*;

public class Robot extends RobotBase {

	public static int MAX_SPEED = 5;
	public static long RUN_NORMAL = 50;
	public static long RUN_FAST = 10;
	public static long RUN_SLOW = 250;

	public static DriveTrain driveTrain = new DriveTrain();

	public static void main(String[] args) {

		// create a robot runner
		RobotRunner robotRunner = new RobotRunner();

		// create a robot
		Robot robot = new Robot();

		// run the command group
		robotRunner.run(robot, new SpinGroup(), RUN_SLOW);

	}

}
