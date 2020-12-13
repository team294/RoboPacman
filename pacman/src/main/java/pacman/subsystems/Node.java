package pacman.subsystems;

public class Node {
    public int f;
    public boolean OPENED = false;
    public Node(Coord point, Coord goalPos, int h) {
        this.f = (int) point.distance(goalPos)*10 + h;
    }
}
