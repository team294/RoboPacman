package pacman.commands;

import pacman.base.CommandGroupBase;

public class AutoGroup extends CommandGroupBase{
	
	public AutoGroup() {
		addSequential(new DriveStraight(6));
		addSequential(new Turn(270));  
		addSequential(new DriveStraight(15));
		addSequential(new Turn(0));
		addSequential(new DriveStraight(6));
	} 
	
}
