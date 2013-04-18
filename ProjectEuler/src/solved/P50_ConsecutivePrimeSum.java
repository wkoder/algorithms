package solved;
import java.util.Arrays;

/**
 * http://projecteuler.net/problem=50
 * 
 * @author Moises Osorio
 *
 */
public class P50_ConsecutivePrimeSum {

	public static void main(String[] args) {
		final long START = System.currentTimeMillis();
		final int MAX = 1000001;
		boolean[] isPrime = new boolean[MAX];
		int[] primes = new int[MAX];
		int np = 0;
		
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		for (int i = 2; i < MAX; i++) {
			if (isPrime[i]) {
				primes[np++] = i;
				for (int k = i << 1; k < MAX; k += i) {
					isPrime[k] = false;
				}
			}
		}
		
		int best = -1;
		int max = 1;
		for (int i = 1; i < np; i++) {
			int sum = 0;
			int a = 0;
			int b = 0;
			int p = primes[i];
			while (b < i && sum != p) {
				sum += primes[b];
				while (sum > p) {
					sum -= primes[a++];
				}
				b++;
			}
			
			if (sum == p && b-a > max) {
				best = primes[i];
				max = b - a;
			}
		}
		
		System.out.println(best);
		System.err.println("It took " + (System.currentTimeMillis() - START) + " ms to process");
	}
	
}
