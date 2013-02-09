package year2012.round1;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class SquishedStatus {
	
	static final int INF = 1 << 28;
	static final double EPS = 1E-9;
	static final long MOD = 4207849484L;
	
	private static void hack() {
		Scanner scan = new Scanner(in);
		int T = scan.nextInt();
		for (int t = 1; t <= T; t++) {
			int M = scan.nextInt();
			String S = scan.next();
			char[] status = S.toCharArray();
			int N = status.length;
			long[] w = new long[N+1];
			w[0] = 1;
			for (int i = 1; i <= N; i++) {
				for (int j = i; j > 0; j--) {
					if (status[j-1] == '0')
						continue;
					long k = Long.parseLong(S.substring(j-1, i));
					if (k > M)
						break;
					w[i] += w[j-1];
					w[i] %= MOD;
				}
			}
			
			out.println("Case #" + t + ": " + w[N]);
		}
	}
	
	static String read() {
		try { return in.readLine(); } catch (IOException e) { return null; }
	}
	public static void main(String[] args) throws IOException {
//		in = new BufferedReader(new InputStreamReader(System.in));
//		out = new PrintWriter(new BufferedOutputStream(System.out));
		String file = "squished_status";
		in = new BufferedReader( new FileReader(file + ".txt") );
		out = new PrintWriter( new FileOutputStream(file + "_" + System.currentTimeMillis() + ".out") );
		hack(); out.flush();
	}
	static BufferedReader in; static PrintWriter out;
}
