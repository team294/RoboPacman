package pacman.base;

import java.util.concurrent.ThreadLocalRandom;

import pacman.graphics.PacmanGraphics;
import pacman.print.StatusPrinter;

public class RobotRunner {

	public static final int MAX_TIME = 60;

	private DriveTrainEngine driveTrainEngine = new DriveTrainEngine();
	private GhostSensorEngine ghostSensorEngine = new GhostSensorEngine();

	public void run(RobotBase robot, CommandGroupBase group) {
		run(robot,group,100,1);
	}

	public void run(RobotBase robot, CommandGroupBase group, long delay, int level) {

		boolean running = true;
		boolean runInit = true;
		int currentCommand = 0;
		int startingPosition = 4;
		int ping = 0;
		int radar = 0;
		int directionalRadar = 0;

		Util.log("RobotRunner:robot.robotInit version:"+RobotBase.VERSION);
		robot.robotInit();

		PacmanGraphics graphics = new PacmanGraphics();
		graphics.setup(level);

		// starting position is randomized for level 3
		if (level == 3) {
			startingPosition = ThreadLocalRandom.current().nextInt(1,5);
		}

		driveTrainEngine.setup(startingPosition);
		driveTrainEngine.update(RobotBase.driveTrain);

		graphics.drawField(robot);

		while (running && !graphics.getCaught() && graphics.getTime() < MAX_TIME) {

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

				// update all sensors before executing command
				ping = ghostSensorEngine.getDirectionalRadar(RobotBase.driveTrain.getPositionX(), 
						RobotBase.driveTrain.getPositionY(), 
						RobotBase.driveTrain.getAngle(), 
						graphics.getGhostList());

				RobotBase.ghostSensor.setPing(ping);
				RobotBase.ghostSensor.setGhostLocations(graphics.getGhostLocations());

				radar = ghostSensorEngine.getRadar(RobotBase.driveTrain.getPositionX(), 
						RobotBase.driveTrain.getPositionY(), 
						RobotBase.driveTrain.getAngle(), 
						graphics.getGhostList());

				RobotBase.ghostSensor.setRadar(radar);

				directionalRadar = ghostSensorEngine.getDirectionalRadar(RobotBase.driveTrain.getPositionX(), 
						RobotBase.driveTrain.getPositionY(), 
						RobotBase.driveTrain.getAngle(), 
						graphics.getGhostList());

				RobotBase.ghostSensor.setDirectionalRadar(directionalRadar);

				// run the commands init
				if (runInit) {
					Util.log("RobotRunner:initialize command "+command.getClass().getName());
					command.initialize();
					runInit = false;
				}

				// run the command
				Util.log("RobotRunner:execute command "+command.getClass().getName());
				command.execute();	

				//move pacman based upon current values from the drivetrain
				driveTrainEngine.tankDrive(RobotBase.driveTrain);
			}

			graphics.drawField(robot);
			StatusPrinter.print();

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
