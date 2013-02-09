package gcj2012.quals;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class DancingWithTheGooglers {
	
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
			int[] points = new int[N];
			for (int i = 0; i < N; i++)
				points[i] = scan.nextInt();
			int max = 0;
			for (int mask = 0; mask < 1<<N; mask++) {
				if (Integer.bitCount(mask) != S)
					continue;
				int k = 0;
				for (int i = 0; i < N; i++) {
					int a = points[i] / 3;
					int r = points[i] % 3;
					if ((mask & (1 << i)) == 0) {
						if (r > 0)
							a++;
					} else {
						if (r == 0 && a > 0)
							a++;
						else
							a += r;
					}
					if (a >= P)
						k++;
				}
				
				max = Math.max(max, k);
			}
			out.println(max);
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
