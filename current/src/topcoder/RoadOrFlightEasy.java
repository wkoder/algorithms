package topcoder;
public class RoadOrFlightEasy {
	public int minTime(int N, int[] roadTime, int[] flightTime, int K) {
		final int INF = 1 << 28;
		int[][] dp = new int[N][K+1];
		for (int k = 0; k <= K; k++)
			dp[0][k] = INF;
		dp[0][0] = roadTime[0];
		if (K > 0)
			dp[0][1] = flightTime[0];
		for (int i = 1; i < N; i++)
			for (int k = 0; k <= K; k++) {
				dp[i][k] = INF;
				if (i == 0)
					dp[i][k] = 0;
				else {
					dp[i][k] = Math.min(dp[i][k], dp[i-1][k] + roadTime[i]);
					if (k > 0)
						dp[i][k] = Math.min(dp[i][k], dp[i-1][k-1] + flightTime[i]);
				}
			}
		int min = INF;
		for (int t : dp[N-1])
			min = Math.min(min, t);
		return min;
	}
}
