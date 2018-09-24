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
}
