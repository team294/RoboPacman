package pacman.base;


public class CommandBase {
	
	protected void initialize() {
		System.out.println("CommandBase:initialize");
	}
	
	protected void execute() {
		System.out.println("CommandBase:execute");
	}
	
	protected boolean isFinished() {
		System.out.println("CommandBase:isFinished");
		return false;
	}
	
}
