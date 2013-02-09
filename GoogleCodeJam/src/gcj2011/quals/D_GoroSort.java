package gcj2011.quals;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class D_GoroSort {
	
	static final int INF = 1 << 28, MAX = 1000;
	static final double EPS = 1E-9;
	
	static double[][] memo = new double[MAX][MAX];
	static int[] fact = new int[MAX];
	static int[][] comb = new int[MAX][MAX];
	
	static double f(int n, int level) {
		if (level == MAX)
			return 0;
		if (n == 1)
			return 0;
//		if (n == 2)
//			return 2;
		if (memo[n][level] >= 0)
			return memo[n][level];

		double x = 1;
		x += f(n, level+1) / fact[n];
		for (int k = 1; k < n; k++) {
			x += f(n-k, 0) * fact[n] / fact[k];
		}
		
		return memo[n][level] = x;
	}
	
	static void solve() {
		fact[0] = 1;
		for (int i = 1; i < MAX; i++)
			fact[i] = fact[i-1] * i;
		for (int i = 0; i < MAX; i++)
			Arrays.fill(memo[i], -1);
		comb[1][1] = 1;
		for (int i = 2; i < MAX; i++) {
			comb[i][0] = 1;
			for (int j = 1; j <= i; j++)
				comb[i][j] = comb[i-1][j-1] + comb[i-1][j];
		}
		
		System.out.println(f(2, 0));
		System.out.println(f(3, 0));
		System.out.println(f(4, 0));
		System.out.println(f(5, 0));
		System.out.println(f(6, 0));
		System.out.println(f(7, 0));
		System.out.println(f(8, 0));
		System.out.println(f(9, 0));
		System.out.println(f(10, 0));
		System.out.println(f(11, 0));
		
		Scanner scan = new Scanner(in);
		int T = scan.nextInt();
		int[] val = new int[MAX];
		boolean[] vis = new boolean[MAX];
		for (int t = 1; t <= T; t++) {
			out.print("Case #" + t + ": ");
			int n = scan.nextInt();
			for (int i = 0; i < n; i++)
				val[i] = scan.nextInt() - 1;
			
			double hits = 0;
			Arrays.fill(vis, false);
			for (int i = 0; i < n; i++)
				if (!vis[i]) {
					int size = 0;
					int k = i;
					while (!vis[k]) {
						vis[k] = true;
						k = val[k];
						size++;
					}
					
					hits += 2 * (size - 1);
				}
			
			out.println(hits);
		}
	}
	
	static String read() {
		try { return in.readLine(); } catch (IOException e) { return null; }
	}
	public static void main(String[] args) throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new BufferedOutputStream(System.out));
//		String file = "D-small";
//		in = new BufferedReader( new FileReader(file + ".in") );
//		out = new PrintWriter( new FileOutputStream(file + "_" + System.currentTimeMillis() + ".out") );
		solve(); out.flush();
	}
	static BufferedReader in; static PrintWriter out;
}
