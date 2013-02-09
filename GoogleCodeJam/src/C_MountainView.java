import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class C_MountainView {
	
	static final int INF = 1 << 28, MAX = 1000;
	static final double EPS = 1E-9;
	
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
	
	static boolean ok(int[] high, int[] H, int N) {
		for (int i = 0; i < N-1; i++) {
			int diffih = high[H[i]] - high[i];
			for (int j = i+1; j < N; j++) {
				if (H[i] == j)
					continue;
				int highij = (H[i] - i) * (high[j] - high[i]);
				int highih = (j - i) * diffih;
				if (j < H[i] && highij >= highih)
					return false;
				if (j > H[i] && highij > highih)
					return false;
			}
		}
		
		return true;
	}
	
	static void solve() {
		Scanner scan = new Scanner(in);
		int T = scan.nextInt();
		Random random = new Random();
		for (int t = 1; t <= T; t++) {
			out.print("Case #" + t + ":");
			int N = scan.nextInt();
			int[] H = new int[N];
			int[] high = new int[N];
			for (int i = 0; i < N-1; i++)
				H[i] = scan.nextInt()-1;
			int[] order = new int[N];
			for (int i = 0; i < N; i++)
				order[i] = i;
			
			boolean ok = false;
			for (int trial = 0; trial < 36288000 && !ok; trial++) {
				for (int i = 0; i < N; i++)
					high[i] = random.nextInt(1000);
				ok = ok(high, H, N);
			}
//			do {
//				for (int trial = 0; trial < 100 && !ok; trial++) {
//					high[0] = random.nextInt(10000) + 1;
//					for (int i = 1; i < N; i++)
//						high[order[i]] = high[order[i-1]] - random.nextInt(100);
//					ok = ok(high, H, N);
//				}
//			} while (!ok && nextPermutation(order, 0, N));
			
			if (!ok)
				out.println(" Impossible");
			else {
				for (int i = 0; i < N; i++)
					out.print(" " + high[i]);
				out.println();
			}
		}
	}
	
	static String read() {
		try { return in.readLine(); } catch (IOException e) { return null; }
	}
	public static void main(String[] args) throws IOException {
//		in = new BufferedReader(new InputStreamReader(System.in));
//		out = new PrintWriter(new BufferedOutputStream(System.out));
		String file = "C-small";
		in = new BufferedReader( new FileReader(file + ".in") );
		out = new PrintWriter( new FileOutputStream(file + "_" + System.currentTimeMillis() + ".out") );
		solve(); out.flush();
	}
	static BufferedReader in; static PrintWriter out;
}
