package pacman.commands;

import pacman.base.CommandGroupBase;

public class AutoGroup extends CommandGroupBase{
	
	public AutoGroup() {
		// Paul's code
		//addSequential(new SpinForever());  

		addSequential(new SpinOnce());  
		addSequential(new MoveFoward(6));
		addSequential(new TurnRight(270));
		addSequential(new MoveFoward(10));
		addSequential(new TurnLeft(90));
		addSequential(new MoveFoward(6));
		addSequential(new TurnLeft(90));
		addSequential(new MoveFoward(5));
		addSequential(new TurnLeft(90));
		addSequential(new MoveFoward(3));
		addSequential(new TurnLeft(90));
		addSequential(new MoveFoward(4));
		addSequential(new TurnRight(180));
		addSequential(new MoveFoward(8));
		addSequential(new TurnRight(90));
		addSequential(new MoveFoward(3));
		addSequential(new TurnRight(90));
		addSequential(new MoveFoward(4));
		addSequential(new TurnRight(90));
		addSequential(new MoveFoward(6));
	} 

}
