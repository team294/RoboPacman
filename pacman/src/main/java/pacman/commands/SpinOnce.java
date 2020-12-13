package pacman.commands;

import pacman.base.CommandBase;
import pacman.robot.Robot;

public class SpinOnce extends CommandBase{

    @Override
    protected void initialize() {
        System.out.println("SpinOnce.initialize");
    }

    @Override
    protected void execute() {
        Robot.driveTrain.tankDrive(0,1);
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
    
}
