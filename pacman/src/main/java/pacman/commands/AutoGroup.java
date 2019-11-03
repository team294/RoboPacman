package pacman.commands;

import pacman.base.CommandBase;
import pacman.base.CommandGroupBase;
import pacman.print.StatusPrinter;

public class AutoGroup extends CommandGroupBase{
	
	public AutoGroup() {
		DriveForward fwd3 = new DriveForward(3);
		StatusPrinter.addPrintable(fwd3);

		// addSequential(new DriveForward(3));
		addSequential(fwd3);
		addSequential(new PrintCommand("Print this message instead."));
		addSequential(new TurnToAngle(270));
		// addSequential(new DriveForward(3));
		// addSequential(new SpinForever());  
	} 
	
}
