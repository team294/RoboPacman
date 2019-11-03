package pacman.commands;

import pacman.base.CommandBase;
import pacman.robot.Robot;

public class threeCommand extends CommandBase {

	private int counter = 0;
	


		

	
	@Override
	protected boolean isFinished() {
		counter = counter+1;
 		if (counter == 3) {
			 return true;
		 }
		 return false;
	}

}
