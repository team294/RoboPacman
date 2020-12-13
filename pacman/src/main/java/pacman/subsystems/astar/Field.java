package pacman.subsystems.astar;

import pacman.subsystems.Coord;

public class Field {
    public Coord[] obstacles;
    public int length, height;

    public Field(int length, int height, Coord[] obstacles) {
        this.length = length;
        this.height = height;
        this.obstacles = obstacles;
    }

    public void update(Coord[] obstacles) {
        this.obstacles = obstacles;
    }
}
