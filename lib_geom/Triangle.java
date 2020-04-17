public class Triangle {

  static final double EPS = 1e-9;
  Point v0, v1, v2;

  Triangle(Point _v0, Point _v1, Point _v2) {
    v0 = _v0;
    v1 = _v1;
    v2 = _v2;
  }

  static boolean sameSide(Point p1, Point p2, Point a, Point b) {
    Vector cp1 = b.minus(a).cross(p1.minus(a));
    Vector cp2 = b.minus(a).cross(p2.minus(a));
    return cp1.dot(cp2) >= 0;
  }

  static boolean isLeft(Point v0, Point v1, Point v2) { // strictly left, use -EPS for boundaries
    return (v1.x - v0.x) * (v2.y - v0.y) - (v2.x - v0.x) * (v1.y - v0.y) > EPS;
  }

  // > 0: counterclockwise, < 0: clockwise, = 0: in line
  double isLeft() {
    return (v1.x - v0.x) * (v2.y - v0.y) - (v2.x - v0.x) * (v1.y - v0.y);
  }

  double area2D() {
    return isLeft() / 2.0;
  }

  boolean inside(Point p) {
    return isLeft(v0, v1, p) && isLeft(v1, v2, p) && isLeft(v2, v0, p);
  }

  boolean inside2(Point p) {
    return sameSide(p, v0, v1, v2) && sameSide(p, v1, v0, v2) && sameSide(p, v2, v0, v1);
  }
}
