package pacman.commands;

import pacman.base.CommandGroupBase;

public class AutoGroup extends CommandGroupBase{
	
	public AutoGroup() {
		addSequential(new DriveStraight(6)); 
		addSequential(new Turn("left"));
		addSequential(new DriveStraight(10));
		addSequential(new Turn("down"));
		addSequential(new DriveStraight(6));
		addSequential(new Turn("right"));
		addSequential(new DriveStraight(10));
		addSequential(new Turn("up"));
		addSequential(new DriveStraight(3));
		addSequential(new Turn("left"));
		addSequential(new DriveStraight(9));
		addSequential(new Turn("right"));
		addSequential(new DriveStraight(4));
		addSequential(new Turn("up"));
		addSequential(new DriveStraight(3));
		addSequential(new Turn("down"));
		addSequential(new DriveStraight(5));
	} 
	
}
