package pacman.commands;



import pacman.base.CommandBase;
import pacman.robot.Robot;
public class TurnLeft extends CommandBase{
	

	public TurnLeft (int degrees){
		super();
		count = degrees;
	}
	int degreesRotated = 0;
	int count = 0;

	@Override
	protected void initialize() {
		System.out.println("SpinForever.initialize");
	}

	@Override
	protected void execute() {
		Robot.driveTrain.tankDrive(0, 1);
		degreesRotated += 90;
	}
	
	@Override
	protected boolean isFinished() {
		if(degreesRotated >= count +90) {
			return true;
		}
		else {
			return false;
		}
	
	}
}
