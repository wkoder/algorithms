package topcoder.unknown;
public class SteeplechaseTrack {
	public int maxComplexity(String[] fences, String[] tracks, int K) {
		final int INF = 1 << 28;
		int N = fences.length + 2;
		int[][] mat = new int[N][N];
		for (int i = 0; i < N-2; i++)
			for (int j = 0; j < N-2; j++)
				mat[i][j] = tracks[i].charAt(j) - '0';
		int[] cost = new int[N];
		for (int i = 0; i < N-2; i++) {
			cost[i] = fences[i].charAt(0) - '0';
			mat[N-2][i] = fences[i].charAt(1) - '0';
			mat[i][N-1] = fences[i].charAt(2) - '0';
		}
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (mat[i][j] == 0)
					mat[i][j] = -INF;
		
		int[][] dp = new int[K+2][N];
		int max = -1;
		for (int i = 0; i < N; i++)
			dp[0][i] = -INF;
		dp[0][N-2] = 0;
		for (int k = 1; k <= K+1; k++) {
			int[] bef = dp[k-1];
			int[] now = dp[k];
			for (int i = 0; i < N; i++) {
				now[i] = -INF;
				for (int j = 0; j < N; j++)
					now[i] = Math.max(now[i], mat[j][i] + bef[j] + cost[i]);
			}
			max = Math.max(max, now[N-1]);
		}
		return max;
	}
}
