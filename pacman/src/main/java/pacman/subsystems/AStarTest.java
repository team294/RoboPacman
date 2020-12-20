package pacman.subsystems;

import pacman.subsystems.astar.*;

public class AStarTest {
    // [
    //     [4, 0, 0, 0, 0, 0, 0, 4, 0, 0],
    //     [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
    //     [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
    //     [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
    //     [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
    //     [0, 0, 2, 0, 0, 0, 0, 0, 0, 0],
    //     [0, 0, 4, 0, 0, 0, 0, 0, 0, 0],
    //     [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
    //     [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
    //     [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
    //     [0, 0, 0, 0, 0, 0, 0, 1, 0, 0]
    // ]
    public static void main(String[] args) {
        Field field = new Field(10, 10, Coord.intArrToCoord(new int[][] {{0, 0}, {7,0}, {2, 6}}));
        Coord start = new Coord(7, 9);
        Coord goal = new Coord(2, 5);
        AStar route = new AStar(field, start, goal);
        System.out.println(Coord.toString(route.calculate().coord));
    }
}
