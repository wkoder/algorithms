package solved;

/**
 * http://projecteuler.net/problem=43
 * 
 * @author Moises Osorio
 *
 */
public class P43_SubstringDivisibility {
	
	static final int MAX = 1000001;

	static boolean nextPermutation(int[] arr, int first, int last) {
        int i, ii, j;
        int t;
        if (first == last || first + 1 == last)
            return false;
        i = last - 1;
        for(;;) {
            ii = i--;
            if (arr[i] < arr[ii]) {
                j = last;
                while (arr[i] >= arr[--j]) ;
                t = arr[i]; // swap
                arr[i] = arr[j];
                arr[j] = t;
                last--; // reverse
                for (;;) {
                    if (ii >= last)
                        break;
                    t = arr[ii];
                    arr[ii] = arr[last];
                    arr[last] = t;
                    ii++;
                    last--;
                }
                return true;
            }
            if (i == first) {
                last--; // reverse
                for (;;) {
                    if (first >= last)
                        break;
                    t = arr[first];
                    arr[first] = arr[last];
                    arr[last] = t;
                    first++;
                    last--;
                }
                return false;
            }
        }
    }
	
	public static void solve() {
		int[] perm = new int[10];
		for (int i = 0; i < perm.length; i++)
			perm[i] = i;
		int[] primes = {2, 3, 5, 7, 11, 13, 17};
		long sum = 0;
		do {
			boolean ok = true;
			for (int i = 0; i < primes.length && ok; i++) {
				int k = perm[i+1]*100 + perm[i+2]*10 + perm[i+3];
				ok &= k % primes[i] == 0;
			}
			if (ok) {
				long n = 0;
				for (int i = 0; i < perm.length; i++)
					n = n*10 + perm[i];
				sum += n;
			}
		} while (nextPermutation(perm, 0, perm.length));
		
		System.out.println(sum);
	}

	public static void main(String[] args) {
		final long START = System.currentTimeMillis();
		solve();
		System.err.println("It took " + (System.currentTimeMillis() - START) + " ms to solve");
	}
	
}
