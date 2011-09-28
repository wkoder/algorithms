package anpa;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class SecuenciaKOrdenada2 {
	
	static int f(int N, int K) {
		int[] x = new int[N];
		for (int i = 0; i < N; i++)
			x[i] = i;
		
		int w = 0;
		do {
			int st = 0;
			for (int i = 0; i < N; i++) {
				if (i > 0 && x[i-1] <= x[i])
					st++;
				else
					st = 1;
				if (st >= K) {
					w++;
					break;
				}
			}
		} while (nextPermutation(x, 0, N));
		
		return w;
	}
	
	static void solve() {
		int MAXN = 10;
		int MAXK = 10;
		int[][] ans = new int[MAXN+1][MAXK+1];
		for (int n = 1; n <= MAXN; n++)
			for (int k = 1; k <= n; k++)
				ans[n][k] = f(n, k);
		System.out.println(Arrays.deepToString(ans).replaceAll("\\[", "{").replaceAll("\\]", "}"));
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
	
	static String read() {
		try { return in.readLine(); } catch (IOException e) { return null; }
	}
	public static void main(String[] args) throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new BufferedOutputStream(System.out));
		solve(); out.flush();
	}
	static BufferedReader in; static PrintWriter out;
}
