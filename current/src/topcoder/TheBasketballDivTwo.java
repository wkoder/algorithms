package topcoder;
public class TheBasketballDivTwo {
	char[][] m;
	int N;
	int[] p;
	int go(int r, int c) {
		if (r == N) {
			int max = 0;
			for (int i = 0; i < N; i++)
				max = Math.max(max, p[i]);
			return max;
		}
		if (r == c)
			return go(r, c+1);
		if (c == N)
			return go(r+1, 0);
		
		int min = 1 << 28;
		if (m[r][c] != 'L') {
			p[r]++;
			min = Math.min(min, go(r, c+1));
			p[r]--;
		}
		if (m[r][c] != 'W') {
			p[c]++;
			min = Math.min(min, go(r, c+1));
			p[c]--;
		}
		return min;
	}
	public int find(String[] table) {
		N = table.length;
		m = new char[N][];
		for (int i = 0; i < N; i++)
			m[i] = table[i].toCharArray();
		p = new int[N];
		return go(0, 0);
	}
}
