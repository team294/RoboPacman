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

		// run the command group
		 System.out.println("Before I run");
		 robotRunner.run(robot, new AutoGroup(), RUN_FAST,6);
		//robotRunner.

		// System.out.println("I'm gonna get my dots");
		// int[][] dots = Robot.dotSensor.getDotLocations();
		
		// for(int i = 0; i < dots.length; i++) {
			

		// 	for(int j = 0; j < dots[i].length; j++) {
				
		// 		System.out.println(dots[i][j]);
				
				
		// 	}
			
		// }

		

		

		
	}

}
