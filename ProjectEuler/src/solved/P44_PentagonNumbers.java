package solved;
import java.util.HashSet;
import java.util.Set;


/**
 * http://projecteuler.net/problem=44
 * 
 * @author Moises Osorio
 *
 */
public class P44_PentagonNumbers {
	
	static final int MAX = 10001;

	public static void solve() {
		long[] penta = new long[MAX];
		Set<Long> isPenta = new HashSet<Long>();
		penta[0] = 1;
		for (int n = 1; n < MAX; n++) {
			penta[n] = n * (3L*n - 1L) / 2;
			isPenta.add(penta[n]);
		}
		
		long bestD = 1L << 60;
		for (int i = 0; i < MAX; i++)
			for (int j = i+1; j < MAX && penta[j] - penta[i] < bestD; j++) {
				if (isPenta.contains(penta[j] - penta[i]) && isPenta.contains(penta[i] + penta[j])) {
					bestD = penta[j] - penta[i];
				}
			}
		System.out.println(bestD);
	}

	public static void main(String[] args) {
		final long START = System.currentTimeMillis();
		solve();
		System.err.println("It took " + (System.currentTimeMillis() - START) + " ms to solve");
	}
	
}
