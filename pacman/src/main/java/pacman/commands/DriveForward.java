package pacman.commands;

import pacman.base.CommandBase;
import pacman.robot.Robot;

public class DriveForward extends CommandBase {

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
		Robot.driveTrain.tankDrive(1, 1);
	}
	
	@Override
	protected boolean isFinished() {
        if (Robot.driveTrain.getDistance() >= distInitial + distToTravel) {
            Robot.driveTrain.tankDrive(0, 0);
            return true;
        }
		return false;
	}

}
