public class Point implements Comparable<Point> {
	
	double x, y, z;
	final double EPS = 1e-9;
	
	Point(double _x, double _y, double _z) {
		x = _x;
		y = _y;
		z = _z;
	}
	
	Point(double _x, double _y) {
		this(_x, _y, 0);
	}
	
	Point medium(Point v) {
		return new Point((x + v.x) / 2, (y + v.y) / 2, (z + v.z) / 2);
	}
	
	boolean below(Point vi, Point vj) {
		return new Triangle(this, vi, vj).isLeft() < 0;
	}
	
	boolean above(Point vi, Point vj) {
		return new Triangle(this, vi, vj).isLeft() > 0;
	}
	
	public int compareTo(Point p) {
		if (x > p.x)
			return 1;
		if (x < p.x)
			return -1;
		if (y > p.y)
			return 1;
		if (y < p.y)
			return -1;
		return 0;
	}
	
	Vector minus(Point p) {
		return new Vector(x-p.x, y-p.y, z-p.z);
	}
	
	Point plus(Vector v) {
		return new Point(x+v.x, y+v.y, z+v.z);
	}
	
	double distance(Point p) {
		return Math.sqrt((x-p.x)*(x-p.x) + (y-p.y)*(y-p.y) + (z-p.z)*(z-p.z));
	}
	
	boolean equals(Point p) {
		return x==p.x && y==p.y && z==p.z;
	}

	boolean equalsEPS(Point p) {
		return Math.abs(x-p.x) < EPS && Math.abs(y-p.y) < EPS && Math.abs(z-p.z) < EPS;
	}
	
	public String toString() {
		return "(" + x + "," + y + "," + z + ")";
	}
}
