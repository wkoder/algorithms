package gcj2010.round2b;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class B_TideGoesInTideGoesOut {
	
	static final int INF = 1 << 28, MAX = 1000;
	static final double EPS = 1E-9;
	
	static void solve() {
		final int[] DX = {1, -1, 0, 0};
		final int[] DY = {0, 0, 1, -1};
		final int MINH = 50;
		
		Scanner scan = new Scanner(in);
		int T = scan.nextInt();
		for (int t = 1; t <= T; t++) {
			out.print("Case #" + t + ": ");
			int H = scan.nextInt();
			int R = scan.nextInt();
			int C = scan.nextInt();
			int[][] ceiling = new int[R][C];
			int[][] floor = new int[R][C];
			for (int i = 0; i < R; i++)
				for (int j = 0; j < C; j++)
					ceiling[i][j] = scan.nextInt();
			for (int i = 0; i < R; i++)
				for (int j = 0; j < C; j++)
					floor[i][j] = scan.nextInt();
			
			TreeSet<State> states = new TreeSet<State>();
			states.add(new State(0, 0, 0));
			double[][] min = new double[R][C];
			for (int i = 0; i < R; i++)
				Arrays.fill(min[i], Double.MAX_VALUE);
			min[0][0] = 0;
			
			while (!states.isEmpty()) {
				State s = states.first();
				states.remove(s);
				for (int k = 0; k < DX.length; k++) {
					int xx = s.x + DX[k];
					int yy = s.y + DY[k];
					if (xx < 0 || xx >= R || yy < 0 || yy >= C)
						continue;
					
					if (floor[s.x][s.y] > ceiling[xx][yy] - MINH || floor[xx][yy] > ceiling[xx][yy] - MINH || floor[xx][yy] > ceiling[s.x][s.y] - MINH)
						continue;
					
					double currentH = Math.max(0, H - 10*s.cost);
					double wait = Math.max(0, (currentH - (ceiling[xx][yy] - MINH)) / 10);
					double nextH = Math.max(0, currentH - 10*wait);
					double cost = s.cost + wait;
//					System.out.println(H + " " + currentH + " " + nextH); 
					if (nextH-floor[s.x][s.y] > 20-EPS)
						cost += 1;
					else
						cost += 10;
					
					if (wait < EPS && s.cost < EPS)
						cost = 0;
					
					if (cost > min[xx][yy]-EPS)
						continue;
					
					min[xx][yy] = cost;
					State s2 = new State(xx, yy, cost);
					states.add(s2);
				}
			}
			
			out.println(min[R-1][C-1]);
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
	
	static class State implements Comparable<State> {
		int x, y;
		double cost;
		public State(int xx, int yy, double ccost) {
			x = xx;
			y = yy;
			cost = ccost;
		}
		@Override
		public int compareTo(State s) {
			int cmp = Double.compare(cost, s.cost);
			if (cmp != 0)
				return cmp;
			if (x != s.x)
				return x - s.x;
			return y - s.y;
		}
	}
}
