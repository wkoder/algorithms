package round1a;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class C_NumberGame {
	
	static final int INF = 1 << 28, MAX = 1000;
	static final double EPS = 1E-9;
	
	static Set<Pair> win = new HashSet<Pair>(500000);
	static Set<Pair> lose = new HashSet<Pair>(500000);
	
	static boolean go(int a, int b) {
//		System.out.println(a + " "+ b);
		if (a <= 0 || b <= 0)
			return false;
		if (a > b)
			return go(b, a);
		if (b >= 2*a)
			return true;
		
		Pair p = new Pair(a, b);
		if (win.contains(p))
			return true;
		if (lose.contains(p))
			return false;
		
		return go(a, b-a);
	}
	
	static void solve() {
		Scanner scan = new Scanner(in);
		int T = scan.nextInt();
		for (int t = 1; t <= T; t++) {
			out.print("Case #" + t + ": ");
			int A1 = scan.nextInt();
			int A2 = scan.nextInt();
			int B1 = scan.nextInt();
			int B2 = scan.nextInt();
			
			int w = 0;
			for (int A = A1; A <= A2; A++)
				for (int B = B1; B <= B2; B++) {
					if (go(A, B))
						w++;
				}
			out.println(w);
		}
	}
	
	static String read() {
		try { return in.readLine(); } catch (IOException e) { return null; }
	}
	public static void main(String[] args) throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new BufferedOutputStream(System.out));
//		String file = "C-small";
//		in = new BufferedReader( new FileReader(file + ".in") );
//		out = new PrintWriter( new FileOutputStream(file + "_" + System.currentTimeMillis() + ".out") );
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
			return a*1000000 + b;
		}
		@Override
		public boolean equals(Object obj) {
			Pair p = (Pair) obj;
			return a == p.a && b == p.b;
		}
	}
}
