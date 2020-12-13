package pacman.commands;

import pacman.base.CommandGroupBase;

public class EatTheDots extends CommandGroupBase{
	
	public EatTheDots() {
		// do outside square first

		// left for bottom row
		addSequential(new TurnToAngle(270));  
		addSequential(new DriveStraight(10));  

		// up for left side
		addSequential(new TurnToAngle(0));  
		addSequential(new DriveStraight(6));  

		// right for top
		addSequential(new TurnToAngle(90));  
		addSequential(new DriveStraight(10));  

		// down for right
		addSequential(new TurnToAngle(180));  
		addSequential(new DriveStraight(5));  

		// left to get back to middle 
		addSequential(new TurnToAngle(270));  
		addSequential(new DriveStraight(5));  

		// do verticle line in middle

		// up for middle vertical line
		addSequential(new TurnToAngle(0));  
		addSequential(new DriveStraight(4));  

		// down to middle horizonal line
		addSequential(new TurnToAngle(180));  
		addSequential(new DriveStraight(2));  

		// left
		addSequential(new TurnToAngle(270));  
		addSequential(new DriveStraight(4)); 

		// right
		addSequential(new TurnToAngle(90));  
		addSequential(new DriveStraight(8));

	} 
	
}
