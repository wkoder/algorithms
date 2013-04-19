import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class SolarEclipse {
	
	static final int MAX = 100;
	static final int INF = 1 << 30;
	static final double EPS = 1e-7;
	static final int[] DX = {-1, 0, 1, 0};
	static final int[] DY = {0, 1, 0, -1};
	
	static Point[] p = new Point[MAX];
	static int N;
	static double R;
	
	static boolean ok(Point me) {
		for (int i = 0; i < N; i++)
			if (Math.hypot(me.x - p[i].x, me.y - p[i].y) < 2*R-EPS)
				return false;
		return true;
	}
	static double testOk(double best, Point me) {
		double dd = Math.hypot(me.x, me.y);
		if (dd < best-EPS && ok(me))
			best = dd;
		return best;
	}
	
	public static void solve() {
		DecimalFormat format = new DecimalFormat("0.000000");
		while (true) {
			StringTokenizer tok = new StringTokenizer(read());
			N = Integer.parseInt(tok.nextToken());
			R = Double.parseDouble(tok.nextToken());
			for (int i = 0; i < N; i++) {
				tok = new StringTokenizer(read());
				p[i] = new Point(Double.parseDouble(tok.nextToken()), Double.parseDouble(tok.nextToken()));
			}
			
			double best = INF;
			if (ok(new Point(0, 0))) { // Try the original target
				best = 0;
			} else {
				// Try each 4 sides
				for (int i = 0; i < N; i++) {
					for (int k = 0; k < DX.length; k++) {
						double x = p[i].x + DX[k]*R;
						double y = p[i].y + DY[k]*R;
						double d = Math.hypot(x, y);
						if (d > best-EPS)
							continue;
						
						if (ok(new Point(x, y)))
							best = d;
					}
				}
				
				// Try 2 ways of touching 2 circles
				for (int i = 0; i < N; i++)
					for (int j = i+1; j < N; j++) {
						double d = Math.hypot(p[i].x - p[j].x, p[i].y - p[j].y);
						if (d > 3*R-EPS) // Too far, no touching
							continue;
						
						double xx = (p[i].x + p[j].x) / 2;
						double yy = (p[i].y + p[j].y) / 2;
						double h = Math.sqrt(2*R*2*R - d/2*d/2); // Triangle height
						if (Math.abs(p[i].x - p[j].x) < EPS) { // m = INF
							best = testOk(best, new Point(xx, yy+h));
							best = testOk(best, new Point(xx, yy-h));
						} else {
							double m = - (p[j].y - p[i].y) / (p[j].x - p[i].x);
							best = testOk(best, new Point(xx + m*h, yy + m*h));
							best = testOk(best, new Point(xx - m*h, yy - m*h));
						}
					}
			}
			
			out.println(format.format(best));
		}
	}
	
	static class Point {
		double x, y;
		public Point(double xx, double yy) {
			x = xx;
			y = yy;
		}
	}
	
	public static String read() {
		try { return in.readLine(); } catch (IOException e) { return null; }
	}
	public static void main(String[] args) throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new BufferedOutputStream(System.out));
		solve(); out.flush();
	}
	static BufferedReader in; static PrintWriter out;
}
