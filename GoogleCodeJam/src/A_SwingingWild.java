import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class A_SwingingWild {
	
	static final int INF = 1 << 28, MAX = 10000;
	static final double EPS = 1E-9;
//	static final HashSet<Pair> seen = new HashSet<A_SwingingWild.Pair>(100000);
	static final int[] seen = new int[MAX];
	static final Queue<Pair> queue = new LinkedList<A_SwingingWild.Pair>();
	
	static void queue(Pair p) {
		if (seen[p.a] < p.b) {
			seen[p.a] = p.b;
			queue.add(p);
		}
	}
	
	static void solve() {
		Scanner scan = new Scanner(in);
		int T = scan.nextInt();
		for (int t = 1; t <= T; t++) {
			out.print("Case #" + t + ": ");
			for (int i = 0; i < MAX; i++)
				seen[i] = -1;
			queue.clear();
			
			int N = scan.nextInt();
			int[] d = new int[N];
			int[] l = new int[N];
			for (int i = 0; i < N; i++) {
				d[i] = scan.nextInt();
				l[i] = scan.nextInt();
			}
			int D = scan.nextInt();
			queue(new Pair(0, d[0]));
			boolean ok = false;
			while (!queue.isEmpty()) {
				Pair p = queue.poll();
				if (d[p.a] + p.b >= D) {
					ok = true;
					break;
				}
				
				for (int i = p.a+1; i < N && p.b >= d[i]-d[p.a]; i++)
					queue(new Pair(i, Math.min(l[i], d[i]-d[p.a])));
			}
			out.println(ok ? "YES" : "NO");
		}
	}
	
	static String read() {
		try { return in.readLine(); } catch (IOException e) { return null; }
	}
	public static void main(String[] args) throws IOException {
//		in = new BufferedReader(new InputStreamReader(System.in));
//		out = new PrintWriter(new BufferedOutputStream(System.out));
		String file = "A-large";
		in = new BufferedReader( new FileReader(file + ".in") );
		out = new PrintWriter( new FileOutputStream(file + "_" + System.currentTimeMillis() + ".out") );
		solve(); out.flush();
	}
	static BufferedReader in; static PrintWriter out;
	
	static class Pair {
		int a, b;
		public Pair(int aa, int bb) {
			a = aa;
			b = bb;
		}
		@Override
		public int hashCode() {
			return (a * 1337) ^ b;
		}
		@Override
		public boolean equals(Object obj) {
			Pair p = (Pair) obj;
			return a == p.a && b == p.b;
		}
		@Override
		public String toString() {
			return "(" + a + "," + b + ")";
		}
	}
}
