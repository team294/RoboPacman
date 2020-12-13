package pacman.commands;

import pacman.base.CommandGroupBase;

public class AvoidTheGhosts extends CommandGroupBase{
	
	public AvoidTheGhosts() {
		// do outside square first

		// left for bottom row
		addSequential(new TurnToAngle(270));  
		addSequential(new DriveStraightWithSensor(10));  

		// up for left side
		addSequential(new TurnToAngle(0));  
		addSequential(new DriveStraightWithSensor(6));  

		// right for top
		addSequential(new TurnToAngle(90));  
		addSequential(new DriveStraightWithSensor(10));  

		// down for right
		addSequential(new TurnToAngle(180));  
		addSequential(new DriveStraightWithSensor(5));  

		// left to get back to middle 
		addSequential(new TurnToAngle(270));  
		addSequential(new DriveStraightWithSensor(5));  

		// do verticle line in middle

		// up for middle vertical line
		addSequential(new TurnToAngle(0));  
		addSequential(new DriveStraightWithSensor(4));  

		// down to middle horizonal line
		addSequential(new TurnToAngle(180));  
		addSequential(new DriveStraightWithSensor(2));  

		// left
		addSequential(new TurnToAngle(270));  
		addSequential(new DriveStraightWithSensor(4)); 

		// right
		addSequential(new TurnToAngle(90));  
		addSequential(new DriveStraightWithSensor(8));

	} 
	
}
