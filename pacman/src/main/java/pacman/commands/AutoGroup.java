package pacman.commands;

import pacman.base.CommandGroupBase;
import pacman.base.GhostSensor;

public class AutoGroup extends CommandGroupBase{
	
	public AutoGroup() {
		// addSequential(new SpinForever());

		addSequential(new FindDots());

		// level 1
		// addSequential(new DriveStraight(3)); 
		// addSequential(new Turn(-90));
		// addSequential(new DriveStraight(10)); 
		// addSequential(new Turn(90));
		// addSequential(new DriveStraight(3)); 
		// addSequential(new Turn(90));
		// addSequential(new DriveStraight(10)); 
		// addSequential(new Turn(90));
		// addSequential(new DriveStraight(6)); 
		// addSequential(new Turn(90));
		// addSequential(new DriveStraight(10));
		// addSequential(new Turn(90));
		// addSequential(new DriveStraight(2));	
		// addSequential(new Turn(180));
		// addSequential(new DriveStraight(2));
		// addSequential(new Turn(-90));
		// addSequential(new DriveStraight(5));			
		// addSequential(new Turn(-90));
		// addSequential(new DriveStraight(6)); 
	} 
	
}
