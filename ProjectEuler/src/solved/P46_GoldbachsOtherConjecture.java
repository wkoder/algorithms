package solved;
import java.util.Arrays;


/**
 * http://projecteuler.net/problem=
 * 
 * @author Moises Osorio
 *
 */
public class P46_GoldbachsOtherConjecture {
	
	static final int MAX = 1000001;

	public static void solve() {
		boolean[] isPrime = new boolean[MAX];
		int[] primes = new int[MAX];
		boolean[] is2Square = new boolean[MAX];
		int np = 0;
		
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		for (int i = 2; i < MAX; i++)
			if (isPrime[i]) {
				primes[np++] = i;
				for (int j = i << 1; j < MAX; j += i)
					isPrime[j] = false;
			}
		
		for (int i = 1; 2*i*i < MAX; i++)
			is2Square[2*i*i] = true;
		
		for (int n = 3; n < MAX; n += 2)
			if (!isPrime[n]) {
				boolean ok = false;
				for (int j = 0; j < np && n > primes[j] && !ok; j++) {
					ok = is2Square[n - primes[j]];
				}
				if (!ok) {
					System.out.println(n);
					break;
				}
			}
	}

	public static void main(String[] args) {
		final long START = System.currentTimeMillis();
		solve();
		System.err.println("It took " + (System.currentTimeMillis() - START) + " ms to solve");
	}
	
}
