package pacman.base;

public class RobotBase {

	// subsystems
	public static DriveTrain driveTrain = new DriveTrain();
	public static GhostSensor ghostSensor = new GhostSensor();

	public void robotInit() {
	}
	
	public String getVersion() {
		return "1004.1";
	}
}
