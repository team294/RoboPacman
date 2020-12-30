package pacman.commands;

import pacman.base.CommandGroupBase;

public class AutoGroup extends CommandGroupBase{
	
	public AutoGroup() {
		
		
		
		//  addSequential(new MoveForward(6));
		//  addSequential(new TurnLeft(90));
		//  addSequential(new MoveForward(10));
		//  addSequential(new TurnLeft(90));
		//  addSequential(new MoveForward(6));
		//  addSequential(new TurnLeft(90));
		//  addSequential(new MoveForward(9));
		//  addSequential(new TurnLeft(90));
		//  addSequential(new MoveForward(3));
		//  addSequential(new TurnLeft(90));
		//  addSequential(new MoveForward(8));
		//  addSequential(new TurnLeft(180));
		//  addSequential(new MoveForward(4));
		//  addSequential(new TurnLeft(90));
		//  addSequential(new MoveForward(2));
		//  addSequential(new TurnLeft(180));
		//  addSequential(new MoveForward(4));  

		addSequential(new EatAllDots());


	 } 
	
}
