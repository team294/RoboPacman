package pacman.commands;

import pacman.base.CommandBase;
import pacman.robot.Robot;

public class InstantCommand extends CommandBase {
	int timesThrough = 1;
	@Override
	protected boolean isFinished() {
		if(timesThrough >= 20){
			return true;
		}
		else{
			timesThrough++;
			return false;
		}
	}


}
