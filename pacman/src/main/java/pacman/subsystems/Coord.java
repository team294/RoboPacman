package pacman.subsystems;

import java.lang.Math;

public class Coord {
    public int x, y;
    public static Coord farPoint = new Coord(1000000,1000000); // extreme starting value
    public Coord() {
        this.x = farPoint.x;
        this.y = farPoint.y;
    }
    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Coord(int[] a) {
        this.x = a[0];
        this.y = a[1];
    }
    public void set(int[] a) {
        this.x = a[0];
        this.y = a[1];
    }
    public void set(int x, int y) {
        this.x = x;
        this.y = x;
    }
    public double distance(Coord a) {
        return Math.sqrt(Math.pow(this.x-a.x, 2) + Math.pow(this.y-a.y, 2));
    }
    public Coord closestTo (Coord[] coords) {
        Coord res = farPoint;
        for (Coord x: coords) {
            res = this.distance(x) < this.distance(res) ? x : res;
        }
        return res;
    }
    public Coord closestTo (Coord[] coords, int minDistance) {
        Coord res = farPoint;
        for (Coord x: coords) {
            res = this.distance(x) < this.distance(res) && this.distance(x) < minDistance ? x : res;
        }
        return res;
    }
    public boolean exists() {
        return (this.x == farPoint.x && this.y == farPoint.y) ? false : true; 
    }
    public static String toString(Coord a) {
        return (a.exists()) ? "("+a.x+", "+a.y+")" : "Doesn't exist";
    }
    public static String toString(Coord[] a) {
        String res = "(";
        for (int i = 0; i < a.length; i++) {
            res += Coord.toString(a[i]) + ((i == a.length-1) ? ")" : ", ");
        }
        return res;
    }
    public static Coord[] intArrToCoord(int[][] arr) {
        Coord[] res = new Coord[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = new Coord(arr[i]);
        }
        return res;
    }
}
