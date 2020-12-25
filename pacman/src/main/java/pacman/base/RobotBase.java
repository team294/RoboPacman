package pacman.base;

public class RobotBase {
	
	public static String VERSION = "2020.1";

	// subsystems
	public static DriveTrain driveTrain = new DriveTrain();
	public static GhostSensor ghostSensor = new GhostSensor();
	public static DotSensor dotSensor = new DotSensor();

	public void robotInit() {
	}


	
}
