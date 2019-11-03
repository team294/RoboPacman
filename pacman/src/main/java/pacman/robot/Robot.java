package pacman.robot;

import pacman.base.RobotBase;
import pacman.base.RobotRunner;
import pacman.print.Printable;
import pacman.print.StatusPrinter;
import pacman.commands.*;

public class Robot extends RobotBase implements Printable {

	public static long RUN_NORMAL = 50;
	public static long RUN_FAST = 10;
	public static long RUN_SLOW = 250;
	
	public static void main(String[] args) {

		// create a robot runner
		RobotRunner robotRunner = new RobotRunner();

		// create a robot
		Robot robot = new Robot();
		StatusPrinter.addPrintable(robot);

		// run the command group
		robotRunner.run(robot, new AutoGroup(), RUN_NORMAL,1);

	}

	@Override
    public String printStatus() {
        return "This is the status of Robot.";
    }

}
