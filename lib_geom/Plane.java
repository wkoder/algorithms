public class Plane {

  double EPS = 1E-9;
  Point v;
  Vector n;

  Plane(Point _v, Vector _n) {
    v = _v;
    n = _n;
  }

  double distance(Point p) {
    double sb, sn, sd;
    sn = -n.dot(p.minus(v));
    sd = n.dot(n);
    sb = sn / sd;
    Point pb = p.plus(n.multiply(sb)); // base point in plane
    return p.distance(pb);
  }

  // 0: disjoint, 1: intersection, 2: coincide
  int intersect3D(Plane p) {
    Line inter; // intersection line
    Vector u = n.cross(p.n);
    double ax = Math.abs(u.x);
    double ay = Math.abs(u.y);
    double az = Math.abs(u.z);
    if (ax + ay + ax < EPS) { // parallelv
      Vector w = p.v.minus(v);
      if (n.dot(w) == 0) // p.v lies in this plane
        return 2; // concide
      else
        return 0; // disjoint
    }
    int maxc; // max coordinate
    if (ax > ay)
      if (ax > az)
        maxc = 1;
      else
        maxc = 3;
    else if (ay > az)
      maxc = 2;
    else
      maxc = 3;
    // get a point on the intersect line
    double x = 0, y = 0, z = 0; // intersect point coordinates
    double d1, d2; // constants in the 2 plane equations
    d1 = -n.dot(p.v);
    d2 = -p.n.dot(p.v);
    switch (maxc) { // intersection with maxc (x, y, z)
      case 1:
        x = 0;
        y = (d2 * n.z - d1 * p.n.z) / u.x;
        z = (d1 * p.n.y - d2 * n.y) / u.x;
        break;
      case 2:
        x = (d1 * p.n.z - d2 * n.z) / u.y;
        y = 0;
        z = (d2 * n.x - d1 * p.n.x) / u.y;
        break;
      case 3:
        x = (d2 * n.y - d1 * p.n.y) / u.z;
        y = (d1 * p.n.x - d2 * n.x) / u.z;
        z = 0;
    }
    Point iP = new Point(x, y, z);
    inter = new Line(iP, iP.plus(u));
    return 1;
  }

  // 0: disjoint, 1: intersection, 2: segment lies in the plane
  int intersect3D(Line s) {
    Point inter; // intersection point
    Vector u = s.p1.minus(s.p0);
    Vector w = s.p0.minus(v);
    double d = n.dot(u);
    double N = -n.dot(w);
    if (Math.abs(d) < EPS) // parallel
      if (N == 0) // lies in the plane
        return 2;
      else
        return 0; // no intersection
    double sI = N / d; // intersect param
    if (sI < 0 || sI > 1)
      return 0;
    inter = s.p0.plus(u.multiply(sI));
    return 1;
  }
}
