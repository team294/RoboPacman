package pacman.subsystems.astar;

import pacman.subsystems.Coord;

public class Node extends Coord {
    public int f;
    public boolean OPENED;
    public boolean CLOSED;
    public boolean traversable;
    public Coord coord;

    public Node() {
        this.coord = Coord.farPoint;
        this.OPENED = false;
        this.CLOSED = false;
        this.f = Coord.farPoint.x;
        this.traversable = false;
    }
    public Node(Coord point, Coord goalPos, int h, Field field) {
        this.coord = point;
        this.OPENED = false;
        this.CLOSED = false;
        this.f = (int) point.distance(goalPos)*10 + h;
        this.traversable = true;
        for (Coord obstacle: field.obstacles) {
            if (obstacle == point) {
                traversable = false;
            } 
        }
        if (point.x < field.minX || point.x > field.maxX || point.y < field.minY || point.y > field.maxY) {
            traversable = false;
        }
    }
}