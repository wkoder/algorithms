package gcj2010.round1a;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class B_MakeItSmooth {
	
	static final int INF = 1 << 28, MAX = 300;
	static final double EPS = 1E-9;
	static final int ANY = 299;
	
	static int D, I, M, N;
	static int[][] cost = new int[MAX][MAX];
	static int[] bytes = new int[MAX];
	
	static int go() {
		for (int i = 0; i < MAX; i++)
			Arrays.fill(cost[i], INF);
		
		cost[ANY][0] = 0;
		SortedSet<V> set = new TreeSet<V>();
		set.add(new V(ANY, 0, 0));
		while (! set.isEmpty()) {
			V a = set.first();
			set.remove(a);
			if (a.pos == N)
				return a.cost;
			
			for (int nextValue = 0; nextValue <= 255; nextValue++)
				if (a.value == ANY || Math.abs(nextValue - a.value) <= M) {
					int ncost = a.cost + Math.abs(nextValue - bytes[a.pos]);
					int currCost = cost[nextValue][a.pos+1];
					if (currCost > ncost) {
						V b = new V(nextValue, a.pos+1, currCost);
						set.remove(b);
						b.cost = cost[b.value][b.pos] = ncost;
						set.add(b);
					}
					
					ncost = a.cost + I;
					currCost = cost[nextValue][a.pos];
					if (currCost > ncost) {
						V b = new V(nextValue, a.pos, currCost);
						set.remove(b);
						b.cost = cost[b.value][b.pos] = ncost;
						set.add(b);
					}
				}
			
			int ncost = a.cost + D;
			int currCost = cost[a.value][a.pos+1];
			if (currCost > ncost) {
				V b = new V(a.value, a.pos+1, currCost);
				set.remove(b);
				b.cost = cost[b.value][b.pos] = ncost;
				set.add(b);
			}
		}
		System.err.println("Whuttttttt? =/");
		return -1;
	}
	
	static void solve() {
		Scanner scan = new Scanner(in);
		int T = scan.nextInt();
		for (int t = 1; t <= T; t++) {
			out.print("Case #" + t + ": ");
			
			D = scan.nextInt();
			I = scan.nextInt();
			M = scan.nextInt();
			N = scan.nextInt();
			for (int i = 0; i < N; i++)
				bytes[i] = scan.nextInt();
			
			out.println(go());
		}
	}
	
	static String read() {
		try { return in.readLine(); } catch (IOException e) { return null; }
	}
	public static void main(String[] args) throws IOException {
//		in = new BufferedReader(new InputStreamReader(System.in));
//		out = new PrintWriter(new BufferedOutputStream(System.out));
		String file = "B-large";
		in = new BufferedReader( new FileReader(file + ".in") );
		out = new PrintWriter( new FileOutputStream(file + "_" + System.currentTimeMillis() + ".out") );
		solve(); out.flush();
	}
	static BufferedReader in; static PrintWriter out;
	
	static class V implements Comparable<V> {
		int value, pos, cost;
		public V(int value, int pos, int cost) {
			this.value = value;
			this.pos = pos;
			this.cost = cost;
		}
		public int compareTo(V v) {
			if (cost != v.cost)
				return cost - v.cost;
			if (pos != v.pos)
				return pos - v.pos;
			return value - v.value;
		}
	}
}
