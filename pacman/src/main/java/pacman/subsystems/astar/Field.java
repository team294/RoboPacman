package pacman.subsystems.astar;

import pacman.subsystems.Coord;

public class Field {
    public Coord[] obstacles;
    public int length, height, minX, maxX, minY, maxY;

    public Field(int length, int height, Coord[] obstacles) {
        this.length = length;
        this.height = height;
        this.obstacles = obstacles;
        this.minX = 0;
        this.minY = 0;
        this.maxX = length;
        this.maxY = height;
    }

    public void update(Coord[] obstacles) {
        this.obstacles = obstacles;
    }

    public void setMins(int minX, maxX, minY, maxY) {
        
    }
}
