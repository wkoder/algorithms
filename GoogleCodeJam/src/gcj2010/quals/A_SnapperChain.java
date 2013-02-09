package gcj2010.quals;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class A_SnapperChain {
	
	static final int INF = 1 << 28, MAX = 1000;
	static final double EPS = 1E-9;
	
	static void solve() {
		int i, j, k;
		Scanner scan = new Scanner(in);
		int T = scan.nextInt();
		for (int t = 1; t <= T; t++) {
			out.print("Case #" + t + ": ");
			int N = scan.nextInt();
			int K = scan.nextInt();
//			if ((K / (1 << (N-1))) % 2 == 1)
			if (K % (1 << N) == (1 << N)-1)
				out.println("ON");
			else
				out.println("OFF");
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
}
