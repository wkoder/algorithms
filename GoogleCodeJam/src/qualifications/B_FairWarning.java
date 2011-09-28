package qualifications;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class B_FairWarning {
	
	static final int INF = 1 << 28, MAX = 1000;
	static final double EPS = 1E-9;
	
	static BigInteger gcd(BigInteger a, BigInteger b) {
		while (! b.equals(BigInteger.ZERO)) {
			BigInteger t = a.mod(b);
			a = b;
			b = t;
		}
		return a;
	}
	
	static void solve() {
		Scanner scan = new Scanner(in);
		int C = scan.nextInt();
		BigInteger[] e = new BigInteger[MAX];
		for (int t = 1; t <= C; t++) {
			out.print("Case #" + t + ": ");
			int N = scan.nextInt();
			BigInteger T = null;
			for (int i = 0; i < N; i++)
				e[i] = scan.nextBigInteger();
			for (int i = 0; i < N; i++)
				for (int j = i+1; j < N; j++) {
					BigInteger diff = e[i].subtract(e[j]).abs();
					if (T == null)
						T = diff;
					else
						T = gcd(T, diff);
				}
			BigInteger Y = T.subtract(e[0].mod(T)).mod(T);
			out.println(Y);
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
