package pacman.subsystems.astar;

import pacman.subsystems.Coord;

public class Node extends Coord {
    private static Field field;
    public int f, pathLength;
    public boolean OPENED = false, CLOSED = false, traversable;
    public Coord coord;
    public Node parent;

    public Node() {
        this.coord = Coord.farPoint;
        this.f = Coord.farPoint.x;
        this.traversable = false;
    }
    public Node(Coord point) {
        this.coord = point;
    }
    public Node(Coord point, Coord goalPos, int h) {
        this.coord = point;
        this.f = (int) point.distance(goalPos)*10 + h;
        this.traversable = true;
        for (Coord obstacle: field.obstacles) {
            if (obstacle == point) {
                traversable = false;
            } 
        }
        if (point.x < Node.field.minX || point.x >= Node.field.maxX || point.y < Node.field.minY || point.y >= Node.field.maxY) {
            traversable = false;
        }
    }
    public void setLength(int length) {
        this.pathLength = length;
    }
    public void setParent(Node parent) {
        this.parent = parent;
    }
    public void open() {
        this.OPENED = true;
        this.CLOSED = false;
    }
    public void close() {
        this.CLOSED = true;
        this.OPENED = false;
    }
    public void setF(Coord goalPos, int h) {
        this.f = (int) this.coord.distance(goalPos)*10 + h;
        this.traversable = true;
        for (Coord obstacle: field.obstacles) {
            if (obstacle == this.coord) {
                traversable = false;
            } 
        }
        if (this.coord.x < Node.field.minX || this.coord.x >= Node.field.maxX || this.coord.y < Node.field.minY || this.coord.y >= Node.field.maxY) {
            traversable = false;
        }
    }
    // public void print(String prefix) {
    //     System.out.println(prefix + ": " + this.f + );
    // }
    public static void setField(Field field) {
        Node.field = field;
    }
}