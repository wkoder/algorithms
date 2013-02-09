package gcj2012.quals;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class DancingWithTheGooglersHard {
	
	static final int INF = 1 << 28, MAX = 1000;
	static final double EPS = 1E-9;
	
	static void solve() {
		Scanner scan = new Scanner(in);
		int T = scan.nextInt();
		for (int t = 1; t <= T; t++) {
			out.print("Case #" + t + ": ");
			int N = scan.nextInt();
			int S = scan.nextInt();
			int P = scan.nextInt();
			int w = 0;
			for (int i = 0; i < N; i++) {
				int points = scan.nextInt();
				int a = points / 3;
				int r = points % 3;
				if (a >= P) {
					w++;
					continue;
				}
				
				if (a == P-1 && r > 0) {
					w++;
					continue;
				}
				
				if (S == 0)
					continue;
				
				int extra;
				if (r == 0 && a > 0)
					extra = 1;
				else
					extra = r;
				if (a + extra >= P) {
					w++;
					S--;
				}
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
		String file = "B-large";
		in = new BufferedReader( new FileReader(file + ".in") );
		out = new PrintWriter( new FileOutputStream(file + "_" + System.currentTimeMillis() + ".out") );
		solve(); out.flush();
	}
	static BufferedReader in; static PrintWriter out;
}
