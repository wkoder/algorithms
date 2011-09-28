package anpa;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class SecuenciaKOrdenada {
	
	static final int MAX = 11;
	static int N, K;
	static int[] x;
	static int[] perm;
	static int[] max;
	static boolean[] used = new boolean[MAX];
	
	static int go(int pos) {
		if (pos == N)
			return 0;
		int w = 0;
		for (int i = 0; i < N; i++)
			if (! used[i]) {
				used[i] = true;
				perm[pos] = x[i];
				max[pos] = 0;
				for (int j = 0; j < pos; j++)
					if (perm[j] <= perm[pos])
						max[pos] = Math.max(max[pos], max[j]);
				max[pos]++;
				if (max[pos] == K) {
					int p = 1;
					int l = N - pos - 1;
					while (l > 0)
						p *= l--;
					w += p;
				} else
					w += go(pos+1);
				used[i] = false;
			}
		return w;
	}
	
	static void solve() {
		StringTokenizer tok = new StringTokenizer(read());
		long SS = System.currentTimeMillis();
		N = Integer.parseInt(tok.nextToken());
		K = Integer.parseInt(tok.nextToken());
		tok = new StringTokenizer(read());
		x = new int[N];
		perm = new int[N];
		max = new int[N];
		for (int i = 0; i < N; i++)
			x[i] = Integer.parseInt(tok.nextToken());
		out.println(go(0));
		System.err.println(System.currentTimeMillis()-SS);
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
