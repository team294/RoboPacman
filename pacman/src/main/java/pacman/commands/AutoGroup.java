package pacman.commands;

import pacman.base.CommandBase;
import pacman.base.CommandGroupBase;

public class AutoGroup extends CommandGroupBase{
	
	public AutoGroup() {
		addSequential(new DriveStraight(6));
		addSequential(new Turn(270));
		addSequential(new DriveStraight(10));
		addSequential(new Turn(180));
		addSequential(new DriveStraight(6));
		addSequential(new Turn(90));
		addSequential(new DriveStraight(5));
		addSequential(new Turn(0));
		addSequential(new DriveStraight(6));
	}
	
}
