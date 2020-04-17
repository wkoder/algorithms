public class Circle {

  static final double EPS = 1e-9;

  Point center;
  double radius;

  Circle(Point c, double r) {
    center = c;
    radius = r;
  }

  static Circle getCircumcircle(Point v0, Point v1, Point v2) { // in 2D
    Vector v = v1.minus(v0);
    Vector w = v1.minus(v2);
    Point midV = v0.plus(v.multiply(0.5));
    Point midW = v2.plus(w.multiply(0.5));
    Vector perpV = v.perp();
    Vector perpW = w.perp();
    Line lv = new Line(midV, midV.plus(perpV));
    Line lw = new Line(midW, midW.plus(perpW));
    Point center = lv.intersection(lw);
    if (center == null)
      return null;
    return new Circle(new Point(center.x, center.y), center.distance(v0));
  }

  static Circle fastball(Point[] v) {
    int N = v.length;
    Point c; // center of ball
    double rad, rad2, xmin, ymin, xmax, ymax;
    int pxmin, pxmax, pymin, pymax; // index if v[] at box extreme
    xmin = xmax = v[0].x;
    ymin = ymax = v[0].y;
    pxmin = pxmax = pymin = pymax = 0;
    for (int i = 1; i < N; i++) {
      if (v[i].x < xmin) {
        xmin = v[i].x;
        pxmin = i;
      } else if (v[i].x > xmax) {
        xmax = v[i].x;
        pxmax = i;
      }
      if (v[i].y < ymin) {
        ymin = v[i].y;
        pymin = i;
      } else if (v[i].y > ymax) {
        ymax = v[i].y;
        pymax = i;
      }
    }
    // largest extend as initial diameter
    Vector dVx = v[pxmax].minus(v[pxmin]);
    Vector dVy = v[pymax].minus(v[pymin]);
    double dx2 = dVx.dot(dVx); // norm^2
    double dy2 = dVy.dot(dVy);
    if (dx2 >= dy2) {
      c = v[pxmin].plus(dVx.multiply(0.5));
      rad2 = v[pxmax].minus(c).dot(v[pxmax].minus(c));
    } else {
      c = v[pymin].plus(dVy.multiply(0.5));
      rad2 = v[pymax].minus(c).dot(v[pymax].minus(c));
    }
    rad = Math.sqrt(rad2);
    // check all points are in the ball
    Vector dV;
    double dist, dist2;
    for (int i = 0; i < N; i++) {
      dV = v[i].minus(c);
      dist2 = dV.dot(dV);
      if (dist2 <= rad2) // v[i] is inside
        continue;
      // expand ball
      dist = Math.sqrt(dist2);
      rad = (rad + dist) / 2; // enlarge radius
      rad2 = rad * rad;
      c = c.plus(dV.multiply((dist - rad) / dist)); // shift center toward v[i]
    }
    return new Circle(c, rad);
  }

  Line[] externalTangents(Circle c) {
    if (radius < c.radius)
      return c.externalTangents(this);
    double h = center.distance(c.center);
    double r = radius - c.radius;
    double phi = Math.acos(r / h);
    double dy = c.center.y - center.y;
    double dx = c.center.x - center.x;
    double theta = Math.atan2(dy, dx) - phi;
    while (theta < 0)
      theta += 2 * Math.PI;
    double x = r * Math.cos(theta);
    double y = r * Math.sin(theta);
    Vector v = new Vector(radius * Math.cos(theta) - x, radius * Math.sin(theta) - y);
    Line up = new Line(c.center.plus(v), new Point(x, y).plus(v));
    v.y = -v.y;
    y = -y;
    Line down = new Line(c.center.plus(v), new Point(x, y).plus(v));
    return new Line[]{up, down};
  }

  Line[] internalTangents(Circle c) {
    if (radius < c.radius)
      return c.internalTangents(this);
    double h = center.distance(c.center);
    double r = radius + c.radius;
    double phi = Math.acos(r / h);
    double dy = c.center.y - center.y;
    double dx = c.center.x - center.x;
    double theta = Math.atan2(dy, dx) - phi;
    while (theta < 0)
      theta += 2 * Math.PI;
    double x = r * Math.cos(theta);
    double y = r * Math.sin(theta);
    Vector v = new Vector(radius * Math.cos(theta) - x, radius * Math.sin(theta) - y);
    Line up = new Line(c.center.plus(v), new Point(x, y).plus(v));
    v.y = -v.y;
    y = -y;
    Line down = new Line(c.center.plus(v), new Point(x, y).plus(v));
    return new Line[]{up, down};
  }

  boolean isCircumferenceIntersect(Circle c) {
    return isIntersect(c) && !(c.isInside(this) || isInside(c));
  }

  boolean isIntersect(Circle c) {
    double d = center.distance(c.center);
    return radius + c.radius >= d + EPS;
  }

  boolean isInside(Circle c) {
    double d = center.distance(c.center);
    return d + c.radius <= radius + EPS;
  }

  double getArea() {
    return Math.PI * radius * radius;
  }

  private double a(double R, double d) {
    return R * R * Math.acos(d / R) - d * Math.sqrt(R * R - d * d);
  }

  double getIntersectionArea(Circle c) {
    if (!isCircumferenceIntersect(c))
      if (isInside(c))
        return c.getArea();
      else if (c.isInside(this))
        return getArea();
      else
        return 0;
    double R = radius;
    double r = c.radius;
    double d = center.distance(c.center);
    double d1 = (d * d - r * r + R * R) / (2 * d);
    double d2 = (d * d + r * r - R * R) / (2 * d);
    return a(R, d1) + a(r, d2);
  }
}
