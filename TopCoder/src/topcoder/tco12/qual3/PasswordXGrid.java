package topcoder.tco12.qual3;
public class PasswordXGrid {
	public int minSum(String[] horizontal, String[] vertical) {
		int R = horizontal.length;
		int C = vertical[0].length();
		int[][] cost = new int[R][C];
		for (int i = 0; i < R; i++)
			for (int j = 0; j < C; j++) {
				cost[i][j] = 0;
				if (i > 0)
					cost[i][j] = Math.max(cost[i][j], cost[i-1][j] + vertical[i-1].charAt(j)-'0');
				if (j > 0)
					cost[i][j] = Math.max(cost[i][j], cost[i][j-1] + horizontal[i].charAt(j-1)-'0');
			}
		
		return cost[R-1][C-1];
	}
}
