package solved;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * http://projecteuler.net/problem=51
 * 
 * @author Moises Osorio
 *
 */
public class P51_PrimeDigitReplacements {
	
	public static void solve() {
		final int MAXD = 7;
		int[] dmin = new int[MAXD];
		dmin[0] = 1;
		for (int i = 1; i < MAXD; i++)
			dmin[i] = dmin[i-1] * 10;
		int MAX = dmin[MAXD - 1];
		boolean[] isCompo = new boolean[MAX];
		int[] n = new int[MAXD];
		

		isCompo[0] = isCompo[1] = true;
		for (int i = 4; i < MAX; i += 2)
			isCompo[i] = true;
		
		@SuppressWarnings("unchecked")
		Map<Integer, List<Integer>>[] families = new Map[1 << MAXD];
		
		for (int k = 3, D = 0; k < MAX; k += 2) {
			if (k >= dmin[D]) {
				D++;
				for (int i = 0; i < 1<<D; i++)
					families[i] = new HashMap<>();
			}
			if (!isCompo[k]) {
				for (int i = 0, kk = k; i < D; i++, kk /= 10)
					n[i] = kk % 10;
				next:
				for (int mask = 1; mask < 1 << D; mask += 2) {
					int digit = -1;
					for (int i = 0; i < D; i++)
						if ((mask & (1 << i)) == 0)
							if (digit == -1)
								digit = n[i];
							else if (digit != n[i])
								continue next; // Not valid
					
					int km = 0;
					for (int i = D-1; i >= 0; i--)
						km = km*10 + ((mask & (1 << i)) == 0 ? 0 : n[i]);
					List<Integer> family = families[mask].get(km);
					if (family == null) {
						family = new ArrayList<>();
						families[mask].put(km, family);
					}
					family.add(k);
					if (family.size() >= 8) {
						System.out.println(family);
					}
				}
				
				for (int i = k << 1; i < MAX; i += k)
					isCompo[i] = true;
			}
		}
	}

	public static void main(String[] args) {
		final long START = System.currentTimeMillis();
		solve();
		System.err.println("It took " + (System.currentTimeMillis() - START) + " ms to solve");
	}
	
}
