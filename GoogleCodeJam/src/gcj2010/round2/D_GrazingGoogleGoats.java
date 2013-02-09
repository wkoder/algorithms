package gcj2010.round2;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

import javax.management.RuntimeErrorException;

public class D_GrazingGoogleGoats {
	
	static final int INF = 1 << 28, MAX = 1000;
	static final double EPS = 1E-9;
	
	static void solve() {
		Scanner scan = new Scanner(in);
		int T = scan.nextInt();
		DecimalFormat f = new DecimalFormat("0.00000000");
		for (int t = 1; t <= T; t++) {
			out.print("Case #" + t + ":");
			int N = scan.nextInt();
			int M = scan.nextInt();
			Circle[] c = new Circle[N];
			for (int i = 0; i < N; i++)
				c[i] = new Circle(new Point(scan.nextDouble(), scan.nextDouble()), 0);
			while (M-- > 0) {
				Point q = new Point(scan.nextDouble(), scan.nextDouble());
				for (Circle cc : c)
					cc.radius = cc.center.distance(q);
				
				double area = c[0].getIntersectionArea(c[1]);
				out.print(" " + area);
			}
			out.println();
		}
	}
	
	static String read() {
		try { return in.readLine(); } catch (IOException e) { return null; }
	}
	public static void main(String[] args) throws IOException {
//		in = new BufferedReader(new InputStreamReader(System.in));
//		out = new PrintWriter(new BufferedOutputStream(System.out));
		String file = "D-small";
		in = new BufferedReader( new FileReader(file + ".in") );
		out = new PrintWriter( new FileOutputStream(file + "_" + System.currentTimeMillis() + ".out") );
		solve(); out.flush();
	}
	static BufferedReader in; static PrintWriter out;
	
	private static class Circle {
		
		static final double EPS = 1e-9;
		
		Point center;
		double radius;
		
		Circle(Point c, double r) {
			center = c;
			radius = r;
		}
		
		boolean isCircumferenceIntersect(Circle c) {
			return isIntersect(c) && ! (c.isInside(this) || isInside(c));
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
			return R*R * Math.acos(d / R) - d * Math.sqrt(R*R - d*d);
		}
		
		double getIntersectionArea(Circle c) {
			if (! isCircumferenceIntersect(c))
				if (isInside(c))
					return c.getArea();
				else if (c.isInside(this))
					return getArea();
				else
					return 0;
			double R = radius;
			double r = c.radius;
			double d = center.distance(c.center);
			double d1 = (d*d - r*r + R*R) / (2 * d);
			double d2 = (d*d + r*r - R*R) / (2 * d);
			return a(R, d1) + a(r, d2);
		}
	}

	private static class Point implements Comparable<Point> {
		
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

}
