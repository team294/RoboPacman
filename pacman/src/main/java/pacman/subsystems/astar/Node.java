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
        // checkIfTraversable(field.obstacles);
    }
    public Node(Coord point, Coord goalPos, int h) {
        this.coord = point;
        this.f = (int) point.distance(goalPos)*10 + h;
        this.traversable = true;
        // checkIfTraversable(field.obstacles);
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
        this.pathLength = h;
        this.f = (int) this.coord.distance(goalPos)*10 + h*10;
        this.traversable = true;
        checkIfTraversable(field.obstacles);
    }
    private void checkIfTraversable(Coord[] obstacles) {
        if (obstacles != null) {
            for (Coord obstacle: obstacles) {
                if (obstacle == this.coord) {
                    this.traversable = false;
                } 
            }
            if (this.coord.x < Node.field.minX || this.coord.x >= Node.field.maxX || this.coord.y < Node.field.minY || this.coord.y >= Node.field.maxY) {
                this.traversable = false;
            }
        }
    }
    public static void setField(Field field) {
        Node.field = field;
    }
}