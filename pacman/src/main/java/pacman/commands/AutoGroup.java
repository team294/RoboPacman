package pacman.commands;

import pacman.base.CommandGroupBase;

public class AutoGroup extends CommandGroupBase{
	
	public AutoGroup() {
		// addSequential(new SpinForever());


		addSequential(new MoveForwardAvoidGhosts(3));
		addSequential(new Turn(270));
		addSequential(new MoveForwardAvoidGhosts(5));
		addSequential(new Turn(180));
		addSequential(new MoveForwardAvoidGhosts(4));

		addSequential(new Turn(270));
		addSequential(new MoveForwardAvoidGhosts(5));
		addSequential(new Turn(0));
		addSequential(new MoveForwardAvoidGhosts(3));

		addSequential(new Turn(90));
		addSequential(new MoveForwardAvoidGhosts(5));
		addSequential(new Turn(0));
		addSequential(new MoveForwardAvoidGhosts(3));

		addSequential(new Turn(270));
		addSequential(new MoveForwardAvoidGhosts(5));
		addSequential(new Turn(180));
		addSequential(new MoveForwardAvoidGhosts(3));

		addSequential(new Turn(90));
		addSequential(new MoveForwardAvoidGhosts(5));
		addSequential(new Turn(0));
		addSequential(new MoveForwardAvoidGhosts(3));

		addSequential(new Turn(90));
		addSequential(new MoveForwardAvoidGhosts(5));
		addSequential(new Turn(180));
		addSequential(new MoveForwardAvoidGhosts(7));

		addSequential(new Turn(270));
		addSequential(new MoveForwardAvoidGhosts(5));
	} 
	
}
