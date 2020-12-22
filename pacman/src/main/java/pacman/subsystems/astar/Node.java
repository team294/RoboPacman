package pacman.subsystems.astar;

import pacman.subsystems.Coord;

import java.util.ArrayList;

public class Node extends Coord {
  // public static Field field = new Field(1, 1, new Coord[] {});
  public int f, pathLength;
  public Node parent;

  public Node() {
    this.x = Coord.farPoint.x;
    this.y = Coord.farPoint.y;
    this.f = Coord.farPoint.x;
  }
  public Node(Coord point) {
    this.x = point.x;
    this.y = point.y;
  }
  public Node(Coord point, Coord goal, int h) {
    this.x = point.x;
    this.y = point.y;
    this.setF(goal, h);
  }

  public void setF(Coord goal, int h) {
    this.f = (int) this.distance(goal) + h;
    this.pathLength = h;
  }

  public boolean traversable(Field field) {
    if (this.x < field.minX || this.y < field.minY || this.x > field.maxX || this.y > field.maxY) {
      return false;
    }
    for (Coord obstacle: field.obstacles) {
      if (obstacle.x == this.x && obstacle.y == this.y) {
        return false;
      }
    }
    return true;
  }

  public void setParent(Node parent) {
    this.parent = parent;
  }

  public Node alreadyIn(ArrayList<Node> arr) {
    for (Node point: arr) {
      if (point.x == this.x && point.y == this.y) {
        return point;
      }
      // return (point.x == this.x && point.y == this.y)
    }
    return this;
  }

  // public static setField(Field field) {
  //   Node.field = field;
  // }
}