package solved;


/**
 * http://projecteuler.net/problem=33
 * 
 * @author Moises Osorio
 *
 */
public class P33_DigitCancelingFunctions {
	
	static final int MAX = 100;
	
	static long gcd(long a, long b) {
		while (b > 0) {
			long c = a % b;
			a = b;
			b = c;
		}
		
		return a;
	}
	
	public static void solve() {
		long nn = 1;
		long dd = 1;
		for (int a = 1; a < 10; a++)
			for (int b = 1; b < 10; b++)
				for (int c = 0; c < 10; c++) {
					if (a == b && b == c)
						continue;
					int n = a*10 + b;
					int d = b*10 + c;
					if (n*c == d*a) {
						nn *= n;
						dd *= d;
					}
				}
		System.out.println(dd / gcd(nn, dd));
	}

	public static void main(String[] args) {
		final long START = System.currentTimeMillis();
		solve();
		System.err.println("It took " + (System.currentTimeMillis() - START) + " ms to process");
	}
	
}
