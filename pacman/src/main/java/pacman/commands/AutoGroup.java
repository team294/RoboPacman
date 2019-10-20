package pacman.commands;

import pacman.base.CommandGroupBase;

public class AutoGroup extends CommandGroupBase{
	
	public AutoGroup() {
		addSequential(new DriveForward(6));
		addSequential(new Turn(270));
		addSequential(new DriveForward(15));


		



		
	}
	
}
