public class Track {

  double EPS = 1E-9;
  Point p;
  Vector v;

  Track(Point _p, Vector _v) {
    p = _p;
    v = _v;
  }

  // time at wich two tracks are closest (closest point of approach)
  double cpaTime(Track t) {
    Vector dv = v.minus(t.v);
    double dv2 = dv.dot(dv);
    if (dv2 < EPS) // parallel
      return 0; // any time is ok
    Vector w0 = p.minus(t.p);
    double time = -w0.dot(dv) / dv2;
    return time;
  }

  double cpaDistance(Track t) {
    double time = cpaTime(t);
    Point p1 = p.plus(v.multiply(time));
    Point p2 = t.p.plus(t.v.multiply(time));
    return p1.distance(p2);
  }
}
