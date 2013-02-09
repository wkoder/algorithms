package gcj2010.round1b;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class B_PickingUpChicks {
	
	static final int INF = 1 << 28, MAX = 1000;
	static final double EPS = 1E-9;
	
	static void solve() {
		Scanner scan = new Scanner(in);
		int TT = scan.nextInt();
		for (int t = 1; t <= TT; t++) {
			out.print("Case #" + t + ": ");
			int N = scan.nextInt();
			int K = scan.nextInt();
			int B = scan.nextInt();
			int T = scan.nextInt();
			int[] x = new int[N];
			int[] v = new int[N];
			for (int i = 0; i < N; i++)
				x[i] = scan.nextInt();
			for (int i = 0; i < N; i++)
				v[i] = scan.nextInt();
			
			int canCount = 0;
			int swaps = 0;
			int toPass = 0;
			for (int k = N-1; k >= 0 && canCount < K; k--)
				if (x[k] + T*v[k] >= B) {
					canCount++;
					swaps += toPass;
				} else {
					toPass++;
				}
			
			if (canCount >= K)
				out.println(swaps);
			else
				out.println("IMPOSSIBLE");
		}
	}
	
	static String read() {
		try { return in.readLine(); } catch (IOException e) { return null; }
	}
	public static void main(String[] args) throws IOException {
//		in = new BufferedReader(new InputStreamReader(System.in));
//		out = new PrintWriter(new BufferedOutputStream(System.out));
		String file = "B-large";
		in = new BufferedReader( new FileReader(file + ".in") );
		out = new PrintWriter( new FileOutputStream(file + "_" + System.currentTimeMillis() + ".out") );
		solve(); out.flush();
	}
	static BufferedReader in; static PrintWriter out;
	
	static class Chick implements Comparable<Chick> {
		int x, v;
		public Chick(int xx, int vv) {
			x = xx;
			v = vv;
		}
		public int compareTo(Chick c) {
			if (x != c.x)
				return x - c.x;
			return v - c.v;
		}
	}
}
