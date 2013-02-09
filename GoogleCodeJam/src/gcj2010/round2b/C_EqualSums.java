package gcj2010.round2b;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class C_EqualSums {
	
	static final int INF = 1 << 28, MAX = 1000;
	static final double EPS = 1E-9;
	
	static int sum(int[] k, int mask) {
		int sum = 0;
		for (int i = 0; i < k.length; i++)
			if ((mask & (1 << i)) != 0)
				sum += k[i];
		return sum;
	}
	
	static void printSubset(int[] k, int mask) {
		for (int i = 0; i < k.length; i++)
			if ((mask & (1 << i)) != 0)
				out.print(k[i] + " ");
		out.println();
	}
	
	static void solve() {
		Scanner scan = new Scanner(in);
		int T = scan.nextInt();
		Map<Integer, List<Integer>> subsets = new HashMap<Integer, List<Integer>>(1 << 20);
		for (int t = 1; t <= T; t++) {
			subsets.clear();
			out.println("Case #" + t + ":");
			int N = scan.nextInt();
			int[] k = new int[N];
			for (int i = 0; i < N; i++)
				k[i] = scan.nextInt();
			
			final int ALL = (1 << N) - 1;
			boolean ok = false;
			for (int mask = 1; mask < ALL && !ok; mask++) {
				int sum = sum(k, mask);
				List<Integer> subs = subsets.get(sum);
				if (subs == null) {
					subs = new ArrayList<Integer>();
					subsets.put(sum, subs);
				}
				
				for (int s : subs)
					if ((s & mask) == 0) {
						printSubset(k, s);
						printSubset(k, mask);
						ok = true;
						break;
					}
				subs.add(mask);
			}
			if (!ok) {
				out.println("Impossible");
			}
		}
	}
	
	static String read() {
		try { return in.readLine(); } catch (IOException e) { return null; }
	}
	public static void main(String[] args) throws IOException {
//		in = new BufferedReader(new InputStreamReader(System.in));
//		out = new PrintWriter(new BufferedOutputStream(System.out));
		String file = "C-small";
		in = new BufferedReader( new FileReader(file + ".in") );
		out = new PrintWriter( new FileOutputStream(file + "_" + System.currentTimeMillis() + ".out") );
		solve(); out.flush();
	}
	static BufferedReader in; static PrintWriter out;
}
