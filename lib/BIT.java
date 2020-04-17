import java.util.Arrays;

public class BIT {

  int N;
  int[] tree;

  public BIT(int size) {
    N = 1;
    while (N < size)
      N <<= 1;
    tree = new int[N + 1];
  }

  void update(int idx, int val) {
    while (idx <= N) {
      tree[idx] += val;
      idx += (idx & -idx);
    }
  }

  int getData(int idx) {
    int sum = tree[idx];
    if (idx > 0) {
      int z = idx - (idx & -idx);
      idx--;
      while (idx != z) {
        sum -= tree[idx];
        idx -= (idx & -idx);
      }
    }
    return sum;
  }

  void scale(int c) {
    for (int i = 1; i <= N; i++)
      tree[i] = tree[i] / c;
  }

  int find(int cumFre) {
    int idx = 0, bitMask = N, tIdx;
    while ((bitMask != 0) && (idx < N)) {
      tIdx = idx + bitMask;
      if (cumFre == tree[tIdx])
        return tIdx;
      else if (cumFre > tree[tIdx]) {
        idx = tIdx;
        cumFre -= tree[tIdx];
      }
      bitMask >>= 1;
    }
    if (cumFre != 0)
      return -1;
    else
      return idx;
  }

  int findGreatest(int cumFre) {
    int idx = 0, bitMask = N, tIdx;
    while ((bitMask != 0) && (idx < N)) {
      tIdx = idx + bitMask;
      if (cumFre >= tree[tIdx]) {
        idx = tIdx;
        cumFre -= tree[tIdx];
      }
      bitMask >>= 1;
    }
    if (cumFre != 0)
      return -1;
    else
      return idx;
  }

  int get(int idx) {
    int sum = 0;
    while (idx > 0) {
      sum += tree[idx];
      idx -= (idx & -idx);
    }
    return sum;
  }

  void clear() {
    Arrays.fill(tree, 0);
  }
}
