package topcoder;
import java.util.*;

public class MazeMaker {
	public int longestPath(String[] maze, int startRow, int startCol, int[] moveRow, int[] moveCol) {
		final int INF = 1 << 28;
		int R = maze.length;
		int C = maze[0].length();
		char[][] m = new char[R][];
		for (int i = 0; i < R; i++)
			m[i] = maze[i].toCharArray();
		int[][] cost = new int[R][C];
		for (int i = 0; i < R; i++)
			Arrays.fill(cost[i], INF);
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(startRow);
		q.add(startCol);
		cost[startRow][startCol] = 0;
		while (! q.isEmpty()) {
			int r = q.poll();
			int c = q.poll();
			for (int i = 0; i < moveRow.length; i++) {
				int rr = r + moveRow[i];
				int cc = c + moveCol[i];
				if (rr >= 0 && rr < R && cc >= 0 && cc < C && m[rr][cc] == '.' && cost[rr][cc] > cost[r][c]+1) {
					cost[rr][cc] = cost[r][c]+1;
					q.add(rr);
					q.add(cc);
				}
			}
		}
		int max = 0;
		for (int i = 0; i < R; i++)
			for (int j = 0; j < C; j++)
				if (m[i][j] == '.')
					max = Math.max(max, cost[i][j]);
		return max < INF? max : -1;
	}
}
