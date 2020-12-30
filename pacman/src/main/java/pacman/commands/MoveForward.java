package pacman.commands;



import pacman.base.CommandBase;
import pacman.robot.Robot;
public class MoveForward extends CommandBase{
	
	

	public MoveForward (int steps){
		super();
		count = steps;
		System.out.println("Steps" + steps);



	}
	int stepsMoved = 0;
	int count = 0;

	@Override
	protected void initialize() {
		System.out.println("SpinForever.initialize");
	}

	@Override
	protected void execute() {
		// System.out.println("I'm in the execute. steps moved =" + stepsMoved);
		// int ping = Robot.ghostSensor.getDirectionalRadar();
		// if (ping > 0){
		// 	Robot.driveTrain.tankDrive(-1, -1);
		// 	stepsMoved -= 1;
		// } else {
			
		// 	System.out.println(Robot.dotSensor.getDotLocations());
		// //	if(dots[1][3])
		// 	Robot.driveTrain.tankDrive(1, 1);
		// stepsMoved += 1;
		// }
		System.out.println("HELLOO");

		int [][] dotLocation = Robot.dotSensor.getDotLocations();
		int pacmanX = Robot.driveTrain.getPositionX();
		int pacmanY = Robot.driveTrain.getPositionY();
		for(int i=0; i < dotLocation.length; i++) {
			System.out.println("HELLOO");
			int dotX = dotLocation[i][0];
			int dotY = dotLocation[i][1];
			System.out.println("x = " + dotX + "and y = " + dotY);
			if (pacmanX == dotX) {
				if (pacmanY > dotY) {
					Robot.driveTrain.tankDrive(1, 1);
					stepsMoved += 1;
					System.out.println("ASDFLAKSDJFKLASDFJLASDF");
		
				}
				if (pacmanY < dotY) {
					Robot.driveTrain.tankDrive(-1, -1);
					stepsMoved += -1;
					System.out.println("wasdjfaksldjfa");
		
				}
			}
		}
	}
	
	@Override
	protected boolean isFinished() {
		if(stepsMoved >= count ) {
			return true;
		}
		else {
			return false;
		}
	
	}
	private void turnAround() {
		stepsMoved = stepsMoved * (-1);
		System.out.println("ASDFKASDFJAKSDF");

	}
}


