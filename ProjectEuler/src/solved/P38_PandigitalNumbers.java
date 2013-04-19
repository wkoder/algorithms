package solved;

/**
 * http://projecteuler.net/problem=38
 * 
 * @author Moises Osorio
 *
 */
public class P38_PandigitalNumbers {
	
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
		int[] perm = new int[9];
		int[] pan = new int[9];
		for (int i = 0; i < perm.length; i++)
			perm[i] = i;
		search:
		do {
			for (int i = 0; i < pan.length; i++)
				pan[i] = 9 - perm[i];
			
			int k = 0;
			for (int i = 0; i < pan.length-1; i++) {
				k = k*10 + pan[i];
				int j = i + 1;
				boolean ok = true;
				for (int n = 2; ok && j < pan.length; n++) {
					int kn = 0;
					while (j < pan.length && kn != k*n) {
						kn = kn*10 + pan[j++];
					}
					ok &= kn == k*n;
				}
				if (ok) {
					for (int d : pan)
						System.out.print(d);
					System.out.println();
					break search;
				}
			}
		} while (nextPermutation(perm, 0, perm.length));
	}

	public static void main(String[] args) {
		final long START = System.currentTimeMillis();
		solve();
		System.err.println("It took " + (System.currentTimeMillis() - START) + " ms to process");
	}
	
}
