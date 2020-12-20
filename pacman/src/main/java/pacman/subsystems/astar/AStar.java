package pacman.subsystems.astar;

import pacman.subsystems.Coord;

public class AStar {
    private Coord goal;
    private boolean finished = false;
    private Node[][] points;
    private Field field;

    // make array with Nodes for every position in field
    // give Node constructor w/out f cost
    //    give func to set f cost
    // last 5 lines still needed https://www.youtube.com/watch?v=-L-WgKMFuhE&t=506s


    public AStar(Field field, Coord start, Coord goal) {
        this.field = field;
        Node.setField(this.field);
        this.goal = goal;
        points = field.coords;
        System.out.println("Start: " + start.x + ", " + start.y);
        System.out.println("Goal: " + goal.x + ", " + goal.y);
        points[start.y][start.x].setF(goal, 0);
        points[start.y][start.x].open();
        points[start.y][start.x].setLength(0);
        System.out.println("Start Opened? " + points[start.y][start.x].OPENED);
        System.out.println("Start F: " + points[start.y][start.x].f);
    }

    private Coord[] findNeighbors(Node m) {
        return new Coord[] {new Coord(m.coord.x-1, m.coord.y), new Coord(m.coord.x+1, m.coord.y), new Coord(m.coord.x, m.coord.y-1), new Coord(m.coord.x, m.coord.y+1)};
    }

    public Node calculate() {
        Node current = new Node();
        while(!finished) {
            Node lowest = new Node();
            for (Node[] row: points) {
                for (Node point: row) {
                    if (point.OPENED) {
                        // System.out.println("Point f: " + point.f);
                        // System.out.println("Current f: " + current.f);
                        if (point.f < lowest.f) {
                            current = point;
                        }
                        // System.out.println("Updated f: " + current.f);
                    }
                }   
            }
            for (Node[] row: points) {
                for (Node point: row) {
                    if (point == current) {
                        System.out.println("Closing point: " + Coord.toString(current.coord));
                        point.close();
                    }
                }
            }
            if (current.coord == goal) {
                finished = true;
                break;
            }

            for (Coord x: findNeighbors(current)) {
                Node xNode = new Node(x);
                xNode.setF(goal, current.pathLength+1);
                if (xNode.traversable) {
                    if (!xNode.CLOSED) {
                        xNode.setLength(current.pathLength+1);
                        xNode.setParent(current);
                        xNode.open();
                        points[x.y][x.x] = xNode;
                    }
                }
            }
            String str = "";
            for (Node[] row: points) {
                for (Node point: row) {
                    if (point == current) {
                        str += "n";
                    } else if (point.CLOSED) {
                        str += "c";
                    } else if (point.OPENED) {
                        str += "o";
                    } else {
                        str += "x";
                    }
                    str += " ";
                }   
                str += "\n";
            }
            System.out.println(str);
        }
        return current;
    }
}
