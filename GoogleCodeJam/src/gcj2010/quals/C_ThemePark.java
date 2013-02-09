package gcj2010.quals;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class C_ThemePark {
	
	static final int INF = 1 << 28, MAX = 1100;
	static final double EPS = 1E-9;
	
	static void solve() {
		Scanner scan = new Scanner(in);
		int T = scan.nextInt();
		int[] g = new int[MAX];
		int[] cost = new int[MAX];
		int[] next = new int[MAX];
		for (int t = 1; t <= T; t++) {
			out.print("Case #" + t + ": ");
			int R = scan.nextInt();
			int K = scan.nextInt();
			int N = scan.nextInt();
			for (int i = 0; i < N; i++)
				g[i] = scan.nextInt();
			for (int i = 0; i < N; i++) {
				cost[i] = 0;
				for (int j = 0; j < N; j++) {
					int k = (i + j) % N;
					cost[i] += g[k];
					if (cost[i] > K) {
						cost[i] -= g[k];
						break;
					}
					next[i] = (k + 1) % N;
				}
			}
			long w = 0;
			int k = 0;
			while (R-- > 0) {
				w += cost[k];
				k = next[k];
			}
			out.println(w);
		}
	}
	
	static String read() {
		try { return in.readLine(); } catch (IOException e) { return null; }
	}
	public static void main(String[] args) throws IOException {
//		in = new BufferedReader(new InputStreamReader(System.in));
//		out = new PrintWriter(new BufferedOutputStream(System.out));
		String file = "C-large";
		in = new BufferedReader( new FileReader(file + ".in") );
		out = new PrintWriter( new FileOutputStream(file + "_" + System.currentTimeMillis() + ".out") );
		solve(); out.flush();
	}
	static BufferedReader in; static PrintWriter out;
}
