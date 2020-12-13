package pacman.commands;

import pacman.base.CommandGroupBase;

public class FindTheDots extends CommandGroupBase {

	public FindTheDots() {

		addSequential(new FindDotsWithDotSensor()); 

	}
	
}