package pacman.commands;

import pacman.base.CommandGroupBase;

public class AutoGroup extends CommandGroupBase{
	
	public AutoGroup() {
		addSequential(new DriveStraight(6));
		addSequential(new Turn(270));  
		addSequential(new DriveStraight(10));
		addSequential(new Turn(180));
		addSequential(new DriveStraight(6));
		addSequential(new Turn(90));
		addSequential(new DriveStraight(9));
		addSequential(new Turn(0));
		addSequential(new DriveStraight(3));
		addSequential(new Turn(270));
		addSequential(new DriveStraight(8));
		addSequential(new Turn(90));
		addSequential(new DriveStraight(4));
		addSequential(new Turn(0));
		addSequential(new DriveStraight(2));
		addSequential(new Turn(180));
		addSequential(new DriveStraight(5));
	
		
		
	} 
	
}
