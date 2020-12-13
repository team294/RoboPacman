package pacman.commands;
import pacman.base.CommandBase;
import pacman.robot.Robot;


public class TurnLeft extends CommandBase{
    int degreesRotated = 0;
    int degreesCounted = 0;
    public TurnLeft (int counts){
        super();
        degreesCounted = counts +90;
    }

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
        if(degreesRotated >= degreesCounted){
           
            return true;
        } else {
            return false;
        }

		
	}
}
