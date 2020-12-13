package pacman.subsystems.astar;

import pacman.subsystems.Coord;

public class AStar {
    private int currDist = 0;
    private Coord goal;
    private boolean finished = false;
    private Node[] points;
    private Coord[] path;
    private Field field;


    public AStar(Field field, Coord start, Coord goal) {
        this.field = field;
        this.goal = goal;
        points = new Node[field.length * field.height];
        points[0] = new Node(start, goal, currDist, field);
    }

    private Coord[] findNeighbors(Node m) {
        return new Coord[] {new Coord(m.x-1, m.y),new Coord(m.x+1, m.y),new Coord(m.x, m.y-1),new Coord(m.x, m.y+1)};
    }

    public Coord[] calculate() {
        while(!finished) {
            Node current = new Node();
            for (Node point: points) {
                if (point.OPENED) {
                    current = point.f < current.f ? point : current;
                }
            }
            for (Node point: points) {
                if (point == current) {
                    point.OPENED = false;
                    point.CLOSED = true;
                }
            }
            if (current.coord == goal) {
                finished = true;
                break;
            }

            for (findNeighbors())

        }
        return path;
    }
}
