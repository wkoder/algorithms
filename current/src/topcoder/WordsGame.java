package topcoder;
public class WordsGame {
	final int INF = 1 << 28;
	int f(char[] a, char[] b) {
		int N = a.length;
		int m = 0;
		for (int i = 0; i < N; i++)
			if (a[i] != b[i]) {
				int pos;
				for (pos = i+1; pos < N; pos++)
					if (a[i] == b[pos])
						break;
				if (pos == N)
					return INF;
				m++;
				b[pos] = b[i];
			}
		return m;
	}
	public int minimumSwaps(String[] grid, String word) {
		int N = word.length();
		int min = INF;
		char[] w = word.toCharArray();
		for (int i = 0; i < N; i++)
			min = Math.min(min, f(w, grid[i].toCharArray()));
		for (int i = 0; i < N; i++) {
			char[] c = new char[N];
			for (int j = 0; j < N; j++)
				c[j] = grid[j].charAt(i);
			min = Math.min(min, f(w, c));
		}
		return min < INF? min : -1;
	}
}
