package solved;

/**
 * http://projecteuler.net/problem=63
 * 
 * @author Moises Osorio
 *
 */
public class P63_PowerfulDigitCounts {
	
	static final int MAX = 1000001;

	public static void solve() {
		int count = 0;
		for (int k = 1; k < 10; k++) {
			double logk = Math.log10(k);
			double log = logk;
			for (int n = 1; log >= n-1; n++, count++, log += logk)
				;
		}
		System.out.println(count);
	}

	public static void main(String[] args) {
		final long START = System.currentTimeMillis();
		solve();
		System.err.println("It took " + (System.currentTimeMillis() - START) + " ms to solve");
	}
	
}
