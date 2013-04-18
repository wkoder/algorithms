
/**
 * http://projecteuler.net/problem=63
 * 
 * @author Moises Osorio
 *
 */
public class P63_PowerfulDigitCounts {
	
	static final int MAX = 1000001;

	public static void solve() {
		for (int k = 1; k < 10; k++) {
			long kn = 1;
			for (int n = 1; n < 10; n++) {
				kn *= k;
				
			}
		}
	}

	public static void main(String[] args) {
		final long START = System.currentTimeMillis();
		solve();
		System.err.println("It took " + (System.currentTimeMillis() - START) + " ms to solve");
	}
	
}
