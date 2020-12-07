package pacman.commands;

import javax.print.event.PrintJobListener;

import pacman.base.CommandBase;
import pacman.robot.Robot;

public class DriveStraight extends CommandBase {

    int distance;
    double start;

    public DriveStraight(int distance) {
        this.distance = distance;
    }

    @Override
	protected void initialize() {
        System.out.println("DriveStraight.initialize");
        this.start = Robot.driveTrain.getDistance();
	}

	@Override
	protected void execute() {
        Robot.driveTrain.tankDrive(1, 1);
        System.out.println(Robot.driveTrain.getDistance() - start);
        
	}
	
	@Override
	protected boolean isFinished() {
		return Robot.driveTrain.getDistance() - start == distance;
	}
}
