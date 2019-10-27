package pacman.commands;
import pacman.base.CommandBase;
import pacman.robot.Robot;
public class goFoward extends CommandBase {
	@Override
	protected void initialize() {
		System.out.println("Go Foward.initialize");
		execute(2,2);
	}

	@Override
	protected void execute(int number1, int number2) {
		Robot.driveTrain.tankDrive(number1, number2);
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}
}