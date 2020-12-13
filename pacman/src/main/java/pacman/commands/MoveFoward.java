package pacman.commands;
import pacman.base.CommandBase;
import pacman.robot.Robot;
public class MoveFoward extends CommandBase{
    
    
    int spotsMoved = 0;
    int spotsCounted =0;
   public MoveFoward (int spots){
    super();
    spotsCounted = spots;
   }

    @Override
	protected void initialize() {
        spotsMoved = 0;
		System.out.println("SpinForever.initialize");
	}
   
    @Override
    
	protected void execute() {
        Robot.driveTrain.tankDrive(1, 1);
        spotsMoved += 1;
	}
	
	@Override
	protected boolean isFinished() {
        if(spotsMoved >= spotsCounted){
           
            return true;
        } else {
            return false;
        }

		
	}


}
