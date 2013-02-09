package gcj2010.round1c;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class A_RopeIntranet {
	
	static final int INF = 1 << 28, MAX = 1000;
	static final double EPS = 1E-9;
	
	static void solve() {
		Scanner scan = new Scanner(in);
		int T = scan.nextInt();
		int[] a = new int[MAX];
		int[] b = new int[MAX];
		for (int t = 1; t <= T; t++) {
			out.print("Case #" + t + ": ");
			int N = scan.nextInt();
			for (int i = 0; i < N; i++) {
				a[i] = scan.nextInt();
				b[i] = scan.nextInt();
			}
			int points = 0;
			for (int i = 0; i < N; i++)
				for (int j = i+1; j < N; j++)
					if ((a[i] > a[j] && b[i] < b[j]) || (a[i] < a[j] && b[i] > b[j]))
						points++;
			out.println(points);
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
