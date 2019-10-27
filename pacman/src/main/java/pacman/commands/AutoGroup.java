package pacman.commands;

import pacman.base.CommandGroupBase;

public class AutoGroup extends CommandGroupBase{
	
	public AutoGroup() {
		addSequential(new pacman()); 
		pacman.class.move(10);
		pacman.class.execute();
	} 
}
