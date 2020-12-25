package pacman.commands;

import pacman.base.CommandGroupBase;

public class AutoGroup extends CommandGroupBase{
    
	public AutoGroup() {
		// Challenge 1 - Spin Forever or Spin Once
		// addSequential(new SpinForever());
		// addSequential(new SpinOnce());

		// Challenge 2 - Eat the Dots (pre-determined dots in a pattern)
		// addSequential(new MoveForwardAvoidGhosts(3));
		// addSequential(new Turn(270));
		// addSequential(new MoveForwardAvoidGhosts(5));
		// addSequential(new Turn(180));
		// addSequential(new MoveForwardAvoidGhosts(4));

		// addSequential(new Turn(270));
		// addSequential(new MoveForwardAvoidGhosts(5));
		// addSequential(new Turn(0));
		// addSequential(new MoveForwardAvoidGhosts(3));

		// addSequential(new Turn(90));
		// addSequential(new MoveForwardAvoidGhosts(5));
		// addSequential(new Turn(0));
		// addSequential(new MoveForwardAvoidGhosts(3));

		// addSequential(new Turn(270));
		// addSequential(new MoveForwardAvoidGhosts(5));
		// addSequential(new Turn(180));
		// addSequential(new MoveForwardAvoidGhosts(3));

		// addSequential(new Turn(90));
		// addSequential(new MoveForwardAvoidGhosts(5));
		// addSequential(new Turn(0));
		// addSequential(new MoveForwardAvoidGhosts(3));

		// addSequential(new Turn(90));
		// addSequential(new MoveForwardAvoidGhosts(5));
		// addSequential(new Turn(180));
		// addSequential(new MoveForwardAvoidGhosts(7));

		// addSequential(new Turn(270));
		// addSequential(new MoveForwardAvoidGhosts(5));

		// Challenge 2 Bonus - Move Forwrad and Avoid the Ghosts
		// addSequential(new MoveForwardAvoidGhosts());

		// Challenge 3 - Eat all the random dots
		addSequential(new EatAllDots());
	} 
	
}
