import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class B {
	
	static final int INF = 1 << 28, MAX = 1000;
	static final double EPS = 1E-9;
	
	static double dist(double x1, double y1, double x2, double y2) {
		double dx = Math.abs(x1 - x2);
		double dy = Math.abs(y1 - y2);
		return Math.hypot(dx, dy);
	}
	
	static void solve() {
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();
		for (int t = 1; t <= T; t++) {
			int x1 = scan.nextInt();
			int y1 = scan.nextInt();
			int x2 = scan.nextInt();
			int y2 = scan.nextInt();
			double P = scan.nextDouble();
			double Q = scan.nextDouble();
			double R = scan.nextDouble();
			
			double min = Double.MAX_VALUE;
			min = Math.min(min, Math.abs(x1-x2) + Math.abs(y1-y2));
			
			double[][] a = new double[2][2];
			a[0][0] = x1;
			a[0][1] = (R - P*x1) / Q;
			a[1][0] = (R - Q*y1) / P;
			a[1][1] = y1;
			
			double[][] b = new double[2][2];
			b[0][0] = x2;
			b[0][1] = (R - P*x2) / Q;
			b[1][0] = (R - Q*y2) / P;
			b[1][1] = y2;
			
			for (int i = 0; i < 2; i++)
				if (!Double.isInfinite(a[i][0]) && !Double.isInfinite(a[i][0]))
					for (int j = 0; j < 2; j++)
						if (!Double.isInfinite(b[j][0]) && !Double.isInfinite(b[j][0]))
							min = Math.min(min, dist(x1, y1, a[i][0], a[i][1]) + dist(x2, y2, b[j][0], b[j][1]) + dist(a[i][0], a[i][1], b[j][0], b[j][1]));
			System.out.println(min);
		}
	}
	
	public static void main(String[] args) throws IOException {
		solve();
	}
}
