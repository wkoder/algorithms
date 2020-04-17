import java.util.Arrays;

public class RMQ {

  int N;
  int[] tmax, tmin, data;

  public RMQ(int size) {
    N = 1;
    while ((N <<= 1) < size) ;
    tmax = new int[N + 1];
    tmin = new int[N + 1];
    data = new int[N + 1];
    Arrays.fill(tmax, 0, N + 1, Integer.MIN_VALUE);
    Arrays.fill(tmin, 0, N + 1, Integer.MAX_VALUE);
  }

  void update() {
    for (int i = 1; i <= N; i++) {
      int k = Integer.lowestOneBit(i);
      tmax[i] = Math.max(data[i], getMax(i - k + 1, i - 1));
      tmin[i] = Math.min(data[i], getMin(i - k + 1, i - 1));
    }
  }

  void clear(int size) {
    N = 1;
    while ((N <<= 1) < size) ;
    Arrays.fill(tmax, 0, N + 1, Integer.MIN_VALUE);
    Arrays.fill(tmin, 0, N + 1, Integer.MAX_VALUE);
    Arrays.fill(data, 0, N + 1, Integer.MAX_VALUE);
  }

  int getMax(int l, int r) {
    if (l == r)
      return data[r];
    if (l > r)
      return Integer.MIN_VALUE;
    int i, low, c0, k, max = Integer.MIN_VALUE;
    for (i = r; i >= l; ) {
      low = Integer.lowestOneBit(i);
      c0 = Integer.numberOfTrailingZeros(low);
      k = 1 << c0;
      if (i - k + 1 >= l) {
        max = Math.max(max, tmax[i]);
        i -= k;
      } else {
        max = Math.max(max, data[i]);
        i--;
      }
    }
    return max;
  }

  int getMin(int l, int r) {
    if (l == r)
      return data[r];
    if (l > r)
      return Integer.MAX_VALUE;
    int i, low, c0, k, min = Integer.MAX_VALUE;
    for (i = r; i >= l; ) {
      low = Integer.lowestOneBit(i);
      c0 = Integer.numberOfTrailingZeros(low);
      k = 1 << c0;
      if (i - k + 1 >= l) {
        min = Math.min(min, tmin[i]);
        i -= k;
      } else {
        min = Math.min(min, data[i]);
        i--;
      }
    }
    return min;
  }
}
