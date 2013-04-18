package y2013.qual;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class CopyOfFindTheMin {
	
	public static void solve() {
		int cases = scan.nextInt();
		for (int caze = 1; caze <= cases; caze++) {
			out.print("Case #" + caze + ": ");
			int n = scan.nextInt();
			int k = scan.nextInt();
			int a = scan.nextInt();
			long b = scan.nextInt();
			int c = scan.nextInt();
			int r = scan.nextInt();
			int[] m = new int[k];
			m[0] = a;
			for (int i = 1; i < k; i++)
				m[i] = (int)((b * m[i - 1] + c) % r);
			
			long[] res = new long[k*100];
			for (int i = 0; i < k; i++)
				res[i] = m[i];
			for (int i = k; i < res.length; i++) {
				long value;
				next:
				for (value = 0; value < k; value++) {
					for (int j = 1; j <= k; j++)
						if (value == res[i-j])
							continue next;
					break;
				}
				res[i] = value;
			}
//			System.out.println(Arrays.toString(res));
			out.println(res[n-1]);
			
//			for (int i = k; i < res.length; i++)
//				if (res[i] != res[k + ((i-k) % (k+1))]) {
//					System.err.println(i + ": " + res[i] + " != " + res[k + (i % (k+1))]);
//				}
			
			int[] count = new int[k+1];
			for (int i = 0; i < k; i++)
				if (m[i] <= k)
					count[m[i]]++;
			SortedSet<Integer> avail = new TreeSet<Integer>();
			for (int i = 0; i <= k; i++)
				if (count[i] == 0)
					avail.add(i);
			int[] res2 = new int[k+1];
			for (int i = 0; i <= k; i++) {
				res2[i] = avail.first();
				avail.remove(res2[i]);
				if (i < k && m[i] <= k && --count[m[i]] == 0)
					avail.add(m[i]);
			}
			
			out.println(res2[(n-1 - k) % (k+1)]);
		}
	}
	
	public static void main(String[] args) throws IOException {
		scan = new Scanner(new InputStreamReader(System.in));
		//scan = new Scanner(new File(".txt"));
		out = new PrintWriter(new BufferedOutputStream(System.out));
		//out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(".out")));
		solve(); out.flush();
	}
	static Scanner scan; static PrintWriter out;
}
