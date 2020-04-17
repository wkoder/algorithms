import java.util.Arrays;

public class Polygon {

  static double EPS = 1E-9;
  Point[] vertices;
  int N;

  Polygon(Point[] v) {
    vertices = v;
    N = vertices.length;
  }

  Polygon simpleHullPolyline() {
    Point[] D = new Point[2 * N + 1];
    int bot = N - 2, top = bot + 3;
    D[bot] = D[top] = vertices[2];
    // first 3 vertices area a ccw triangle
    if (new Triangle(vertices[0], vertices[1], vertices[2]).isLeft() > 0) {
      D[bot + 1] = vertices[0];
      D[bot + 2] = vertices[1];
    } else {
      D[bot + 1] = vertices[1];
      D[bot + 2] = vertices[0];
    }
    for (int i = 3; i < N; i++) {
      // test if next vertex is inside de deque hull
      if (new Triangle(D[bot], D[bot + 1], vertices[i]).isLeft() > 0
          && new Triangle(D[top - 1], D[top], vertices[i]).isLeft() > 0)
        continue;
      // get the rightmost tangent at the deque bot
      while (new Triangle(D[bot], D[bot + 1], vertices[i]).isLeft() <= 0)
        bot++;
      D[--bot] = vertices[i];
      // get the leftmost tangent at the deque bot
      while (new Triangle(D[top - 1], D[top], vertices[i]).isLeft() <= 0)
        top--;
      D[++top] = vertices[i];
    }
    Point[] v = new Point[top - bot + 1];
    for (int h = 0; h < v.length; h++)
      v[h] = D[bot + h];
    return new Polygon(v);
  }

  // 0 = disjoint, 1 = intersect, 2 = overlap
  int intersect(Polygon p) {
    boolean ok = true;
    for (int i = 0; i < p.N; i++)
      if (isInBounds(p.vertices[i]))
        ok = false;
      else if (isInside(p.vertices[i]))
        return 1;
    Line L, L2;
    int i, j, k;
    for (i = 0; i < p.N; i++) {
      L = new Line(p.vertices[i], p.vertices[(i + 1) % p.N]);
      for (j = 0; j < N; j++) {
        L2 = new Line(vertices[j], vertices[(j + 1) % N]);
        k = L.segmentIntersect2D(L2);
        if (k == 1)
          return 1;
        else if (k == 2)
          ok = false;
      }
    }
    return ok ? 0 : 2;
  }

  // int[0] = tangent point of this polygon, int[1] tangent for the other
  // polygon
  int[] RLTangentConvexPoly(Polygon w) {
    int ix1, ix2;
    // initial vertex on each polygon
    ix1 = rightTangentConvexPoly(w.vertices[0]);
    ix2 = leftTangentConvexPoly(vertices[ix1]);
    // ping-pong linear search
    boolean done = false;
    while (!done) {
      done = true;
      while (new Triangle(w.vertices[ix2], vertices[ix1],
          vertices[(ix1 + 1) % 2]).isLeft() <= 0)
        ix1++; // get RTang
      while (new Triangle(vertices[ix1], w.vertices[ix2],
          w.vertices[ix2 - 1]).isLeft() <= 0) {
        ix2--; // get LTang
        done = false;
      }
    }
    return new int[]{ix1, ix2};
  }

  // int[0] = rightmost tangent, int[1] = leftmost tangent
  int[] tangentConvexPoly(Point p) {
    return new int[]{rightTangentConvexPoly(p), leftTangentConvexPoly(p)};
  }

  int rightTangentConvexPoly(Point p) {
    int a, b, c;
    boolean upA, dnC;
    // rightmost tangent = maximum for the isLeft ordering
    if (p.below(vertices[1], vertices[0])
        && !p.above(vertices[N - 1], vertices[0]))
      return 0;
    for (a = 0, b = N; ; ) {
      c = (a + b) / 2;
      dnC = p.below(vertices[(c + 1) % N], vertices[c % N]);
      if (dnC && !p.above(vertices[c - 1], vertices[c % N]))
        return c;
      upA = p.above(vertices[(a + 1) % N], vertices[a]);
      if (upA)
        if (dnC)
          b = c;
        else if (p.above(vertices[a], vertices[c % N]))
          b = c;
        else
          a = c;
      else if (!dnC)
        a = c;
      else if (p.below(vertices[a], vertices[c % N]))
        b = c;
      else
        a = c;
    }
  }

  int leftTangentConvexPoly(Point p) {
    int a, b, c;
    boolean dnA, dnC;
    // leftmost tangent = minimum for the isLeft ordering
    if (p.above(vertices[N - 1], vertices[0])
        && !p.below(vertices[1], vertices[0]))
      return 0;
    for (a = 0, b = N; ; ) {
      c = (a + b) / 2;
      dnC = p.below(vertices[c - 1], vertices[c % N]);
      if (p.above(vertices[c - 1], vertices[c]) && !dnC)
        return c;
      dnA = p.below(vertices[(a + 1) % N], vertices[a]);
      if (dnA)
        if (!dnC)
          b = c;
        else if (p.below(vertices[a], vertices[c % N]))
          b = c;
        else
          a = c;
      else if (dnC)
        a = c;
      else if (p.above(vertices[a], vertices[c % N]))
        b = c;
      else
        a = c;
    }
  }

  // int[0] = rightmost tangent, int[1] = leftmost tangent
  int[] tangentAnyPoly(Point p) {
    int rtan = 0, ltan = 0;
    double eprev, enext;
    eprev = new Triangle(vertices[0], vertices[1], p).isLeft();
    for (int i = 1; i < N; i++) {
      enext = new Triangle(vertices[i], vertices[(i + 1) % N], p)
          .isLeft();
      if (eprev <= 0 && enext > 0) {
        if (!p.below(vertices[i], vertices[rtan]))
          rtan = i;
      } else if (eprev > 0 && enext <= 0)
        if (!p.above(vertices[i], vertices[ltan]))
          ltan = i;
      eprev = enext;
    }
    return new int[]{rtan, ltan};
  }

  double distance(Line l) {
    Vector u, n;
    int max;
    // get a leftward normal n to l
    n = new Vector(-(l.p1.y - l.p0.y), l.p1.x - l.p0.x, 0);
    // get a normal vector u to l with vert[0] on u-backside
    if (n.dot(vertices[0].minus(l.p0)) <= 0)
      u = n;
    else
      u = n.negative();
    max = max2D(u);
    if (u.dot(vertices[max].minus(l.p0)) > 0)
      return 0;
    return l.distance(vertices[max]);
  }

  // >= 0: index of maximum vertex, -1: error
  int max2D(Vector u) {
    if (N < 10) {
      int max = 0;
      for (int i = 1; i < N; i++)
        if (u.above(vertices[i], vertices[max]))
          max = i;
      return max;
    }
    int a, b, c; // indices for edge chain endpoints
    Vector A, C; // edge vectors of vert[a] and vert[c]
    boolean upA, upC; // test for up direction of A and C
    a = 0;
    b = N;
    A = vertices[1].minus(vertices[0]);
    upA = u.up(A);
    if (!upA && !u.above(vertices[N - 1], vertices[0]))
      return 0;
    while (true) {
      c = (a + b) / 2;
      C = vertices[(c + 1) % N].minus(vertices[c]);
      upC = u.up(C);
      if (!upC && !u.above(vertices[c - 1], vertices[c]))
        return c;
      if (upA)
        if (!upC)
          b = c;
        else if (u.above(vertices[a], vertices[b % N]))
          b = c;
        else {
          a = c;
          A = C;
          upA = upC;
        }
      else if (upC) {
        a = c;
        A = C;
        upA = upC;
      } else if (u.below(vertices[a], vertices[c]))
        b = c;
      else {
        a = c;
        A = C;
        upA = upC;
      }
      if (b <= a + 1) // impossibly small
        return -1;
    }
  }

  boolean intersect(Line s) { // convex
    Line inter; // intersection segment
    double tE = 0, tL = 1, t, n, d;
    Vector dS = s.p1.minus(s.p0);
    Vector e;
    for (int i = 0; i < N; i++) {
      e = vertices[(i + 1) % N].minus(vertices[i]);
      n = e.perp(s.p0.minus(vertices[i]));
      d = -e.perp(dS);
      if (Math.abs(d) < EPS) // s is parallel to this edge
        if (n < 0) // p0 is outside this edge
          return false;
        else
          // s cannot cross this edge, so ignore
          continue;
      t = n / d;
      if (d < 0) { // s is entering across this edge
        if (t > tE) {
          tE = t;
          if (tE > tL) // s enters after leaving polygon
            return false;
        }
      } else if (t < tL) { // s in leaving across this edge
        tL = t;
        if (tL < tE) // s leaves before enter polygon
          return false;
      }
    }
    // tE < tL implies that there is a valid intersection
    // p(tE) where S enters polygon, p(tL) where leaves
    inter = new Line(s.p0.plus(dS.multiply(tE)), s.p0.plus(dS.multiply(tL)));
    return true;
  }

  Polygon chainHull2D() {
    Arrays.sort(vertices, 0, N);
    Point[] H = new Point[N + 1];
    // the output array H[] will be used as the stack
    int bot = 0, top = (-1); // indices for bottom and top of the stack
    int i; // array scan index

    // Get the indices of points with min x-coord and min|max y-coord
    int minmin = 0, minmax;
    double xmin = vertices[0].x;
    for (i = 1; i < N; i++)
      if (vertices[i].x != xmin)
        break;
    minmax = i - 1;
    if (minmax == N - 1) { // degenerate case: all x-coords == xmin
      H[++top] = vertices[minmin];
      if (vertices[minmax].y != vertices[minmin].y) // a nontrivial
        // segment
        H[++top] = vertices[minmax];
      H[++top] = vertices[minmin]; // add polygon endpoint
      Polygon pp = new Polygon(H);
      pp.N = top;
      return pp;
    }

    // Get the indices of points with max x-coord and min|max y-coord
    int maxmin, maxmax = N - 1;
    double xmax = vertices[N - 1].x;
    for (i = N - 2; i >= 0; i--)
      if (vertices[i].x != xmax)
        break;
    maxmin = i + 1;

    // Compute the lower hull on the stack H
    H[++top] = vertices[minmin]; // push minmin point onto stack
    i = minmax;
    while (++i <= maxmin) {
      // the lower line joins P[minmin] with P[maxmin]
      if (new Triangle(vertices[minmin], vertices[maxmin], vertices[i])
          .isLeft() >= 0
          && i < maxmin)
        continue; // ignore P[i] above or on the lower line

      while (top > 0) // there are at least 2 points on the stack
      {
        // test if P[i] is left of the line at the stack top
        if (new Triangle(H[top - 1], H[top], vertices[i]).isLeft() > 0)
          break; // P[i] is a new hull vertex
        else
          top--; // pop top point off stack
      }
      H[++top] = vertices[i]; // push P[i] onto stack
    }

    // Next, compute the upper hull on the stack H above the bottom hull
    if (maxmax != maxmin) // if distinct xmax points
      H[++top] = vertices[maxmax]; // push maxmax point onto stack
    bot = top; // the bottom point of the upper hull stack
    i = maxmin;
    while (--i >= minmax) {
      // the upper line joins P[maxmax] with P[minmax]
      if (new Triangle(vertices[maxmax], vertices[minmax], vertices[i])
          .isLeft() >= 0
          && i > minmax)
        continue; // ignore P[i] below or on the upper line

      while (top > bot) // at least 2 points on the upper stack
      {
        // test if P[i] is left of the line at the stack top
        if (new Triangle(H[top - 1], H[top], vertices[i]).isLeft() > 0)
          break; // P[i] is a new hull vertex
        else
          top--; // pop top point off stack
      }
      H[++top] = vertices[i]; // push P[i] onto stack
    }
    if (minmax != minmin)
      H[++top] = vertices[minmin]; // push joining endpoint onto stack
    Polygon pp = new Polygon(H);
    pp.N = top;
    return pp;
  }

  boolean isSimple() {
    SweepLine.EventQueue eq = new SweepLine.EventQueue(this);
    SweepLine sl = new SweepLine(this);
    SweepLine.Event e;
    SweepLine.SLSegment s;
    // processes all events in the sorted queue
    // events are only left or right vertices since
    // no new events will be added
    while ((e = eq.next()) != null) {
      if (e.type == SweepLine.EventQueue.LEFT) {
        s = sl.add(e);
        if (s.intersect(s.above, N))
          return false;
        if (s.intersect(s.below, N))
          return false;
      } else {
        s = sl.find(e);
        if (s.below != null && s.below.intersect(s.above, N))
          return false;
        sl.remove(s);
      }
    }
    return true;
  }

  boolean isInBounds(Point p) {
    for (int i = 0; i < N; i++)
      if (new Triangle(vertices[i], vertices[(i + 1) % N], p).isLeft() == 0 && new Line(vertices[i], vertices[(i + 1) % N]).inSegment(p))
        return true;
    return false;
  }

  int symmetries() {
    int s = 0, i, j, plus = N / 2;
    Line sym;
    Point p;
    loop:
    for (i = 0; i < N; i++) { // fixed points
      if ((N & 1) != 0)
        sym = new Line(vertices[i], vertices[(i + plus) % N].medium(vertices[(i + plus + 1) % N]));
      else
        sym = new Line(vertices[i], vertices[(i + plus) % N]);
      for (j = 1; j <= plus; j++)
        if (!sym.reflect(vertices[(i - j + N) % N], vertices[(i + j) % N]))
          continue loop;
      s++;
    }
    loop:
    for (i = 0; i < N; i++) { // medium points
      p = vertices[i].medium(vertices[(i + 1) % N]);
      if ((N & 1) == 0)
        sym = new Line(p, vertices[(i + plus) % N].medium(vertices[(i + plus + 1) % N]));
      else
        sym = new Line(p, vertices[(i + plus + 1) % N]);
      for (j = 1; j <= plus; j++)
        if (!sym.reflect(vertices[(i - j + 1 + N) % N], vertices[(i + j) % N]))
          continue loop;
      s++;
    }
    return s / 2;
  }

  boolean isInside(Line s) {
    if ((!isInBounds(s.p0) && !isInside(s.p0)) || (!isInBounds(s.p1) && !isInside(s.p1)))
      return false; // some point is out!
    Line L;
    int k, pk, over = 0;
    pk = new Line(vertices[N - 1], vertices[0]).segmentIntersectNotTouch(s);
    for (int i = 0; i < N; i++) {
      L = new Line(vertices[i], vertices[(i + 1) % N]);
      k = L.segmentIntersectNotTouch(s);
      if (k == 1)
        return false;
      if ((k == 2 && pk != 2) || (k != 2 && pk == 2))
        over++;
    }
    return over <= 2;
  }

  boolean isInside(Point p) { // Winding number
    int wn = 0;
    for (int i = 0; i < N; i++)
      if (vertices[i].y <= p.y) { // upward crossing
        if (vertices[(i + 1) % N].y > p.y
            && new Triangle(vertices[i], vertices[(i + 1) % N], p).isLeft() > 0)
          wn++; // valid up intersect
      } else if (vertices[(i + 1) % N].y <= p.y) { // downward crossing
        if (new Triangle(vertices[i], vertices[(i + 1) % N], p).isLeft() < 0)
          wn--; // valid down intersect
      }
    return wn != 0;
  }

  boolean isInsideCN(Point p) { // Crossing number
    int cn = 0; // the crossing number counter
    double vt;
    // loop through all edges of the polygon
    for (int i = 0; i < N; i++) { // edge from V[i] to V[i+1]
      if (((vertices[i].y <= p.y) && (vertices[(i + 1) % N].y > p.y)) // an upward crossing
          || ((vertices[i].y > p.y) && (vertices[(i + 1) % N].y <= p.y))) { // a downward crossing
        // compute the actual edge-ray intersect x-coordinate
        vt = (p.y - vertices[i].y) / (vertices[(i + 1) % N].y - vertices[i].y);
        if (p.x < vertices[i].x + vt * (vertices[(i + 1) % N].x - vertices[i].x)) // P.x < intersect
          ++cn; // a valid crossing of y=P.y right of P.x
      }
    }
    return (cn & 1) == 1;
  }

  // > 0: counterclockwise, < 0: clockwise, = 0: degenerate
  double orientation2D() {
    // find rightmost lowest vertex
    int rmin = -1;
    double xmin = Double.MAX_VALUE;
    double ymin = Double.MAX_VALUE;
    for (int i = 0; i < vertices.length; i++) {
      if (vertices[i].y > ymin)
        continue;
      if (vertices[i].y == ymin)
        if (vertices[i].x < xmin)
          continue;
      rmin = i;
      ymin = vertices[i].y;
      xmin = vertices[i].x;
    }
    return new Triangle(vertices[(rmin + N - 1) % N], vertices[rmin],
        vertices[(rmin + 1) % N]).isLeft();
  }

  double area2D() {
    double area = 0;
    for (int i = 1, j = 2, k = 0; i <= N; i++, j++, k++)
      area += vertices[i % N].x * (vertices[j % N].y - vertices[k].y);
    return area / 2.0;
  }

  double area3D() {
    double area = 0;
    double an, ax, ay, az;
    int coord; // coordinate to ignore
    Point normal = new Point(0, 0, 0); // unit normal vecitemtor of the
    // polygon's plane

    ax = Math.abs(normal.x);
    ay = Math.abs(normal.y);
    az = Math.abs(normal.z);

    coord = 3; // ignore z-coord
    if (ax > ay) {
      if (ax > az)
        coord = 1; // ignore x-coord
    } else if (ay > az)
      coord = 2; // ignore x-coord
    // area of 2D projectionitem
    for (int i = 1, j = 2, k = 0; i <= N; i++, j++, k++)
      switch (coord) {
        case 1:
          area += vertices[i % N].y * (vertices[j % N].z - vertices[k].z);
          continue;
        case 2:
          area += vertices[i % N].x * (vertices[j % N].z - vertices[k].z);
          continue;
        case 3:
          area += vertices[i % N].x * (vertices[j % N].y - vertices[k].y);
          continue;
      }
    // scale to get area before projection
    an = Math.sqrt(ax * ax + ay * ay + az * az);
    switch (coord) {
      case 1:
        area *= an / (2.0 * ax);
        break;
      case 2:
        area *= an / (2.0 * ay);
        break;
      case 3:
        area *= an / (2.0 * az);
    }
    return area;
  }
}
