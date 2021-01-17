/*
*   Copyright (c) 2021 Galimi
*   All rights reserved.
*/
package pacman.subsystems.astar;

// TODO maybe add system to add cost for things that require turning

import pacman.subsystems.Coord;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class AStar {
  private Field field;
  private Coord goal;
  private Coord start;
  private ArrayList<Node> OPENED = new ArrayList<Node>(), CLOSED = new ArrayList<Node>();
  public long time;

  public AStar(Field field, Coord start, Coord goal) {
    this.goal = goal;
    this.field = field;
    this.start = start;
    if (!this.start.traversable(field)) {
      this.start.set((start.x < field.minX) ? field.minX : field.maxX , (start.y < field.minY) ? field.minY : field.maxY);
    }
    this.OPENED.add(new Node(this.start, goal, 0));
    time = System.currentTimeMillis();
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
      // System.out.println(Coord.toString(current)); // prints current Node for debugging

      if (current.equals(goal)) {
        finished = true;
        System.out.println((double) (System.currentTimeMillis() - time)/1000);
        break;
      }

      Coord[] neighbors = current.findNeighbors();
      int pathLength = current.pathLength+1;
      Node now = current;
      Arrays.asList(neighbors)
        .stream()
        .map(x -> new Node(x, goal, pathLength))
        .filter(x -> x.traversable(field) && !x.inside(CLOSED) && (!x.inside(OPENED) || x.pathLength < x.alreadyIn(OPENED).pathLength))
        .map(x -> x.setParent(now))
        .filter(x -> !x.inside(OPENED))
        .forEach(OPENED::add);


      // .forEach(x -> System.out.println(Coord.toString(x)));

      // for (Coord neighbor: current.findNeighbors()) {
      //   Node nNode = new Node(neighbor, goal, current.pathLength+1);
      //   if (!nNode.traversable(field) || nNode.inside(CLOSED)) {
      //     continue;
      //   }

      //   if (!OPENED.contains(nNode) || nNode.pathLength < nNode.alreadyIn(OPENED).pathLength) {
      //     nNode.setParent(current);
      //     if (!OPENED.contains(nNode)) {
      //       OPENED.add(nNode);
      //     }
      //   }
      // }
  }
    return current;
  }
}