package topcoder.tco12.qual3;
public class PasswordXGuessing {
	boolean ok(int[] a, int[] b) {
		int d = 0;
		for (int i = 0; i < a.length; i++)
			if (a[i] != b[i] && ++d > 1)
				return false;
		return d == 1;
	}
	
	public long howMany(String[] guesses) {
		int N = guesses.length;
		int X = guesses[0].length();
		int[][] g = new int[N][X];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < X; j++)
				g[i][j] = guesses[i].charAt(j) - '0';
		
		long ways = 0;
		for (int k = 0; k < X; k++) {
			int old = g[0][k];
			for (int x = 0; x < 10; x++)
				if (x != old) {
					g[0][k] = x;
					boolean ok = true;
					for (int i = 1; i < N && ok; i++)
						ok &= ok(g[0], g[i]);
					if (ok)
						ways++;
				}
			g[0][k] = old;
		}
		
		return ways;
	}
}
