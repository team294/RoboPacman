package pacman.commands;

import pacman.base.CommandGroupBase;

public class AutoGroup extends CommandGroupBase{
	
	public AutoGroup() {
		// addSequential(new FieldTest());  
		// addSequential(new AStarPathGhostBarrier());  
		// addSequential(new AStarPathTest());  
		addSequential(new AStarPathToClosestDot());  
	} 
	
}
