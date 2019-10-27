package pacman.commands;

import pacman.base.CommandGroupBase;

public class AutoGroup extends CommandGroupBase{
	
	public AutoGroup() {
		addSequential(new DriveStraight10(7));
		addSequential(new Turn(270));
		

		

	} 
	
}
