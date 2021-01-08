package pacman.subsystems.astar;

// 1 == obstacle, 2 == start, 3 == goal

import pacman.subsystems.Coord;

public class ArrayToAStar {
    public AStar route;
    public ArrayToAStar(int[][] arr) {
        Coord start = new Coord();
        Coord goal = new Coord();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 2) {
                    start = new Coord(j, i);
                } else if (arr[i][j] == 3) {
                    goal = new Coord(j, i);
                }
            }
        }
            
        Field map = Field.ArrayToField(arr);
        route = new AStar(map, start, goal);
    }
}
