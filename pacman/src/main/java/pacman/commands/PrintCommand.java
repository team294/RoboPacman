package pacman.commands;

import pacman.base.CommandBase;
import pacman.robot.Robot;

public class PrintCommand extends InstantCommand {
	private String stringToPrint;

	public PrintCommand(String s) {
		stringToPrint = s;
	}

	@Override
	protected void initialize() {
		System.out.println(stringToPrint);
	}

}
