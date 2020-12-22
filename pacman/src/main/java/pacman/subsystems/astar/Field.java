package pacman.subsystems.astar;

import pacman.subsystems.Coord;

public class Field {
  public Coord[] obstacles;
  public int length, height;
  public int minX = 0, minY = 0, maxX, maxY;
  // public Node[][] points;

  public Field(int length, int height, Coord[] obstacles) {
    this.obstacles = obstacles;
    this.length = length;
    this.height = height;
    // this.points = new Node[height][length];
    // setPoints();
    this.maxY = height;
    this.maxX = length;
  }

  public void update(Coord[] obstacles) {
    this.obstacles = obstacles;
  }

  // private void setPoints() {
  //   for (int row = 0; row < height; i++) {
  //     for (int col = 0; col < length; i++) {
  //       points[row][col] = new Node(col, row);
  //     }
  //   }
  // }

  // sets minimums and maxium field values
  public void setExtremes(int minX, int minY, int maxX, int maxY) {
    this.minX = minX;
    this.minY = minY;
    this.maxX = maxX;
    this.maxY = maxY;
  }
  
  public static Field ArrayToField(int[][] arr) {
    int i = 0;
    for (int[] row: arr) {
      for (int point: row) {
        if (point == 1) {
          i++;
        }
      }
    }
    Coord[] barriers = new Coord[i];
    i = 0;
    for (int j = 0; j < arr.length; j++) {
      for (int k = 0; k < arr[j].length; k++) {
        if (arr[j][k] == 1) {
          barriers[i] = new Coord(k, j);
          i++;
        }
      }
    }
    return new Field(arr[0].length, arr.length, barriers);
  }
}