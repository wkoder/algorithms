package year2012.round1;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class CheckPoint {
	
	static final long INF = 1L << 60;
	static final double EPS = 1E-9;
	static final int MAX = 10000001;
	
	static long f(long S) {
		if (S == 1)
			return 1;
		long min = S;
		long Sk = S;
		for (long k = 1; k <= S && k < min; k++) {
			Sk *= k;
			if (Sk >= INF)
				break;
			next:
			for (long n = k; n <= S && n < min; n++) {
				long B = Sk;
				for (long r = n; r >= (n-k+1); r--)
					if (B % r == 0)
						B /= r;
					else
						continue next;
				
				if (B == 1) {
					min = n;
//					System.out.println(n + " " + k);
					break;
				}
			}
		}
//		System.out.println(S + "=" + min);
		
		return min;
	}
	
	private static void hack() {
		Scanner scan = new Scanner(in);
		int T = scan.nextInt();
		for (int t = 1; t <= T; t++) {
			long S = scan.nextInt();
			long min = INF;
			for (long a = 1; a*a <= S; a++) {
				if (S % a != 0)
					continue;
				long b = S / a;
				min = Math.min(min, f(a) + f(b));
			}
			out.println("Case #" + t + ": " + min);
		}
	}
	
	static String read() {
		try { return in.readLine(); } catch (IOException e) { return null; }
	}
	public static void main(String[] args) throws IOException {
//		in = new BufferedReader(new InputStreamReader(System.in));
//		out = new PrintWriter(new BufferedOutputStream(System.out));
		String file = "checkpoint";
		in = new BufferedReader( new FileReader(file + ".txt") );
		out = new PrintWriter( new FileOutputStream(file + "_" + System.currentTimeMillis() + ".out") );
		hack(); out.flush();
	}
	static BufferedReader in; static PrintWriter out;
}
