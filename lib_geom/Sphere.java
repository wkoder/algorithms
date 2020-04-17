public class Sphere {

  Point center;
  double radius;

  public Sphere(Point c, double r) {
    center = c;
    radius = r;
  }

  static double haversine(double x) {
    return (1 - Math.cos(x)) / 2.0;
  }

  Point getPointOnSurface(double lat, double lon) {
    Point p = new Point(0, 0, 0);
    lat *= Math.atan(1.0) / 45.0;
    lon *= Math.atan(1.0) / 45.0;
    p.x = radius * Math.cos(lon) * Math.cos(lat);
    p.y = radius * Math.sin(lon) * Math.cos(lat);
    p.z = radius * Math.sin(lat);
    return p;
  }

  double surfaceDistance(double lat1, double lon1, double lat2, double lon2) {
    double a = haversine(lat2 - lat1);
    double b = Math.cos(lat1) * Math.cos(lat2) * haversine(lon2 - lon1);
    double c = 2 * Math.atan2(Math.sqrt(a + b), Math.sqrt(1 - a - b));
    return radius * c;
  }
}
