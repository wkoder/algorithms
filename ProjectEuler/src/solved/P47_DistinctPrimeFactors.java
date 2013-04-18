package solved;
import java.util.Arrays;


/**
 * http://projecteuler.net/problem=47
 * 
 * @author Moises Osorio
 *
 */
public class P47_DistinctPrimeFactors {
	
	static final int MAX = 1000001;

	public static void solve() {
		final int N = 4;
		boolean[] isPrime = new boolean[MAX];
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		int[] primes = new int[MAX];
		int np = 0;
		for (int i = 2; i < MAX; i++) {
			if (isPrime[i]) {
				primes[np++] = i;
				for (int j = i << 1; j < MAX; j += i) {
					isPrime[j] = false;
				}
			}
		}
		
		int[] dp = new int[MAX];
		search:
		for (int i = 2; i < MAX; i++) {
			if (isPrime[i]) {
				dp[i] = 1;
				continue;
			}
			
			for (int j = 0; j < np && primes[j]*primes[j] <= i; j++)
				if (i % primes[j] == 0) {
					int k = i / primes[j];
					dp[i] = dp[k] + (k % primes[j] == 0 ? 0 : 1);
					break;
				}
			
			if (i >= N && dp[i] == N) {
				int r = i - 1;
				while (dp[r] == N)
					r--;
				if (i-r == N) {
					System.out.println(r+1);
					break search;
				}
			}
		}
	}

	public static void main(String[] args) {
		final long START = System.currentTimeMillis();
		solve();
		System.err.println("It took " + (System.currentTimeMillis() - START) + " ms to solve");
	}
	
}
