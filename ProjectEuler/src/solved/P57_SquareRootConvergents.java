package solved;
import java.math.BigInteger;


/**
 * http://projecteuler.net/problem=57
 * 
 * @author Moises Osorio
 *
 */
public class P57_SquareRootConvergents {
	
	static final int MAX = 1000;

	public static void solve() {
		BigInteger n = BigInteger.ZERO;
		BigInteger d = BigInteger.ONE;
		int count = 0;
		for (int i = 0; i < MAX; i++) {
			n = n.add(d.shiftLeft(1));
			BigInteger t = d;
			d = n;
			n = t;
			if (n.add(d).toString().length() > d.toString().length())
				count++;
		}
		System.out.println(count);
	}

	public static void main(String[] args) {
		final long START = System.currentTimeMillis();
		solve();
		System.err.println("It took " + (System.currentTimeMillis() - START) + " ms to solve");
	}
	
}
