public class Vector {

  double x, y, z;

  Vector(double _x, double _y, double _z) {
    x = _x;
    y = _y;
    z = _z;
  }

  Vector(double _x, double _y) {
    this(_x, _y, 0);
  }

  Vector() {
  }

  Vector unit() {
    double len = length();
    return new Vector(x / len, y / len, z / len);
  }

  boolean up(Vector v) {
    return dot(v) > 0;
  }

  boolean down(Vector v) {
    return dot(v) < 0;
  }

  // direction sign of vi - vj
  double dr(Point vi, Point vj) {
    return dot(vi.minus(vj));
  }

  boolean above(Point vi, Point vj) {
    return dr(vi, vj) > 0;
  }

  boolean below(Point vi, Point vj) {
    return dr(vi, vj) < 0;
  }

  Vector negative() {
    return new Vector(-x, -y, -z);
  }

  double dot(Vector v) {
    return x * v.x + y * v.y + z * v.z;
  }

  double dot(Point p) {
    return x * p.x + y * p.y + z * p.z;
  }

  Vector cross(Vector v) {
    return new Vector(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x);
  }

  Vector perp() { // 2D
    return new Vector(-y, x);
  }

  double perp(Vector v) { // perp product 2D
    return x * v.y - y * v.x;
  }

  double norm() {
    return Math.sqrt(dot(this));
  }

  double length() {
    return Math.sqrt(x * x + y * y + z * z);
  }

  Vector minus(Vector v) {
    return new Vector(x - v.x, y - v.y, z - v.z);
  }

  Vector plus(Vector v) {
    return new Vector(x + v.x, y + v.y, z + v.z);
  }

  Vector multiply(double c) {
    return new Vector(x * c, y * c, z * c);
  }

  double distance(Vector v) {
    return minus(v).norm();
  }

  boolean equals(Vector v) {
    return x == v.x && y == v.y && z == v.z;
  }
}
