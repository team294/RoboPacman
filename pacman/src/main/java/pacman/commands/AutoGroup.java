package pacman.commands;

import pacman.base.CommandGroupBase;

public class AutoGroup extends CommandGroupBase{
	
	public AutoGroup() {
		addSequential(new Turn(90));
		addSequential(new DriveStraight10(5));  
	} 
	
}
