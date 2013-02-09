/**
 * First challenge of Iron Hacker 2011 {@link http://ironhacker.com}.
 * @author Moises Osorio [WCoder]
 */
public class Challenge1 {
	
	/**
	 * Sums all primes p_i | i <= n.
	 * Uses bit operations to reduce the amount of memory needed, by the sieve of Eratosthenes, to n/32.
	 * Time complexity: O(n^1.5).
	 * Memory complexity: O(n).
	 * @param n Sum limit.
	 * @return Sum of all p_i | i <= n.
	 */
	public long sumOfPrimes(final int n) {
		if (n < 2)
			return 0;
		
		int[] sieve = new int[n / 32 + 1]; // Compressed sieve
		for (int i = 4; i <= n; i += 2) // Mark multiples of 2
			sieve[i >> 5] |= 1 << (i & 31); // Mark it is as composite
		
		final int K = (int) Math.sqrt(n);
		for (int i = 3; i <= K; i += 2) { // Avoid even numbers and those above sqrt(n)
			if ((sieve[i >> 5] & (1 << (i & 31))) != 0) // Composite!
				continue;
			
			for (int j = i << 1; j <= n; j += i) // Mark multiples of i starting from i*2
				sieve[j >> 5] |= 1 << (j & 31); // Mark it is as composite
		}
		
		long sum = 2; // We already know n >= 2
		for (int i = 3; i <= n; i += 2) // Avoid even numbers
			if ((sieve[i >> 5] & (1 << (i & 31))) == 0) // It's me, Prime! (Mario voice)
				sum += i;
		
		return sum;
	}
	
	public static void main(String[] args) {
		Challenge1 solver = new Challenge1();
		System.out.println(solver.sumOfPrimes(2000000));
	}
}
