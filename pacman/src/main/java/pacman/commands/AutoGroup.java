package pacman.commands;

import pacman.base.CommandGroupBase;

public class AutoGroup extends CommandGroupBase{
	
	public AutoGroup() {
		addSequential(new SpinOnce()); 
		addSequential(new DriveForward());
		addSequential(new TurnRight());
	} 
	
}
