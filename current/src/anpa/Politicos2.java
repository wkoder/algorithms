package anpa;
import java.io.*;
import java.util.*;

public class Politicos2 {
	
	static int INF = 1 << 28;
	static int ZERO = 1005;
	static int MAX = 2010;
	static int[][] map = new int[MAX][MAX];
	static boolean[][] ob = new boolean[MAX][MAX];
	static int[] DX = {1, 0, -1, 0};
	static int[] DY = {0, 1, 0, -1};
	static long[] W = new long[2];
	static int[] q = new int[MAX*MAX*2];

	static void f(int x, int y, int S) {
		if (map[x][y] < S) {
			long r = S - map[x][y];
			W[map[x][y] & 1] += r/2;
			W[(map[x][y]+1) & 1] += r - r/2;
		}
	}
	
	static void f2(int x, int y, int S) {
		if (map[x][y]+1 < S) {
			long r = S - (map[x][y] + 1);
			long sum = r*(r+1) / 2;
			long lo = (sum - r/2) / 2;
			long hi = sum - lo;
			if (r % 2 == 1) {
				W[(map[x][y]+1) & 1] += lo;
				W[(map[x][y]) & 1] += hi;
			} else {
				W[(map[x][y]+1) & 1] += hi;
				W[(map[x][y]) & 1] += lo;
			}
		}
	}

	static void solve() {
		StringTokenizer tok = new StringTokenizer(read());
//		long ST = System.currentTimeMillis();
		int B = Integer.parseInt(tok.nextToken());
		int S = Integer.parseInt(tok.nextToken());
		int minx = MAX;
		int maxx = 0;
		int miny = MAX;
		int maxy = 0;
		for (int i = 0; i < B; i++) {
			tok = new StringTokenizer(read());
			int x = Integer.parseInt(tok.nextToken()) + ZERO;
			int y = Integer.parseInt(tok.nextToken()) + ZERO;
			ob[x][y] = true;
			minx = Math.min(minx, x-1);
			maxx = Math.max(maxx, x+1);
			miny = Math.min(miny, y-1);
			maxy = Math.max(maxy, y+1);
		}
		for (int i = minx; i <= maxx; i++)
			for (int j = miny; j <= maxy; j++)
				map[i][j] = INF;
		
		map[ZERO][ZERO] = 0;
		int cur = 0;
		int top = 0;
		q[top++] = ZERO;
		q[top++] = ZERO;
		while (cur < top) {
			int x = q[cur++];
			int y = q[cur++];
			
			W[map[x][y] & 1]++;
			if (map[x][y] == S)
				continue;
			
			for (int i = 0; i < DX.length; i++) {
				int xx = x + DX[i];
				int yy = y + DY[i];
				if (xx >= minx && xx <= maxx && yy >= miny && yy <= maxy && !ob[xx][yy] && map[xx][yy] > map[x][y]+1) {
					map[xx][yy] = map[x][y]+1;
					q[top++] = xx;
					q[top++] = yy;
				}
			}
		}
		
		for (int i = minx; i <= maxx; i++) {
			f(i, miny, S);
			f(i, maxy, S);
		}
		for (int i = miny; i <= maxy; i++) {
			f(minx, i, S);
			f(maxx, i, S);
		}
		
		f2(minx, miny, S);
		f2(minx, maxy, S);
		f2(maxx, miny, S);
		f2(maxx, maxy, S);
		
		out.println(W[0] + " " + W[1]);
//		System.err.println(System.currentTimeMillis() - ST);
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
