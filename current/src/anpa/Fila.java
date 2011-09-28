package anpa;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class Fila {
	
	static void solve() {
		int N = Integer.parseInt(read());
		H[] h = new H[N];
		for (int i = 0; i < N; i++)
			h[i] = new H(i, Integer.parseInt(read()));
		Arrays.sort(h);
		SegmentTree tree = new SegmentTree(N);
		long pairs = 0;
		for (int n = 0; n < N; n++) {
			int high = h[n].h;
			int n2 = n+1;
			while (n2 < N && h[n2].h == high && tree.getCount(h[n].i, h[n2].i) == 0)
				n2++;
			
			long x = n2 - n;
			pairs += x * (x-1) / 2;
			if (tree.getCount(0, h[n].i) > 0)
				pairs += x;
			if (tree.getCount(h[n2-1].i, N) > 0)
				pairs += x;
			
			for (int i = n; i < n2; i++)
				tree.add(h[i].i, 1);
			
			n = n2-1;
		}
		out.println(pairs);
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

	static class H implements Comparable<H> {
		int i, h;
		public H(int ii, int hh) {
			i = ii;
			h = hh;
		}
		public int compareTo(H o) {
			if (h != o.h)
				return o.h - h;
			return i - o.i;
		}
	}
	
	static class SegmentTree {
		int N;
		int[] t;
		SegmentTree(int size) {
			N = 1;
			while (N < size)
				N <<= 1;
			t = new int[2 * N - 1];
		}
		void add(int pos, int n) {
			pos += N;
			while (pos > 0) {
				t[pos] += n;
				pos >>= 1;
			}
	    }
		int getCount(int l, int r) {
			l += N;
			r += N;
			if (r >= t.length)
				r = t.length - 1;
			int ans = 0;
			while (l <= r) {
				if (l == r) {
					ans += t[l];
					break;
				}
				if ((l & 1) == 1)
					ans += t[l++];
				if ((r & 1) == 0)
					ans += t[r--];
				l >>= 1;
				r >>= 1;
			}
			return ans;
		}
	}

}
