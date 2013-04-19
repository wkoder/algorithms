package solved;

/**
 * http://projecteuler.net/problem=55
 * 
 * @author Moises Osorio
 *
 */
public class P55_LychrelNumbers {
	
	static final int MAX = 10000;
	
	static long reverse(long x) {
		long r = 0;
		while (x > 0) {
			r = r*10 + (x % 10);
			x /= 10;
		}
		return r;
	}
	
	public static void solve() {
		int count = MAX;
		for (long n = 0; n < MAX; n++) {
			long k = n;
			for (int i = 0; i < 50; i++) {
				k += reverse(k);
				if (k == reverse(k)) {
					count--;
					break;
				}
			}
		}
		System.out.println(count);
	}

	public static void main(String[] args) {
		final long START = System.currentTimeMillis();
		solve();
		System.err.println("It took " + (System.currentTimeMillis() - START) + " ms to solve");
	}
	
}
