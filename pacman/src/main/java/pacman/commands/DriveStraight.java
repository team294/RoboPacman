package pacman.commands;

import java.util.concurrent.atomic.DoubleAccumulator;

import pacman.base.CommandBase;
import pacman.robot.Robot;

public class DriveStraight extends CommandBase {

	private double desiredDistance;
	// private int[] turnDirection = new int[2]; // turn left or turn right to get there quickest 
	
	public DriveStraight(double distance) {
		this.desiredDistance = distance;
		// turnDirection[0] = (this.desiredAngle) ? 1 : 0
	}

	@Override
	protected void initialize() {
		System.out.println("DriveStraight.initialize");
		this.desiredDistance = this.desiredDistance + Robot.driveTrain.getDistance();
	}

	@Override
	protected void execute() {
		Robot.driveTrain.tankDrive(1, 1);
	}
	
	@Override
	protected boolean isFinished() {
		System.out.println("Desired Dis: " + this.desiredDistance);
		System.out.println("Current Dis: " + Robot.driveTrain.getDistance());
		return (Robot.driveTrain.getDistance() >= this.desiredDistance) ? true : false;
	}

}
