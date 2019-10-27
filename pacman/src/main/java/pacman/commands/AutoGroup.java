package pacman.commands;

import pacman.base.CommandGroupBase;

public class AutoGroup extends CommandGroupBase{
	
	public AutoGroup() {
		addSequential(new DriveStraight(6));
		addSequential(new Turn(270));  
		addSequential(new DriveStraight(16));
		addSequential(new Turn(180));
		addSequential(new DriveStraight(22));
		addSequential(new Turn(90));
		addSequential(new DriveStraight(31));
		addSequential(new Turn(0));
		addSequential(new DriveStraight(34));
		addSequential(new Turn(270));
		addSequential(new DriveStraight(42));
		addSequential(new Turn(90));
		addSequential(new DriveStraight(46));
		addSequential(new Turn(0));
		addSequential(new DriveStraight(48));
		addSequential(new Turn(180));
		addSequential(new DriveStraight(55));
		
	} 
	
}
