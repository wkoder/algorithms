package round1c;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class B_LoadTesting {
	
	static final int INF = 1 << 28, MAX = 1000;
	static final double EPS = 1E-9;
	
	static void solve() {
		Scanner scan = new Scanner(in);
		int T = scan.nextInt();
		for (int t = 1; t <= T; t++) {
			out.print("Case #" + t + ": ");
			long L = scan.nextInt();
			long P = scan.nextInt();
			int C = scan.nextInt();
			int tests = 0;
			int x = 0;
			L *= C;
			while (L < P) {
				x++;
				L *= C;
			}
			while (x > 0) {
				tests++;
				x /= 2;
			}
			out.println(tests);
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
