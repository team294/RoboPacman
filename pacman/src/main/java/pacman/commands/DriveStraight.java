package pacman.commands;

//import com.sun.tools.javac.jvm.Target;

import pacman.base.CommandBase;
import pacman.robot.Robot;

public class DriveStraight extends CommandBase {
private double initial;
private double target; 
public DriveStraight(double dot) {
	target = dot;
}
	@Override
	protected void initialize() {
		initial = Robot.driveTrain.getDistance();
	}

	@Override
	protected void execute() {
		Robot.driveTrain.tankDrive(1, 1);
	}
	
	@Override
	protected boolean isFinished() {
		if (Robot.driveTrain.getDistance() >= initial+target) {
			return true;
		}
		return false;
	}

}
