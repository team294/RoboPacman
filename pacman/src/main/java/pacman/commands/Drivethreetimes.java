package pacman.commands;

import pacman.base.CommandBase;
import pacman.robot.Robot;

public class Drivethreetimes extends threeCommand {
private double travelDistance;

	public Drivethreetimes(double distance){
		travelDistance = distance;
	}

	@Override
	protected void initialize() {
		
		System.out.println("Drivethreetimes.initialize");
	}

	@Override
	protected void execute() {
		System.out.println("I drove");
	}
	

}
 