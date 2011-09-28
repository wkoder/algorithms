package topcoder;
public class DoubleXor {
	public int calculate(int N) {
		int[] dx = new int[7];
		int nn = N;
		for (int i = 0; i < dx.length; i++) {
			dx[i] = nn % 10;
			nn /= 10;
		}
		while (--N > 0) {
			nn = N;
			for (int i = 0; i < dx.length; i++) {
				int d = nn % 10;
				dx[i] = (dx[i] ^ d) % 10;
				nn /= 10;
			}
		}
		for (int i = dx.length-1; i >= 0; i--)
			N = N*10 + dx[i];
		return N;
	}
}
