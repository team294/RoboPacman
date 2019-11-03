package pacman.commands;

import pacman.base.CommandBase;
import pacman.base.CommandGroupBase;

public class AutoGroup extends CommandGroupBase{
	
	public AutoGroup() {
		// addSequential(new DriveForward());
		// addSequential(new DriveForward());
		int myVarName; 
		myVarName = 5;

		DriveForward fwd2 = new DriveForward(2);
		DriveForward fwd3 = new DriveForward(3);

		addSequential(fwd3);
		addSequential(new TurnToAngle(270));
		addSequential(fwd3);
		addSequential(fwd2);
		addSequential(new SpinForever());  
	} 
	
}
