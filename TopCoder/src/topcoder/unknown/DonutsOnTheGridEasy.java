package topcoder.unknown;
import java.util.Arrays;

public class DonutsOnTheGridEasy {
	int MAX = 50;
	int[][][][] memo = new int[MAX][MAX][MAX][MAX];
	int[][] l = new int[MAX][MAX];
	int[][] u = new int[MAX][MAX];
	int go(int a, int b, int x, int y) {
		if (x-a <= 1 || y-b <= 1)
			return 0;
		if (memo[a][b][x][y] >= 0)
			return memo[a][b][x][y];
		int max = 0;
		if (l[x][y] >= y-b+1 && l[a][y] >= y-b+1 && u[x][y] >= x-a+1 && u[x][b] >= x-a+1)
			max = Math.max(max, go(a+1, b+1, x-1, y-1) + 1);
		max = Math.max(max, go(a+1, b, x, y));
		max = Math.max(max, go(a, b+1, x, y));
		max = Math.max(max, go(a, b, x-1, y));
		max = Math.max(max, go(a, b, x, y-1));
		return memo[a][b][x][y] = max;
	}
	public int calc(String[] grid) {
		int R = grid.length;
		int C = grid[0].length();
		for (int i = 0; i < R; i++)
			for (int j = 0; j < C; j++)
				if (grid[i].charAt(j) == '0') {
					u[i][j] = l[i][j] = 1;
					if (j > 0)
						l[i][j] += l[i][j-1];
					if (i > 0)
						u[i][j] += u[i-1][j];
				}
		for (int i = 0; i < R; i++)
			for (int j = 0; j < C; j++)
				for (int k = 0; k < R; k++)
					Arrays.fill(memo[i][j][k], -1);
		return go(0, 0, R-1, C-1);
	}
}
