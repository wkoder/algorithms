package solved;
import java.util.Arrays;


/**
 * http://projecteuler.net/problem=92
 * 
 * @author Moises Osorio
 *
 */
public class P92_SquareDigitChains {
	
	static final int MAX = 10000000;
	static final int MAXD = 568;
	static int[] memo = new int[MAXD];
	
	static int f(int n) {
		int sq = 0;
		int nn = n;
		while (nn > 0) {
			int d = nn % 10;
			sq += d*d;
			nn /= 10;
		}
		
		return sq;
	}
	
	static int go(int n) {
		if (n == 1 || n == 89)
			return n;
		if (memo[n] >= 0)
			return memo[n];
		return memo[n] = go(f(n));
	}
	
	public static void solve() {
		Arrays.fill(memo, -1);
		int count = 0;
		for (int n = 1; n < MAX; n++)
			if (go(f(n)) == 89)
				count++;
		System.out.println(count);
	}

	public static void main(String[] args) {
		final long START = System.currentTimeMillis();
		solve();
		System.err.println("It took " + (System.currentTimeMillis() - START) + " ms to solve");
	}
	
}
