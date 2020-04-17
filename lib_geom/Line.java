public class Line {

  double EPS = 1E-9;
  Point p0, p1;
  double a, b, c;

  Line(Point q, Point p) {
    this((int) (q.y - p.y), (int) (p.x - q.x), (int) (p.x * q.y - p.y * q.x));
    p0 = q;
    p1 = p;
  }

  Line(int A, int B, int C) {
    if (A < 0 || A == 0 && B < 0) {
      A = -A;
      B = -B;
      C = -C;
    }
    int d = A != 0 ? gcd(gcd(Math.abs(A), Math.abs(B)), C) :
        (B != 0 || C != 0 ? gcd(Math.abs(B), C) : 1);
    a = A / d;
    b = B / d;
    c = C / d;
  }

  static int gcd(int a, int b) {
    int t;
    while (b != 0) {
      t = b;
      b = a % b;
      a = t;
    }
    return a;
  }

  double distance(Line l) {
    Vector u = p1.minus(p0);
    Vector v = l.p1.minus(l.p0);
    Vector w = p0.minus(l.p0);
    double a = u.dot(u);
    double b = u.dot(v);
    double c = v.dot(v);
    double d = u.dot(w);
    double e = v.dot(w);
    double D = a * c - b * b;
    double sc, tc;
    // line parameters of two closest points
    if (D < EPS) { // parallel
      sc = 0;
      tc = b > c ? d / b : e / c;
    } else {
      sc = (b * e - c * d) / D;
      tc = (a * e - b * d) / D;
    }
    Vector dp = w.plus(u.multiply(sc)).minus(v.multiply(tc)); // = L1(sc) - L2(tc)
    return dp.norm();
  }

  double segmentDistance(Line s) {
    Vector u = p1.minus(p0);
    Vector v = s.p1.minus(s.p0);
    Vector w = p0.minus(s.p0);
    double a = u.dot(u);
    double b = u.dot(v);
    double c = v.dot(v);
    double d = u.dot(w);
    double e = v.dot(w);
    double D = a * c - b * b;
    double sc, sN, sD = D;
    double tc, tN, tD = D;
    // line parameters of two closest points
    if (D < EPS) { // parallel
      sN = 0;
      sD = 1;
      tN = e;
      tD = c;
    } else {
      sN = b * e - c * d;
      tN = a * e - b * d;
      if (sN < 0) { // the s=0 edge is visible
        sN = 0;
        tN = e;
        tD = c;
      } else if (sN > sD) { // the s=1 edge is visible
        sN = sD;
        tN = e + b;
        tD = c;
      }
    }
    if (tN < 0) { // the t=0 edge is visible
      tN = 0;
      if (-d < 0)
        sN = 0;
      else if (-d > a)
        sN = sD;
      else {
        sN = -d;
        sD = a;
      }
    } else if (tN > tD) { // the t=1 edge is visible
      tN = tD;
      if (-d + b < 0)
        sN = 0;
      else if (-d + b > a)
        sN = sD;
      else {
        sN = -d + b;
        sD = a;
      }
    }
    sc = Math.abs(sN) < EPS ? 0 : sN / sD;
    tc = Math.abs(tN) < EPS ? 0 : tN / tD;
    Vector dp = w.plus(u.multiply(sc)).minus(v.multiply(tc)); // = S1(sc) - S2(tc)
    return dp.norm();
  }

  // -1: degenerate, 0: disjoint, 1: intersect, 2:same plane
  int rayIntersect(Triangle t) {
    Point inter;
    Vector vec0 = new Vector();
    Vector u, v, n;
    Vector dir, w0, w;
    double r, a, b;
    u = t.v1.minus(t.v0);
    v = t.v2.minus(t.v0);
    n = u.cross(v);
    if (n.equals(vec0))
      return -1;
    dir = p1.minus(p0);
    w0 = p0.minus(t.v0);
    a = -n.dot(w0);
    b = n.dot(dir);
    if (Math.abs(b) < EPS) // parallel
      if (a == 0)
        return 2;
      else
        return 0;
    r = a / b;
    if (r < 0)
      return 0;
    // for segment test if (r > 1)
    inter = p0.plus(dir.multiply(r));
    //is inter inside t?
    double uu, uv, vv, wu, wv, d;
    uu = u.dot(u);
    uv = u.dot(v);
    vv = v.dot(v);
    w = inter.minus(t.v0);
    wu = w.dot(u);
    wv = w.dot(v);
    d = uv * uv - uu * vv;
    // parametric coords
    double s, tt;
    s = (uv * wv - vv * wu) / d;
    if (s < 0 || s > 1)
      return 0;
    tt = (uv * wu - uu * wv) / d;
    if (tt < 0 || (s + tt) > 1)
      return 0;
    return 1;
  }

  // 0: disjoint, 1: intersect, 2: overlap
  int intersection(Line s, Point inter) {
    Point pi = intersection(s);
    if (pi != null) {
      inter.x = pi.x;
      inter.y = pi.y;
      inter.z = pi.z;
      return 1;
    }
    if (distance(s.p0) > 0)
      return 0;
    return 2;
  }

  Point intersection(Line s) {
    Vector u = p1.minus(p0);
    Vector v = s.p1.minus(s.p0);
    Vector w = p0.minus(s.p0);
    double d = u.perp(v);
    if (Math.abs(d) < EPS)
      return null;
    double sI = v.perp(w) / d; // intersetc parameter for this segment
    return p0.plus(u.multiply(sI));
  }

  boolean isParallel(Line s) {
    Vector u = p1.minus(p0);
    Vector v = s.p1.minus(s.p0);
    double d = u.perp(v);
    return Math.abs(d) < EPS;
  }

  // 0: disjoint or touch, 1: intersect, 2: overlap
  int segmentIntersectNotTouch(Line s) {
    int k = segmentIntersect2D(s);
    if (k != 1)
      return k;
    if (s.segmentDistance(p0) < EPS || s.segmentDistance(p1) < EPS)
      return 0;
    return 1;
  }

  // 0: disjoint, 1: intersect, 2: overlap
  int segmentIntersect2D(Line s) {
    Point inter; // intersection point
    Vector u = p1.minus(p0);
    Vector v = s.p1.minus(s.p0);
    Vector w = p0.minus(s.p0);
    double d = u.perp(v);
    if (Math.abs(d) < EPS) { // are parallel
      if (u.perp(w) != 0 || v.perp(w) != 0)
        return 0; // not collinear
      // collinear o degenerate
      double du = u.dot(u);
      double dv = v.dot(v);
      if (du == 0 && dv == 0) { // segments are points
        if (!p0.equals(s.p0))
          return 0; // distinct points
        return 2; // the same point
      }
      if (du == 0) { // this is a point
        if (s.inSegment(p0))
          return 2;
        return 0;
      }
      if (dv == 0) { // s is a point
        if (inSegment(s.p0))
          return 2;
        return 0;
      }
      // collinear segments
      double t0, t1; // endpoints of this in eqn for s
      Vector w2 = p1.minus(s.p0);
      if (v.x != 0) {
        t0 = w.x / v.x;
        t1 = w2.x / v.x;
      } else {
        t0 = w.y / v.y;
        t1 = w2.y / v.y;
      }
      if (t0 > t1) { // must have t0 smaller than t1
        double t = t0;
        t0 = t1;
        t1 = t;
      }
      if (t0 > 1 || t1 < 0)
        return 0;
      t0 = t0 < 0 ? 0 : t0;
      t1 = t1 > 1 ? 1 : t1;
      if (t0 == t1) { // intersect
        inter = s.p0.plus(v.multiply(t0));
        return 1;
      }
      // overlap
      inter = s.p0.plus(v.multiply(t0)); // begin segment
      inter = s.p0.plus(v.multiply(t1)); // end segment
      return 2;
    }
    double sI = v.perp(w) / d; // intersetc parameter for this segment
    if (sI < 0 || sI > 1)
      return 0;
    double tI = u.perp(w) / d; // intersect parameter for s
    if (tI < 0 || tI > 1)
      return 0;
    inter = p0.plus(u.multiply(sI));
    return 1;
  }

  boolean inSegment(Point p) { // just for collinear segment
    if (p0.x != p1.x) {
      if (p0.x <= p.x && p.x <= p1.x)
        return true;
      return p0.x >= p.x && p.x >= p1.x;
    } else {
      if (p0.y <= p.y && p.y <= p1.y)
        return true;
      return p0.y >= p.y && p.y >= p1.y;
    }
  }

  double closest2DPoint(Point[] p) {
    int N = p.length;
    double dist;
    double a = p0.y - p1.y;
    double b = p0.x - p1.x;
    double c = p0.x * p1.y - p1.x * p0.y;

    int closest = -1;
    double min = Double.MAX_VALUE;
    for (int i = 0; i < N; i++) {
      dist = Math.abs(a * p[i].x + b * p[i].y + c);
      if (dist < min) {
        closest = i;
        min = dist;
      }
    }
    return closest;
  }

  double distance(Point p) {
    Vector v = p1.minus(p0);
    Vector w = p.minus(p0);
    double b = w.dot(v) / v.dot(v);
    Point pb = p0.plus(v.multiply(b));
    return p.distance(pb);
  }

  boolean reflect(Point v, Point w) {
    double dv = distance(v);
    double dw = distance(w);
    if (Math.abs(dv - dw) > EPS)
      return false;
    Point vv = projection(v);
    Point ww = projection(w);
    return vv.equalsEPS(ww);
  }

  Point projection(Point p) {
    Vector v = p1.minus(p0);
    Vector w = p.minus(p0);
    double b = w.dot(v) / v.dot(v);
    Point pb = p0.plus(v.multiply(b));
    return pb;
  }

  double segmentDistance(Point p) {
    Vector v = p1.minus(p0);
    Vector w = p.minus(p0);
    double c1 = w.dot(v);
    if (c1 <= 0)
      return p.distance(p0);
    double c2 = v.dot(v);
    if (c2 <= c1)
      return p.distance(p1);
    double b = c1 / c2;
    Point pb = p0.plus(v.multiply(b));
    return p.distance(pb);
  }

  public String toString() {
    return p0 + "->" + p1;
  }
}
