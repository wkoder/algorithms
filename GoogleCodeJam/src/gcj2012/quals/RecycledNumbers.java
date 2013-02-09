package gcj2012.quals;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class RecycledNumbers {
	
	static final int INF = 1 << 28, MAX = 1000;
	static final double EPS = 1E-9;
	
	static int digits(int x) {
		int d;
		for (d = 0; x > 0; d++)
			x /= 10;
		return d;
	}
	
	static boolean recycled(int n, int m, int d) {
		int pow = 1;
		for (int i = 0; i < d-1; i++)
			pow *= 10;
		while (d-- > 0) {
			m = m / 10 + pow * (m % 10);
			if (m == n)
				return true;
		}
		
		return false;
	}
	
	static void solve() {
		Scanner scan = new Scanner(in);
		int T = scan.nextInt();
		for (int t = 1; t <= T; t++) {
			out.print("Case #" + t + ": ");
			int A = scan.nextInt();
			int B = scan.nextInt();
			int r = 0;
			for (int n = A; n <= B; n++) {
				int d = digits(n);
				for (int m = n+1; m <= B; m++) {
					if (d != digits(m))
						break;
					if (recycled(n, m, d))
						r++;
				}
			}
			
			out.println(r);
		}
	}
	
	static String read() {
		try { return in.readLine(); } catch (IOException e) { return null; }
	}
	public static void main(String[] args) throws IOException {
//		in = new BufferedReader(new InputStreamReader(System.in));
//		out = new PrintWriter(new BufferedOutputStream(System.out));
		String file = "C-small";
		in = new BufferedReader( new FileReader(file + ".in") );
		out = new PrintWriter( new FileOutputStream(file + "_" + System.currentTimeMillis() + ".out") );
		solve(); out.flush();
	}
	static BufferedReader in; static PrintWriter out;
}
