package pacman.commands;

import pacman.base.CommandBase;
import pacman.robot.Robot;

public class InstantCommand extends CommandBase {

	@Override
	protected boolean isFinished() {
		return true;
	}

}
