package pacman.subsystems.astar;

import java.util.ArrayList;
import java.util.Comparator;

import pacman.subsystems.Coord;

public class AStar {
  private Field field;
  private Coord goal;
  private Coord start;
  private ArrayList<Node> OPENED = new ArrayList<Node>(), CLOSED = new ArrayList<Node>();
  // public long time;

  public AStar(Field field, Coord start, Coord goal) {
    this.goal = goal;
    this.field = field;
    this.start = start;
    if (!this.start.traversable(field)) {
      this.start.set((start.x < field.minX) ? field.minX : field.maxX , (start.y < field.minY) ? field.minY : field.maxY);
      System.out.println(Coord.toString(this.start));
    }
    this.OPENED.add(new Node(this.start, goal, 0));
    // time = System.currentTimeMillis();
  }

  private Coord[] findNeighbors(Node center) {
    return Coord.intArrToCoord(new int[][] {{center.x-1, center.y}, {center.x+1, center.y}, {center.x, center.y+1}, {center.x, center.y-1}});
  }

  public Node calculate() {
    boolean finished = false;
    Node current = new Node();
    while (!finished) {
      Node last = current;
      current = OPENED
        .stream()
        .sorted(Comparator.comparing(x -> x.f))
        .filter(x -> !last.equals(x)) 
        .findFirst()
        .orElse(current);
      OPENED.remove(current);
      CLOSED.add(current);
      // System.out.println(Coord.toString(current));

      if (current.equals(goal)) {
        finished = true;
        // System.out.println((double) (System.currentTimeMillis() - time)/1000);
        break;
      }

      // Coord[] neighbors = findNeighbors(current);
      // Arrays.asList(neighbors)
      //   .stream()
      //   .map(x -> new Node(x, goal, current.pathLength+1))
      //   .filter(x -> x.traversable(field))
      //   .filter(x -> x.inside(CLOSED))
      //   .filter(x -> !x.inside(OPENED) || x.pathLength < nNode.alreadyIn(OPENED).pathLength)
      //   .forEach(x -> x.setParent(current)
      //   .filter(x -> !x.inside(OPENED))
      //   .forEach(OPENED::add);

      for (Coord neighbor: findNeighbors(current)) {
        Node nNode = new Node(neighbor, goal, current.pathLength+1);
        if (!nNode.traversable(field) || nNode.inside(CLOSED)) {
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