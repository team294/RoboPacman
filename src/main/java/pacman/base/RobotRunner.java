package pacman.base;

import pacman.graphics.PacmanGraphics;
import pacman.robot.Robot;

public class RobotRunner {
	
	private DriveTrainEngine driveTrainEngine = new DriveTrainEngine();
	public static final int MAX_TIME = 15;
	
	public void run(RobotBase robot, CommandGroupBase group) {
		run(robot,group,100);
	}
	
	public void run(RobotBase robot, CommandGroupBase group, long delay) {
		
		boolean running = true;
		int currentCommand = 0;
		boolean runInit = true;
		
		Util.log("RobotRunner:robot.robotInit");
		robot.robotInit();
		
		PacmanGraphics graphics = new PacmanGraphics();
		graphics.setup();

		driveTrainEngine.update(Robot.driveTrain);
		
		while (running && graphics.getTime() <= MAX_TIME) {
			
			// get a command from the group
			CommandBase command = group.getCommand(currentCommand);
			
			// check to see if this command is finished and get another
			if (command != null) {
				// if command has been initialized then check to see if it is finished
				if (!runInit) {
					Util.log("RobotRunner:check if command is finished");
					boolean finished = command.isFinished(); 
					if (finished) {
						Util.log("RobotRunner:command "+command.getClass().getName()+" isFinished: true");
						currentCommand ++;
						runInit = true;
						command = group.getCommand(currentCommand);
						if (command != null) Util.log("RobotRunner:got next command "+command.getClass().getName());
					} else {
						Util.log("RobotRunner:command "+command.getClass().getName()+" isFinished: false");
					}
				} 
			}
			
			if (command == null) {
				running = false;
				Util.log("RobotRunner:no commands to run");
			} else {
				if (runInit) {
					Util.log("RobotRunner:initialize command "+command.getClass().getName());
					command.initialize();
					runInit = false;
				}
				Util.log("RobotRunner:execute command "+command.getClass().getName());
				command.execute();	
				driveTrainEngine.tankDrive(Robot.driveTrain);
			}
			
			graphics.drawField(robot);
			
			// add a little delay to make it look better
			try {
				Thread.sleep(delay);
				Util.log("--------------------------\n\n");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}

		if (graphics.getTime() >= MAX_TIME) {
			Util.log("RobotRunner:time is up");	
		}
		
		Util.log("RobotRunner:score is:"+graphics.getScore());
		
	}

	

}
