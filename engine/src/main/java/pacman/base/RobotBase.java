package pacman.base;

public class RobotBase {
	
	public static String VERSION = "2019.3";

	// subsystems
	public static DriveTrain driveTrain = new DriveTrain();
	public static GhostSensor ghostSensor = new GhostSensor();
	public static DotSensor dotSensor = new DotSensor();

	public void robotInit() {
	}
	
}
