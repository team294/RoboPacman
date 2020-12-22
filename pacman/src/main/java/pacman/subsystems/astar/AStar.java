package pacman.subsystems.astar;

import pacman.subsystems.Coord;

import java.util.ArrayList;

public class AStar {
  private Field field;
  private Coord goal;
  private ArrayList<Node> OPENED = new ArrayList<Node>(), CLOSED = new ArrayList<Node>();

  public AStar(Field field, Coord start, Coord goal) {
    this.goal = goal;
    this.field = field;
    this.OPENED.add(new Node(start, goal, 0));

    // System.out.println("AStar initialized");
  }

  private Coord[] findNeighboors(Node center) {
    return Coord.intArrToCoord(new int[][] {{center.x-1, center.y}, {center.x+1, center.y}, {center.x, center.y+1}, {center.x, center.y-1}});
  }

  public Node calculate() {
    boolean finished = false;
    Node current = new Node();
    // System.out.println("AStar loop started");
    while (!finished) {
      // System.out.println("AStar looping...");
      Node lowest = new Node();
      for (Node point: OPENED) {
        if (point.f < lowest.f) {
          lowest = point;
        }
      }
      current = lowest;
      OPENED.remove(current);

      // System.out.println(Coord.toString(current));

      if (current.x == this.goal.x && current.y == this.goal.y) {
        finished = true;
        break;
      }

      for (Coord neighboor: findNeighboors(current)) {
        Node nNode = new Node(neighboor, goal, current.pathLength+1);
        if (!nNode.traversable(field) || CLOSED.contains(nNode)) {
          continue;
        }

        if (!OPENED.contains(nNode) || nNode.pathLength < nNode.alreadyIn(OPENED).pathLength) {
          nNode.setParent(current);
          if (!OPENED.contains(nNode)) {
            OPENED.add(nNode);
          }
        }
      }
  }
    return current;
  }
}