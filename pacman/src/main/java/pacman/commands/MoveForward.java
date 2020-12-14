package pacman.commands;



import pacman.base.CommandBase;
import pacman.robot.Robot;
public class MoveForward extends CommandBase{
	

	public MoveForward (int steps){
		super();
		count = steps;
	}
	int stepsMoved = 0;
	int count = 0;

	@Override
	protected void initialize() {
		System.out.println("SpinForever.initialize");
	}

	@Override
	protected void execute() {
		Robot.driveTrain.tankDrive(1, 1);
		stepsMoved += 1;
	}
	
	@Override
	protected boolean isFinished() {
		if(stepsMoved >= count ) {
			return true;
		}
		else {
			return false;
		}
	
	}
}


