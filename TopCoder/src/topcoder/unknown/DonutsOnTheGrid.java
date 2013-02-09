package topcoder.unknown;
public class DonutsOnTheGrid {
	public long calc(int R, int C, int seed, int threshold) {
		boolean[][] m = new boolean[R][C];
		int x = seed;
		for (int i = 0; i < R; i++)
			for (int j = 0; j < C; j++) {
				x = (x * 25173 + 13849) % 65536;
				m[i][j] = x < threshold;
			}
		short[][] left = new short[R][C];
		for (int j = 0; j < C; j++)
			for (int i = 0; i < R; i++)
				if (m[i][j])
					left[i][j] = (short)(1 + (j > 0? left[i][j-1] : 0));
		short[][] downto = new short[R][C];
		for (int i = R-1; i >= 0; i--)
			for (int j = 0; j < C; j++)
				if (!m[i][j])
					downto[i][j] = -1;
				else {
					downto[i][j] = (short)i;
					if (i < R-1 && downto[i+1][j] >= 0)
						downto[i][j] = downto[i+1][j];
				}
		int W = Math.max(R, C) + 1;
		short[][][] up = new short[R][C][W];
		for (int i = 0; i < R; i++)
			for (int j = 0; j < C; j++)
				if (m[i][j])
					for (int w = 0; w < W; w++) {
						short p = 0;
						if (i > 0)
							p = up[i-1][j][w];
						if (w <= left[i][j])
							p++;
						up[i][j][w] = p;
					}
		long donuts = 0;
		for (int i = 0; i < R-2; i++)
			for (int j = 0; j < C-1; j++)
				if (m[i][j] && m[i][j+1])
					for (int k = j+2; k < C; k++) {
						if (! m[i][k])
							break;
						
						int dt = Math.min(downto[i][j], downto[i][k]);
						if (dt >= i+2)
							donuts += up[dt][k][k-j+1] - up[i+1][k][k-j+1];
					}
		return donuts;
	}
}
