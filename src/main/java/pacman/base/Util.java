package pacman.base;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

	public static void log(String s) {
		System.out.println(s);
	}
	
	public static void logt(String s) {
		String date = new SimpleDateFormat("HH:mm:ss:S").format(new Date())+"   ";
		System.out.println(date.substring(0, 13)+" "+s);
	}

	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			System.out.println("Sleep interrupted");
		}
	}
	
    public static int getDistance(int x1, int y1, int x2, int y2) {
    	double distance = Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
    	System.out.printf("getDistance %s,%s %s,%s dist:%s %n",x1,y1,x2,y2,distance);
    	return (int) distance;
    }	
}
