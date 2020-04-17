import java.util.Arrays;

public class BIT_2D {

  int maxX, maxY;
  int[][] tree;

  public BIT_2D(int sizeX, int sizeY) {
    maxX = 1;
    while ((maxX <<= 1) < sizeX) ;
    maxY = 1;
    while ((maxY <<= 1) < sizeY) ;
    tree = new int[maxX + 1][maxY + 1];
  }

  void update(int x, int y, int val) {
    int y1;
    while (x <= maxX) {
      y1 = y;
      while (y1 <= maxY) {
        tree[x][y1] += val;
        y1 += (y1 & -y1);
      }
      x += (x & -x);
    }
  }

  void clear() {
    for (int i = 0; i < maxX; i++)
      Arrays.fill(tree[i], 0);
  }
}
