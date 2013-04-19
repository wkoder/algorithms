import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class Permutations {
	
	static final int INF = 1 << 28;
	static final double EPS = 1E-9;
	static final long MOD = 1000000007;
	static final int MAX = 2000;
	
	static long[] memo = new long[MAX];
	static List<Integer>[] children;
	static int[][] desc = new int[2][MAX];
	static List<Integer>[] parents;
//	static long[] fact = new long[MAX];
	static long[][] choose = new long[MAX+1][MAX+1];
	
	static long go(int k) {
		if (memo[k] >= 0)
			return memo[k];
		
		long ways = 1;
		int len = 0;
		for (int c : children[k]) {
			long wc = go(c);
			int lc = calcDesc(c, true);
//			out.println("Curr ways of " + k + ": " + ways);
//			out.println("Curr len of " + k + ": " + len);
//			out.println("Len of " + c + ": " + lc);
//			out.println("Ways of " + c + ": " + wc);
//			ways = (((ways * wc) % MOD) * fact[len+1]) % MOD;
			ways = (((ways * wc) % MOD) * choose[len + lc][lc]) % MOD;
			len += lc;
		}
		
//		out.println("Ways of " + k + ": " + ways);
		
		return memo[k] = ways;
	}
	
	static int calcDesc(int k, boolean down) {
		if (k < 0)
			return 0;
		int d = down ? 1 : 0;
		if (desc[d][k] >= 0)
			return desc[d][k];
		desc[d][k] = 1;
		if (down) {
			for (int c : children[k])
				desc[d][k] += calcDesc(c, down);
		} else { // UP
			for (int c : parents[k])
				desc[d][k] += calcDesc(c, down);
		}
		return desc[d][k];
	}
	
	static void invert(int k) {
		List<Integer> tmp = parents[k];
		parents[k] = children[k];
		children[k] = tmp;
		for (int c : children[k])
			invert(c);
	}
	
	private static void hack() {
//		fact[0] = 1;
//		for (int i = 1; i < MAX; i++)
//			fact[i] = (fact[i-1] * i) % MOD;
		for (int i = 0; i <= MAX; i++)
			choose[i][0] = choose[i][i] = 1;
		for (int i = 1; i <= MAX; i++)
			for (int j = 1; j < i; j++)
				choose[i][j] = (choose[i - 1][j - 1] + choose[i - 1][j]) % MOD;
		
		Scanner scan = new Scanner(in);
		int T = scan.nextInt();
		for (int t = 1; t <= T; t++) {
			out.print("Case #" + t + ": ");
			int N = scan.nextInt();
			scan.nextLine();

			children = new List[N];
			parents = new List[N];
			for (int i = 0; i < N; i++) {
				children[i] = new ArrayList<Integer>();
				parents[i] = new ArrayList<Integer>();
			}
			for (int i = 0; i < N-1; i++) {
				int a = scan.nextInt();
				String s = scan.next();
				int b = scan.nextInt();
				if (s.equals(">")) {
					int tmp = a;
					a = b;
					b = tmp;
				}
				parents[b].add(a);
				children[a].add(b);
			}
			
			for (int i = 0; i < 2; i++)
				Arrays.fill(desc[i], -1);
			int root = -1;
			for (int i = 0; i < N; i++) {
				int down = calcDesc(i, true);
				int up = calcDesc(i, false);
				if (down + up == N+1) {
					root = i;
					break;
				}
			}
			
//			for (int i = 0; i < N; i++)
//				out.println(children[i]);
//			System.out.println(root);
			Arrays.fill(memo, -1);
			long ways = go(root);
//			out.println("ways: " + ways);
			
			// Inverting it
//			for (int c : children[root])
//				parents[c].clear(); // Disconnect them
//			children[root].clear();
//			for (int i = 0; i < N; i++)
//				if (parents[i].contains(root)) {
////					parents[i].remove((Integer)root);
//					children[root].add(i);
//				}
			invert(root);
//			out.println("---");
//			for (int i = 0; i < N; i++)
//				out.println(children[i]);
			// Clear it
			memo[root] = -1;
			for (int i = 0; i < 2; i++)
				Arrays.fill(desc[i], -1);
			ways = (ways * go(root)) % MOD;
			
			out.println(ways);
		}
	}
	
	static String read() {
		try { return in.readLine(); } catch (IOException e) { return null; }
	}
	public static void main(String[] args) throws IOException {
//		in = new BufferedReader(new InputStreamReader(System.in));
//		out = new PrintWriter(new BufferedOutputStream(System.out));
		String file = "permutations";
		in = new BufferedReader( new FileReader(file + ".txt") );
		out = new PrintWriter( new FileOutputStream(file + "_" + System.currentTimeMillis() + ".out") );
		hack(); out.flush();
	}
	static BufferedReader in; static PrintWriter out;
}
