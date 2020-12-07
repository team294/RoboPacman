package pacman.commands;

import pacman.base.CommandGroupBase;

public class AutoGroup extends CommandGroupBase{
	
	public AutoGroup() {
		// addSequential(new SpinForever());

		// addSequential(new SpinOnce());
		// MoveForward mymove=new MoveForward();
		// mymove.setMoveStep(4);
		// addSequential(mymove);


		addSequential(new MoveForward(3));
		addSequential(new Turn(270));
		addSequential(new MoveForward(5));
		addSequential(new Turn(180));
		addSequential(new MoveForward(4));

		addSequential(new Turn(270));
		addSequential(new MoveForward(5));
		addSequential(new Turn(0));
		addSequential(new MoveForward(3));

		addSequential(new Turn(90));
		addSequential(new MoveForward(5));
		addSequential(new Turn(0));
		addSequential(new MoveForward(3));

		addSequential(new Turn(270));
		addSequential(new MoveForward(5));
		addSequential(new Turn(180));
		addSequential(new MoveForward(3));

		addSequential(new Turn(90));
		addSequential(new MoveForward(5));
		addSequential(new Turn(0));
		addSequential(new MoveForward(3));

		addSequential(new Turn(90));
		addSequential(new MoveForward(5));
		addSequential(new Turn(180));
		addSequential(new MoveForward(7));

		addSequential(new Turn(270));
		addSequential(new MoveForward(5));
	} 
	
}
