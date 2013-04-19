package solved;
import java.util.Arrays;


/**
 * http://projecteuler.net/problem=58
 * 
 * @author Moises Osorio
 *
 */
public class P58_SpiralPrimes {
	
	static final int MAX = 1000000001;

	public static void solve() {
		boolean[] isPrime = new boolean[MAX];
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		for (int i = 4; i < MAX; i += 2)
			isPrime[i] = false;
		for (int i = 3; i < MAX; i += 2)
			if (isPrime[i] && MAX / i > i)
				for (int j = i * i; j < MAX; j += i)
					isPrime[j] = false;
		
		int n = 1;
		int l = 0;
		int count = 0;
		int all = 1;
		while (true) {
			l += 2;
			for (int i = 0; i < 4; i++, n += l)
				if (isPrime[n])
					count++;
			all += 4;
			if (count*10 < all) {
				System.out.println(l + 1);
//				System.out.println(l + ": " + count + "/" + all);
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
