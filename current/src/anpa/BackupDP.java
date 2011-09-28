package anpa;
import java.util.*;

public class BackupDP {
	public static void main(String[] args) {
		final int INF = 1 << 30;
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		int K = scan.nextInt();
		int[] d = new int[N+1];
		for (int i = 0; i < N; i++)
			d[i+1] = scan.nextInt();
		N++;
		for (int i = N-1; i > 0; i--)
			d[i] -= d[i-1];
		int[] prev = new int[N];
		int[] dp = new int[N];
		Arrays.fill(prev, INF);
		prev[0] = 0;
		for (int k = 0; k < K; k++) {
			int best = INF;
			for (int i = 0; i < N; i++) {
				if (i >= 2)
					best = Math.min(best, prev[i-2]);
				dp[i] = INF;
				if (i > 0) {
					dp[i] = Math.min(dp[i], dp[i-1] + d[i]);
					dp[i] = Math.min(dp[i], best + d[i]);
				}
			}
			int[] temp = prev;
			prev = dp;
			dp = temp;
		}
		int min = INF;
		for (int i = 0; i < N; i++)
			min = Math.min(min, prev[i]);
		System.out.println(min);
	}
}
