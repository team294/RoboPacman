package pacman.subsystems;

public class AStar {
    private int currDist = 0;

    public AStar(Field field, Coord start, Coord goal) {
        Node startNode = new Node(start, goal, currDist);
    }

    public Coord calculate() {
        return new Coord(0, 0);
    }
}
