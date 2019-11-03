package pacman.commands;

import pacman.base.CommandBase;
import pacman.robot.Robot;
import pacman.print.Printable;

public class DriveForward extends CommandBase implements Printable {

    double distToTravel, distInitial;

    /**
     * Drives pacman forward a fixed distance
     * @param distance Number of field cells to move forward
     */
    public DriveForward(double distance) {
        distToTravel = distance;
    }

    /**
     * Drives pacman forward 2 cells
     * @param distance Number of field cells to move forward
     */
    public DriveForward() {
        distToTravel = 2.0;
    }

	@Override
	protected void initialize() {
        System.out.println("DriveForward.initialize");
        distInitial = Robot.driveTrain.getDistance();
	}

	@Override
	protected void execute() {
        System.out.println("DriveForward.execute");
		Robot.driveTrain.tankDrive(1, 1);
	}
	
	@Override
	protected boolean isFinished() {
        System.out.println("DriveForward.isFinished");
        if (Robot.driveTrain.getDistance() >= distInitial + distToTravel) {
            Robot.driveTrain.tankDrive(0, 0);
            return true;
        }
		return false;
    }
    
    @Override
    public String printStatus() {
        return "This is the status of DriveForward.  Current distance = " + Robot.driveTrain.getDistance();
    }

}
