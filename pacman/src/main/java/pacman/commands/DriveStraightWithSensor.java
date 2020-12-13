package pacman.commands;

import pacman.base.CommandBase;
import pacman.robot.Robot;

public class DriveStraightWithSensor extends CommandBase {
	
	private double targetDistance;
	
	private int startX;
	private int startY;
	private boolean success = false;
	
	public DriveStraightWithSensor(double distance) {
		// save the target distance for later
		this.targetDistance = distance;
	}

	@Override
	protected void initialize() {
		// save the starting point for later
		startX = Robot.driveTrain.getPositionX();
		startY = Robot.driveTrain.getPositionY();
	}

	@Override
	protected void execute() {
		// drive forward until we see a ghost then back up
		int ping = Robot.ghostSensor.getDirectionalRadar();
		System.out.println("Ghost ping:"+ping);
		
		if (ping > 0 && ping < 3) {
			System.out.println("Ghost approaching, run!");
			Robot.driveTrain.tankDrive(-1, -1);
		} else {
			Robot.driveTrain.tankDrive(1, 1);
		}
			
	}

	@Override
	protected boolean isFinished() {
		double currentDistance = 0;

		if (Robot.driveTrain.getAngle() == 90 ) {
			currentDistance =  Robot.driveTrain.getPositionX() - startX;
		} 

		if (Robot.driveTrain.getAngle() == 270 ) {
			currentDistance = startX - Robot.driveTrain.getPositionX();
		} 

		if (Robot.driveTrain.getAngle() == 0 ) {
			currentDistance =  startY - Robot.driveTrain.getPositionY();
		} 

		if (Robot.driveTrain.getAngle() == 180 ) {
			currentDistance = Robot.driveTrain.getPositionY() - startY;
		} 		

		System.out.println("currentDistance:"+currentDistance);

		if ( currentDistance >= targetDistance) {
			success = true;
		}

		return success;
	}

}
