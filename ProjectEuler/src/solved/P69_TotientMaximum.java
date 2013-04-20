package solved;
import java.util.Arrays;


/**
 * http://projecteuler.net/problem=69
 * 
 * @author Moises Osorio
 *
 */
public class P69_TotientMaximum {
	
	static final int MAX = 1000001;

	public static void solve() {
		final int MAXN = 1001;
		int np = 0;
		int[] primes = new int[MAXN];
		boolean[] sieve = new boolean[MAXN];
		Arrays.fill(sieve, true);
		primes[np++] = 2;
		for (int i = 4; i < MAXN; i += 2)
			sieve[i] = false;
		for (int i = 3; i < MAXN; i += 2)
			if (sieve[i]) {
				primes[np++] = i;
				for (int j = i << 1; j < MAXN; j += j)
					sieve[j] = false;
			}
		
		int[] totient = new int[MAX];
		double max = 0;
		int best = -1;
		totient[1] = 1;
		for (int n = 2; n < MAX; n++) {
			totient[n] = n - 1; // Is prime?
			for (int j = 0; j < np && primes[j]*primes[j] <= n; j++)
				if (n % primes[j] == 0) { // Not prime
					int nn = n;
					while (nn % primes[j] == 0)
						nn /= primes[j];
					
					if (nn == 1) // Multiple of a prime
						totient[n] = n - n/primes[j];
					else
						totient[n] = totient[nn] * totient[n / nn];
					
					break;
				}
			double value = n / (double)totient[n];
			if (value > max) {
				max = value;
				best = n;
			}
		}
		System.out.println(best);
	}

	public static void main(String[] args) {
		final long START = System.currentTimeMillis();
		solve();
		System.err.println("It took " + (System.currentTimeMillis() - START) + " ms to solve");
	}
	
}
