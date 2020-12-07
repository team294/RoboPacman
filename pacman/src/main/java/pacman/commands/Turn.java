package pacman.commands;

import java.lang.Math;
import pacman.base.CommandBase;
import pacman.robot.Robot;

public class Turn extends CommandBase {

	private int desiredAngle;
	// private int[] turnDirection = new int[2]; // turn left or turn right to get there quickest 
	
	public Turn(int degrees) {
		this.desiredAngle = degrees;
	}

	@Override
	protected void initialize() {
		System.out.println("Turn.initialize");
		this.desiredAngle = Robot.driveTrain.getAngle() + this.desiredAngle;
		this.desiredAngle = (this.desiredAngle >= 360) ? this.desiredAngle - 360 : this.desiredAngle;
		this.desiredAngle = (this.desiredAngle < 0) ? 360 - Math.abs(this.desiredAngle) : this.desiredAngle;
		// turnDirection[0] = (this.desiredAngle) ? 1 : 0
	}

	@Override
	protected void execute() {
		Robot.driveTrain.tankDrive(1, 0);
	}
	
	@Override
	protected boolean isFinished() {
		System.out.println("Desired: " + this.desiredAngle);
		System.out.println("Current: " + Robot.driveTrain.getAngle());
		return (Robot.driveTrain.getAngle() == this.desiredAngle) ? true : false;
	}

}
