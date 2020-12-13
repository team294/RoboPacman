package pacman.commands;

import pacman.base.CommandBase;
import pacman.robot.Robot;
public class SpinOnce extends CommandBase  {

    int degreesRotated = 0;
   

    @Override
	protected void initialize() {
		System.out.println("SpinForever.initialize");
	}

    @Override
    
	protected void execute() {
        Robot.driveTrain.tankDrive(1, 0);
        degreesRotated += 90;
	}
	
	@Override
	protected boolean isFinished() {
        if(degreesRotated >= 360){
           
            return true;
        } else {
            return false;
        }

		
	}

}
