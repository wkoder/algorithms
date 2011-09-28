package topcoder;
public class SpidersOnTheGrid {
	public int find(String[] A) {
		int[] DR = {-1, 0, 1, 0};
		int[] DC = {0, -1, 0, 1};
		String D = "NWSE";
		int R = A.length;
		int C = A[0].length();
		boolean[][] x = new boolean[R][C];
		for (int i = 0; i < R; i++)
			for (int j = 0; j < C; j++) {
				int d = D.indexOf(A[i].charAt(j));
				int ii = i + DR[d];
				int jj = j + DC[d];
				if (ii >= 0 && ii < R && jj >= 0 && jj < C)
					x[ii][jj] = true;
			}
		int f = 0;
		for (int i = 0; i < R; i++)
			for (int j = 0; j < C; j++)
				if (! x[i][j])
					f++;
		return f;
	}
}
