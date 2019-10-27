package pacman.commands;

//import com.sun.org.apache.xml.internal.security.Init;

//import com.sun.tools.javac.jvm.Target;

import pacman.base.CommandBase;
import pacman.robot.Robot;

public class Turn extends CommandBase {
	private double initial; 
	private double target;
	public Turn(double face) {
		target = face;
	}

	@Override
	protected void initialize() {
		System.out.println("Turn.initialize");
	}

	@Override
	protected void execute() {
		Robot.driveTrain.tankDrive(1, 0);
	}
	
	@Override
	protected boolean isFinished() {
		if (Robot.driveTrain.getAngle() ==target) {
			return true;
		}
		return false;
	}

}
