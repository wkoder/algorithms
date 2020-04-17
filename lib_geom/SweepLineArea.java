import java.util.Arrays;

public class SweepLineArea {

  static final int MAXN = 10000;
  static int N;
  static boolean[] set = new boolean[MAXN];
  static int[] x1 = new int[MAXN];
  static int[] y1 = new int[MAXN];
  static int[] x2 = new int[MAXN];
  static int[] y2 = new int[MAXN];
  static Event[] sweepV = new Event[MAXN * 2];
  static Event[] sweepH = new Event[MAXN * 2];

  static int getArea() {
    int i, j, k, w, area, lastpos;
    Event v, h;
    for (i = 0, j = 0; i < N; i++, j += 2) {
      sweepV[j] = new Event(x1[i], 0, i);
      sweepV[j + 1] = new Event(x2[i], 1, i);
      sweepH[j] = new Event(y1[i], 0, i);
      sweepH[j + 1] = new Event(y2[i], 1, i);
    }
    Arrays.sort(sweepV, 0, N * 2);
    Arrays.sort(sweepH, 0, N * 2);
    area = 0;
    for (i = 0; i < N * 2; i++) {
      v = sweepV[i]; // event for vertical sweep line
      if (i > 0 && sweepV[i].pos > sweepV[i - 1].pos) { // look for overlapped area
        w = v.pos - sweepV[i - 1].pos; // width of overlapped area
        k = 0; // number of overlapped rectangles
        lastpos = 0;
        for (j = 0; j < N * 2; j++) {
          h = sweepH[j];  // event for horizontal sweep line
          if (set[h.rect]) { // intersects sweep line
            if (k > 0)
              area += w * (h.pos - lastpos);
            lastpos = h.pos;
            if (h.type == 0)
              k++;
            else
              k--;
          }
        }
      }
      set[v.rect] = v.type == 0;
    }
    return area;
  }
}

class Event implements Comparable<Event> {

  int pos, rect, type;

  public Event(int p, int t, int re) {
    pos = p;
    type = t; // 0: start, 1: end
    rect = re;
  }

  public int compareTo(Event e) {
    if (pos != e.pos)
      return pos - e.pos;
    if (type != e.type)
      return e.type - type;
    return rect - e.rect;
  }
}
