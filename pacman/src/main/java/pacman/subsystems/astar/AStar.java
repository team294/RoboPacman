package pacman.subsystems.astar;

import pacman.subsystems.Coord;

public class AStar {
    private int currDist = 0;
    private Coord goal;
    private boolean finished = false;
    private Node[] OPEN;
    private Node[] CLOSED;
    private Node[] path;


    public AStar(Field field, Coord start, Coord goal) {
        // this.start = start;
        this.goal = goal;
        OPEN = new Node[field.length * field.height];
        CLOSED = new Node[field.length * field.height];
        OPEN[0] = new Node(start, goal, currDist);
    }

    public Coord calculate() {
        while(!finished) {
            Node current = OPEN[0];
            for (Node point: OPEN) {
                current = current.f < point.f ? current : point;
            }
            if (current.coord == goal) {
                
            }
        }
        return new Coord(0, 0);
    }
}
