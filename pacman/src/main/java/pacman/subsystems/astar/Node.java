package pacman.subsystems.astar;

import pacman.subsystems.Coord;

public class Node {
    public int f;
    Coord coord;

    public Node(Coord point, Coord goalPos, int h) {
        this.coord = point;
        this.f = (int) point.distance(goalPos)*10 + h;
    }
}