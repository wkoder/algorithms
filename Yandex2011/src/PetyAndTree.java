import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class PetyAndTree {
	
	static final int MAX = 100100;
	static int[] par = new int[MAX];
	static int[] val = new int[MAX];
	static int[] L = new int[MAX];
	static int[] R = new int[MAX];
	
	static int level(int p, int k) {
		if (L[p] == -1 || R[p] == -1)
			return 0;
		if (val[p] < k)
			return level(R[p], k) + 1;
		return level(L[p], k) + 1;
	}
	
	static double f(int p, int k, boolean ok) {
		if (L[p] == -1 || R[p] == -1) {
//			System.out.println("End in " + p + " with " + val[p]);
			return ok? 0 : val[p];
		}
		
		double v = 0;
		if (val[p] < k) {
			if (ok) {
				v += f(R[p], k, true);
				v += f(L[p], k, false);
			} else
				v += f(R[p], k, false);
		} else {
			if (ok) {
				v += f(L[p], k, true);
				v += f(R[p], k, false);
			} else
				v += f(L[p], k, false);
		}

		return v;
	}
	
	static void solve() {
		int N = Integer.parseInt(read());
		int root = -1;
		Arrays.fill(L, -1);
		Arrays.fill(R, -1);
		for (int i = 0; i < N; i++) {
			StringTokenizer tok = new StringTokenizer(read());
			int p = Integer.parseInt(tok.nextToken()) - 1;
			par[i] = p;
			val[i] = Integer.parseInt(tok.nextToken());
			if (p < 0)
				root = i;
		}
		
		for (int i = 0; i < N; i++) {
			int p = par[i];
			if (p < 0)
				continue;
			
			if (L[p] == -1)
				L[p] = i;
			else {
				if (val[p] < val[i])
					R[p] = i;
				else {
					R[p] = L[p];
					L[p] = i;
				}
			}
		}
		
		int K = Integer.parseInt(read());
		while (K-- > 0) {
			int key = Integer.parseInt(read());
//			System.out.println(f(root, key, true) + " " + level(root, key));
			System.out.println(f(root, key, true) / level(root, key));
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
