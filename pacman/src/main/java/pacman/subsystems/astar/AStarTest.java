package pacman.subsystems.astar;

import pacman.subsystems.Coord;

public class AStarTest {
  public static void main(String[] args) {
    
    //[  0, 0, 0, 1, 0
    //   0, 0, 0, 0, 0
    //   0, 0, 0, 0, 0
    //   0, 3, 1, 0, 0
    //   0, 1, 0, 0, 2

    Coord start = new Coord(4, 4);
    Coord goal = new Coord(1, 3);
    Field map = new Field(5, 5, Coord.intArrToCoord(new int[][] {{1, 4}, {2, 3}, {3, 0}}));
    
    AStar route = new AStar(map, start, goal);
    Node res = route.calculate();
    
    // int[][] arr = {
    //     {0, 0, 0, 1, 0},
    //     {0, 0, 0, 0, 0},
    //     {0, 0, 0, 0, 0},
    //     {0, 3, 1, 0, 0},
    //     {0, 1, 0, 0, 2}
    // };

    // AStar route = new ArrayToAStar(arr).route;
    // Node res = route.calculate();

    System.out.println("Res: " + res.pathLength);
    System.out.println(Coord.toString(res));
    Node last = res;
    for (int i = 0; i < res.pathLength; i++) {
        System.out.println(Coord.toString(last.parent));
        last = last.parent;
    }
  }
}