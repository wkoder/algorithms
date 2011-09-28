import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class B_WorldCup2010 {
	
	static final int INF = 1 << 28, MAX = 1000;
	static final double EPS = 1E-9;
	
	static int[] m;
	
	static int go(int from, int to) {
		int cost = 0;
		for (int i = from; i < to; i++)
			if (m[i] > 0) {
				cost = 1;
				break;
			}
		if (cost == 1) {
			for (int i = from; i < to; i++)
				m[i]--;
			int m = (from + to) / 2;
			cost += go(from, m) + go(m, to);
		}
//		System.out.println(from + " " + to +  " = " + cost);
		return cost;
	}
	
	static void solve() {
		Scanner scan = new Scanner(in);
		int T = scan.nextInt();
		for (int t = 1; t <= T; t++) {
			out.print("Case #" + t + ": ");
			int P = scan.nextInt();
			m = new int[1 << P];
			for (int i = 0; i < 1 << P; i++)
				m[i] = P-scan.nextInt();
			for (int p = P-1; p >= 0; p--)
				for (int i = 0; i < 1 << p; i++)
					scan.nextInt();
			out.println(go(0, 1<<P));
		}
	}
	
	static String read() {
		try { return in.readLine(); } catch (IOException e) { return null; }
	}
	public static void main(String[] args) throws IOException {
//		in = new BufferedReader(new InputStreamReader(System.in));
//		out = new PrintWriter(new BufferedOutputStream(System.out));
		String file = "B-small";
		in = new BufferedReader( new FileReader(file + ".in") );
		out = new PrintWriter( new FileOutputStream(file + "_" + System.currentTimeMillis() + ".out") );
		solve(); out.flush();
	}
	static BufferedReader in; static PrintWriter out;
}
