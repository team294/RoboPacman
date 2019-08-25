package pacman.test;

import pacman.base.CommandGroupBase;

public class TestGroup extends CommandGroupBase{
	
	public TestGroup() {
		addSequential(new TestCommand());  
	} 
	
}
