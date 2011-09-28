import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class I {
	
	static final int INF = 1 << 28, MAX = 1000;
	static final double EPS = 1E-9;
	
	static void solve() {
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();
		for (int t = 1; t <= T; t++) {
			int N = scan.nextInt();
			int[] x = new int[N];
			int[] y = new int[N];
			for (int i = 0; i < N; i++) {
				x[i] = scan.nextInt();
				y[i] = scan.nextInt();
			}
			double dmax = 0;
			double dmax2 = 0;
			for (int i = 1; i < N; i++) {
				double d = Math.hypot(x[i]-x[0], y[i]-y[0]);
				System.out.println(d);
				if (d+EPS > dmax) {
					dmax2 = dmax;
					dmax = d;
				} else if (d+EPS > dmax2)
					dmax2 = d;
			}
			System.out.println(dmax2*N/2);
		}
	}
	
	public static void main(String[] args) throws IOException {
		solve();
	}
}
