package pacman.commands;
import pacman.base.CommandBase;
import pacman.robot.Robot;
import java.util.Arrays;

public class EatAllDots extends CommandBase{
    int dotArray [][];
    int ghostArray [][];
    public EatAllDots(){
        super();
    }
    public int theBestAngle =0 ;

    @Override
    protected void initialize() {
        dotArray = Robot.dotSensor.getDotLocations();

	}
	public void moveFoward (){
        Robot.driveTrain.tankDrive(1,1);
    }
	@Override
    protected void execute() {
        
        // int minDistance = 999;
        // int minIndex = 0;
        // int currentDistance;
        int dotX, dotY, pacmanX, pacmanY;
        
        dotArray = Robot.dotSensor.getDotLocations();
        pacmanX = Robot.driveTrain.getPositionX();
        pacmanY = Robot.driveTrain.getPositionY();

        int lastDot = dotArray.length -1;
        dotX = dotArray[lastDot][0];
        dotY = dotArray [lastDot][1];
        // for(int i = 0; i <dots.length ; i++){
        //     dotX = dots[i][0];
        //     dotY = dots[i][1];
        //     currentDistance = dotDistance(dotX, dotY, pacX, pacY);
        //     if (currentDistance < minDistance) {
        //         minDistance = currentDistance;
        //         minIndex = i;
        //     }
        // }

        turnOrMove(pacmanX, pacmanY, dotX, dotY);
	}
	
    public void turnOrMove (int pacmanX, int pacmanY, int dotX, int dotY){
        int finalAngle;
        int pacmanCurrentAngle = Robot.driveTrain.getAngle();

        finalAngle = destinationAngle(pacmanX, pacmanY, dotX, dotY);
        if(pacmanCurrentAngle == finalAngle ){
            moveFoward();
        } 
        else {
            turn(finalAngle);
        }

    }

    public int distance(int dotX, int dotY, int pacmanX, int pacmanY) {       
        return  Math.abs(pacmanX - dotX) + Math.abs(pacmanY - dotY);
	}
	
    public void turn (int degreesCounted){
        int directionFacing = Robot.driveTrain.getAngle();

        if(directionFacing - degreesCounted == 90){
            Robot.driveTrain.tankDrive(0,1);
        
        } else if(degreesCounted == 270 && directionFacing == 0){
            Robot.driveTrain.tankDrive(0,1);
        }
        else {
            Robot.driveTrain.tankDrive(1,0);
        }

	}
	public boolean hit (int pacmanX, int pacmanY, int destinationAngle){
        boolean is_hit = false;
      
		
        int futurePacmanX = 0;
		int futurePacmanY = 0;
		
		int ghostX;
		int ghostY;
        if(destinationAngle == 270){
            futurePacmanX = pacmanX - 1;
			futurePacmanY = pacmanY;
			
        } else if (destinationAngle == 90) {
            futurePacmanX = pacmanX + 1;
			futurePacmanY = pacmanY;
			
        } else if (destinationAngle == 0) {
			futurePacmanY = pacmanY -1;
			
        } else {
            futurePacmanY = pacmanY +1;
        }
        
        ghostArray = Robot.ghostSensor.getGhostLocations();

        for(int i = 0; i < ghostArray.length; i++){
            ghostX = ghostArray [i][0];
            ghostY = ghostArray [i][1];

            if (futurePacmanX == ghostX && futurePacmanY == ghostY) {
                is_hit = true;
                
                return is_hit;
            }
            //System.out.println("Ghost y " + ghostY + " ghost x " + ghostX);
        }
        return is_hit;
    }

    public int destinationAngle (int pacmanX, int pacmanY, int dotX, int dotY){
        int changeInX = pacmanX - dotX;
        int changeInY = pacmanY - dotY;
        int finalAngle = 0;
    
    	if(changeInX == 0){
          
        	if(changeInY < 0){
               
         	 finalAngle = 180;
        	 } else if(changeInY > 0) {
              
         	 finalAngle = 0;
         }
		 
        } else if (changeInY == 0) {
            
         if(changeInX > 0){
                
             finalAngle = 270;
             if(hit(pacmanX, pacmanY, finalAngle)){
                if(pacmanY == 1){
                    finalAngle = 180;
                   } else if (pacmanY == 8){
                       finalAngle = 0;
                  } else {
                    finalAngle = theBestAngle;
              }

                  

            } 
         } else if (changeInX < 0){
                
            finalAngle = 90;
        }
        }
        else if (changeInX > 0 && changeInY < 0 || changeInX > 0 && changeInY > 0) {

            finalAngle = 270;
                
            if(hit(pacmanX, pacmanY, finalAngle)) {
                if(pacmanY == 1){
                    finalAngle = 180;
                } else if (pacmanY == 8){
                    finalAngle = 0;
                } else {
                    finalAngle = theBestAngle;
                }
            } 
        } 
        theBestAngle = finalAngle;

        return finalAngle;
    }
    @Override
    protected boolean isFinished() {
        return false;
    }
}