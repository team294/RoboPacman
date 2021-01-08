package pacman.subsystems.astar;

import pacman.subsystems.Coord;

public class Field {
  public Coord[] obstacles;
  public int length, height;
  public int minX = 0, minY = 0, maxX, maxY;

  public Field(int length, int height, Coord[] obstacles) {
    this.obstacles = obstacles;
    this.length = length;
    this.height = height;
    this.maxY = height-1;
    this.maxX = length-1;
  }

  // sets minimums and maxium field values
  public void setExtremes(int minX, int minY, int maxX, int maxY) {
    this.minX = minX;
    this.minY = minY;
    this.maxX = maxX;
    this.maxY = maxY;
  }

  public void update(Coord[] obstacles) {
    this.obstacles = obstacles;
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