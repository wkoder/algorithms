package solved;
import java.util.HashMap;
import java.util.Map;


/**
 * http://projecteuler.net/problem=26
 * 
 * @author Moises Osorio
 *
 */
public class P26_RecyprocalCycles {
	
	static final int MAX = 1001;

	public static void solve() {
		int max = 0;
		int best = -1;
		for (int n = 1; n < MAX; n++) {
			Map<Long, Integer> visited = new HashMap<Long, Integer>();
			long mod = 1;
			int p = 0;
			while (mod > 0) {
				p++;
				while (mod < n) {
					mod *= 10;
				}
				mod %= n;
				if (visited.containsKey(mod)) {
					int cycle = p - visited.get(mod);
//					System.out.println(n + ": " + cycle);
					if (cycle > max) {
						max = cycle;
						best = n;
					}
					break;
				} else {
					visited.put(mod, p);
				}
			}
		}
		
		System.out.println(best);
	}

	public static void main(String[] args) {
		final long START = System.currentTimeMillis();
		solve();
		System.err.println("It took " + (System.currentTimeMillis() - START) + " ms to process");
	}
	
}
