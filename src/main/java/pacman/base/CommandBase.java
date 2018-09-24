package pacman.base;


public class CommandBase {
	
	protected void initialize() {
		//Util.log("CommandBase:initialize "+this.getClass().getName());
	}
	
	protected void execute() {
		//Util.log("CommandBase:execute "+this.getClass().getName());
	}
	
	protected boolean isFinished() {
		//Util.log("CommandBase:isFinished "+this.getClass().getName());
		return false;
	}
	
	public void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
