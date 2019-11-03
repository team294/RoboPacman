package pacman.print;

import java.util.ArrayList;
import java.util.List;

public class StatusPrinter {
	private static List<Printable> printObjs = new ArrayList<Printable>();

	public static void addPrintable(Printable p) {
		printObjs.add(p);
	}

	public static void print() {
		System.out.println("Status --------------------------");

		for (Printable p:printObjs) {
			System.out.println("Status: " + p.printStatus());
		}

		System.out.println("Status --------------------------");
	}
}
