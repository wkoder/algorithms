
/**
 * http://projecteuler.net/problem=
 * 
 * @author Moises Osorio
 *
 */
public class P57_SquareRootConvergents {
	
	static final int MAX = 1000001;

	static long gcd(long a, long b) {
		while (b > 0) {
			long c = a % b;
			a = b;
			b = c;
		}
		
		return a;
	}
	
	public static void solve() {
		long n = 0;
		long d = 1;
		
	}

	public static void main(String[] args) {
		final long START = System.currentTimeMillis();
		solve();
		System.err.println("It took " + (System.currentTimeMillis() - START) + " ms to solve");
	}
	
}
