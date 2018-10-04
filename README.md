# RoboPacman2018

The tank drive can be accessed in the following manner:

	Robot.driveTrain.tankDrive(1,1);


The ghost sensor can be accessed in the following manner:

	Robot.ghostSensor.getPing(); // detects objects directly in front
	Robot.ghostSensor.getRadar(); // detects objects in all directions


The following method can be used to calculate the distance between two points

	Util.getDistance(x1,y1,x2,y2);


Gradle

Run a single unit test with Gradle

	gradle run
    gradle test --tests pacman.test.GhostSensorTest --info

