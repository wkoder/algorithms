package topcoder;
import java.util.Arrays;

public class MatrixGame {
	public String[] getMinimal(String[] matrix) {
		int R = matrix.length;
		int C = matrix[0].length();
		char[][] m = new char[R][];
		for (int i = 0; i < R; i++)
			m[i] = matrix[i].toCharArray();

		String[] best = new String[R];
		String[] cur = new String[R];
		char[] row = new char[C];
		
		int[] p = new int[C];
		for (int i = 0; i < C; i++)
			p[i] = i;
		for (int i = 0; i < R; i++)
			best[i] = "2";
		
		do {
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++)
					row[ p[j] ] = m[i][j];
				cur[i] = new String(row);
			}
			Arrays.sort(cur);
			
			boolean b = false;
			for (int i = 0; i < R; i++) {
				if (! b) {
					int c = best[i].compareTo(cur[i]);
					if (c > 0)
						b = true;
					else if (c < 0)
						break;
				}
			}
			if (b)
				for (int i = 0; i < R; i++)
					best[i] = cur[i];
		} while (nextPermutation(p, 0, C));
		
		return best;
	}
	
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
}
