package gcj2010.round1c;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class C_MakingChessBoards {
	
	static final int INF = 1 << 28, MAX = 512;
	static final double EPS = 1E-9;
	
	static void solve() {
		Scanner scan = new Scanner(in);
		int T = scan.nextInt();
		int[][] g = new int[MAX][MAX];
		boolean[][] u = new boolean[MAX][MAX];
		int[][] dp = new int[MAX][MAX];
		int[] count = new int[MAX+1];
		for (int t = 1; t <= T; t++) {
			out.print("Case #" + t + ": ");
			int R = scan.nextInt();
			int C = scan.nextInt();
			for (int i = 0; i < R; i++) {
				Arrays.fill(u[i], false);
				String s = scan.next();
				for (int j = 0; j < C/4; j++) {
					int v;
					if (Character.isDigit(s.charAt(j)))
						v = s.charAt(j) - '0';
					else
						v = s.charAt(j) - 'A' + 10;
					for (int k = 0; k < 4; k++)
						g[i][j*4 + k] = (v >> (3-k)) & 1;
				}
			}
			
			Arrays.fill(count, 0);
			int all = R*C;
			while (all > 0) {
				SortedSet<Pos> set = new TreeSet<Pos>();
				for (int i = 0; i < R; i++)
					for (int j = 0; j < C; j++) {
						if (u[i][j]) {
							dp[i][j] = 0;
							continue;
						}
						
						int l = 1;
						if (i > 0 && j > 0 && !u[i-1][j-1] && !u[i-1][j] && !u[i][j-1] && g[i-1][j-1] == g[i][j] && g[i-1][j] != g[i][j] && g[i][j-1] != g[i][j])
							l = 1 + Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j]));
						
						dp[i][j] = l;
						set.add(new Pos(i-l+1, j-l+1, l));
					}
				
				int maxL = set.first().l;
				while (! set.isEmpty()) {
					Pos pos = set.first();
					if (pos.l < maxL)
						break;
					set.remove(pos);
					
					if (! u[pos.r][pos.c] && !u[pos.r][pos.c+pos.l-1]) {
						all -= pos.l * pos.l;
						for (int i = 0; i < pos.l; i++)
							for (int j = 0; j < pos.l; j++)
								u[pos.r + i][pos.c + j] = true;
						count[pos.l]++;
					}
				}
			}
			
			int tc = 0;
			for (int i = 1; i <= MAX; i++)
				if (count[i] > 0)
					tc++;
			out.println(tc);
			for (int i = MAX; i > 0; i--)
				if (count[i] > 0)
					out.println(i + " " + count[i]);
		}
	}
	
	static String read() {
		try { return in.readLine(); } catch (IOException e) { return null; }
	}
	public static void main(String[] args) throws IOException {
//		in = new BufferedReader(new InputStreamReader(System.in));
//		out = new PrintWriter(new BufferedOutputStream(System.out));
		String file = "C-large";
		in = new BufferedReader( new FileReader(file + ".in") );
		out = new PrintWriter( new FileOutputStream(file + "_" + System.currentTimeMillis() + ".out") );
		solve(); out.flush();
	}
	static BufferedReader in; static PrintWriter out;
	
	static class Pos implements Comparable<Pos> {
		int r, c, l;
		public Pos(int rr, int cc, int ll) {
			r = rr;
			c = cc;
			l = ll;
		}
		public int compareTo(Pos p) {
			if (l != p.l)
				return p.l - l;
			if (r != p.r)
				return r - p.r;
			return c - p.c;
		}
	}
}
