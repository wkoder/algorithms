package topcoder.tco12.qual3;
import java.util.Arrays;

public class FoxAndDoraemon {
	final int INF = 1 << 28;
	
	int[] cost;
	int splitCost;
	int[][] memo = new int[100][100];
	
	int go(int foxes, int tasks) {
		if (tasks == 0)
			return 0;
		if (foxes == 0)
			return INF;
		if (foxes >= tasks)
			return cost[tasks-1];
		if (memo[foxes][tasks] >= 0)
			return memo[foxes][tasks];
		
		int min = INF;
		for (int w = 0; w <= foxes; w++) {
			int m = Math.max(cost[tasks-1], splitCost + go(2 * (foxes-w), tasks-w));
			min = Math.min(min, m);
		}

		return memo[foxes][tasks] = min;
	}
	
	public int minTime(int[] workCost, int splitCost) {
		cost = workCost;
		this.splitCost = splitCost;
		Arrays.sort(cost);
		for (int i = 0; i < memo.length; i++)
			Arrays.fill(memo[i], -1);
		return go(1, cost.length);
	}
}
