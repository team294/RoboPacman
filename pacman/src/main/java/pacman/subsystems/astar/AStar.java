package pacman.subsystems.astar;

import pacman.subsystems.Coord;

public class AStar {
    private int currDist = 0;
    private Coord goal;
    private boolean finished = false;
    private Node[] points;
    private int steps;
    private Coord[] path;
    private Field field;


    public AStar(Field field, Coord start, Coord goal) {
        this.field = field;
        this.goal = goal;
        this.path = new Coord[field.length + field.height];
        this.steps = 0;
        points = new Node[field.length * field.height];
        points[steps] = new Node(start, goal, currDist, field);
    }

    private int findCoordArrLength(Coord[] arr) {
        int length = 0;
        for (Coord x: arr) {
            if (x.exists()) {
                length++;
            }
        }
        return length;
    }

    private Coord[] findNeighbors(Node m) {
        return new Coord[] {new Coord(m.x-1, m.y),new Coord(m.x+1, m.y),new Coord(m.x, m.y-1),new Coord(m.x, m.y+1)};
    }

    public Coord[] calculate() {
        Node parent;
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

            for (Coord x: findNeighbors(current)) {
                Node xNode = new Node(x, goal, currDist, field);
                if (xNode.traversable || !xNode.CLOSED) {
                    for (Coord neighboors: findNeighbors(xNode) {
                        if (true) {
                            points[steps] = xNode;
                            steps++; // Try later: put steps++ into points[steps], so it says points[steps++]
                        }
                    }
                }
            }

        }
        return path;
    }
}
