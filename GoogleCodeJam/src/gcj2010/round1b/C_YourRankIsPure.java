package gcj2010.round1b;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class C_YourRankIsPure {
	
	static final int INF = 1 << 28, MAX = 510;
	static final double EPS = 1E-9;
	static final int MOD = 100003;
	
	static long[][] choose = new long[MAX][MAX];
	static int[][] memo = new int[MAX][MAX];
	static int go(int p, int n) {
		if (p == 1)
			return 1;
		if (memo[p][n] >= 0)
			return memo[p][n];
		
		int w = 0;
		for (int p2 = 1; p2 < p; p2++)
			w = (int)((w + go(p2, p) * choose[n-p-1][p-p2-1]) % MOD);
		return memo[p][n] = w;
	}
	
	static void solve() {
		for (int i = 0; i < MAX; i++)
			choose[i][0] = choose[i][i] = 1;
		for (int i = 1; i < MAX; i++)
			for (int j = 1; j < i; j++)
				choose[i][j] = (choose[i - 1][j - 1] + choose[i - 1][j]) % MOD;
		for (int i = 0; i < MAX; i++)
			Arrays.fill(memo[i], -1);
		
		Scanner scan = new Scanner(in);
		int T = scan.nextInt();
		for (int t = 1; t <= T; t++) {
			out.print("Case #" + t + ": ");
			int n = scan.nextInt();
			int ways = 0;
			for (int i = 1; i < n; i++)
				ways = (ways + go(i, n)) % MOD;
			out.println(ways);
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
