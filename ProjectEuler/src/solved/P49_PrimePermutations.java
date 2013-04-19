package solved;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * http://projecteuler.net/problem=49
 * 
 * @author Moises Osorio
 *
 */
public class P49_PrimePermutations {
	
	static final int MAX = 10000;

	public static void solve() {
		boolean[] isPrime = new boolean[MAX];
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
		for (int i = 2; i < MAX; i++) {
			if (isPrime[i]) {
				if (i >= 1000) {
					char[] c = (i + "").toCharArray();
					Arrays.sort(c);
					String s= new String(c);
					List<Integer> list;
					if (map.containsKey(s)) {
						list = map.get(s);
					} else {
						list = new ArrayList<Integer>();
						map.put(s, list);
					}
					list.add(i);
				}
				
				for (int j = i << 1; j < MAX; j += i)
					isPrime[j] = false;
			}
		}
		for (List<Integer> list : map.values()) {
			if (list.size() < 3)
				continue;
			
			for (int i = 0; i < list.size() - 2; i++) {
				for (int j = i+1; j < list.size() - 1; j++) {
					int a = list.get(i);
					int b = list.get(j);
					int c = b + b - a;
					if (Collections.binarySearch(list, c) >= 0) {
						System.out.println("" + a + b + c);
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		final long START = System.currentTimeMillis();
		solve();
		System.err.println("It took " + (System.currentTimeMillis() - START) + " ms to process");
	}
	
}
