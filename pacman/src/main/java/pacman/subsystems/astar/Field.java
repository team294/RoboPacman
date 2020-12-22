package pacman.subsystems.astar;

import pacman.subsystems.Coord;
// import pacman.subsystems.astar.Node;

public class Field {
    public Coord[] obstacles;
    public Node[][] coords;
    public int length, height, minX, maxX, minY, maxY;

    public Field(int length, int height, Coord[] obstacles) {
        this.length = length;
        this.height = height;
        this.obstacles = obstacles;
        this.coords = new Node[height][length];
        this.setCoords();
        this.minX = 0;
        this.minY = 0;
        this.maxX = length;
        this.maxY = height;
    }

    public void setCoords() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < length; col++) {
                coords[row][col] = new Node(new Coord(col, row));
            }
        }
    }

    public void update(Coord[] obstacles) {
        this.obstacles = obstacles;
    }

    public void setExtremes(int minX, int maxX, int minY, int maxY) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }
}
