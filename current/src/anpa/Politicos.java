package anpa;
import java.util.*;

public class Politicos {
	
	static int INF = 1 << 28;
	static int ZERO = 1005;
	static int MAX = 2010;
	static int[][] map = new int[MAX][MAX];
	static boolean[][] ob = new boolean[MAX][MAX];
	static int[] DX = {1, 0, -1, 0};
	static int[] DY = {0, 1, 0, -1};
	
	static long[] f(int x, int y, int S) {
		long[] w = new long[2];
		if (map[x][y] < S) {
//			w[map[x][y] % 2] = S - map[x][y];
			long r = S - map[x][y];
			w[map[x][y] % 2] = r/2;
			w[(map[x][y]+1) % 2] = r - r/2;
		}
		return w;
	}
	
	static long[] f2(int x, int y, int S) {
		long[] w = new long[2];
		if (map[x][y]+1 < S) {
			long r = S - (map[x][y] + 1);
			long sum = r*(r+1) / 2;
			long lo = (sum - r/2) / 2;
			long hi = sum - lo;
			if (r % 2 == 1) {
				w[(map[x][y]+1) % 2] = lo;
				w[(map[x][y]) % 2] = hi;
			} else {
				w[(map[x][y]+1) % 2] = hi;
				w[(map[x][y]) % 2] = lo;
			}
		}
		return w;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int B = scan.nextInt();
		int S = scan.nextInt();
		for (int i = 0; i < B; i++) {
			int x = scan.nextInt() + ZERO;
			int y = scan.nextInt() + ZERO;
			ob[x][y] = true;
		}
		for (int i = 0; i < MAX; i++)
			Arrays.fill(map[i], INF);
		map[ZERO][ZERO] = 0;
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(ZERO);
		q.add(ZERO);
		while (! q.isEmpty()) {
			int x = q.poll();
			int y = q.poll();
			if (map[x][y] == S)
				break;
			
			for (int i = 0; i < DX.length; i++) {
				int xx = x + DX[i];
				int yy = y + DY[i];
				if (xx >= 0 && xx < MAX && yy >= 0 && yy < MAX && !ob[xx][yy] && map[xx][yy] > map[x][y]+1) {
					map[xx][yy] = map[x][y]+1;
					q.add(xx);
					q.add(yy);
				}
			}
		}
		
		long p = 0;
		long c = 0;
		for (int i = 0; i < MAX; i++)
			for (int j = 0; j < MAX; j++)
				if (map[i][j] <= S) {
					if (map[i][j] % 2 == 0)
						p++;
					else
						c++;
				}
		for (int i = 0; i < MAX; i++) {
			long[] w = f(i, 0, S);
			p += w[0];
			c += w[1];
			
			w = f(i, MAX-1, S);
			p += w[0];
			c += w[1];
			
			w = f(0, i, S);
			p += w[0];
			c += w[1];
			
			w = f(MAX-1, i, S);
			p += w[0];
			c += w[1];
		}
		
		long[] w = f2(0, 0, S);
		p += w[0];
		c += w[1];
		
		w = f2(0, MAX-1, S);
		p += w[0];
		c += w[1];
		
		w = f2(MAX-1, 0, S);
		p += w[0];
		c += w[1];
		
		w = f2(MAX-1, MAX-1, S);
		p += w[0];
		c += w[1];
		
		System.out.println(p + " " + c);
	}
}
