package pacman.commands;

import pacman.base.CommandGroupBase;

public class SpinGroup extends CommandGroupBase{
	
	public SpinGroup() {
		super();
		addSequential(new SpinForever());
	}

}
