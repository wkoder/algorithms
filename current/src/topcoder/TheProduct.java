package topcoder;
public class TheProduct {
	int maxDist;
	Long[][][] memo = new Long[55][15][2];
	int[] n;
	
	Long[] go(int pos, int k) {
		if (k == 1)
			return new Long[]{(long)n[pos], (long)n[pos]};
		if (memo[pos][k][0] != null)
			return memo[pos][k];
		long max = Long.MIN_VALUE;
		long min = Long.MAX_VALUE;
		for (int i = pos+1; i <= pos+maxDist && i <= n.length-(k-1); i++) {
			Long[] a = go(i, k-1);
			max = Math.max(max, Math.max(n[pos]*a[0], n[pos]*a[1]));
			min = Math.min(min, Math.min(n[pos]*a[0], n[pos]*a[1]));
		}
		memo[pos][k][0] = min;
		memo[pos][k][1] = max;
		return memo[pos][k];
	}
	
	public long maxProduct(int[] numbers, int k, int maxDist) {
		this.maxDist = maxDist;
		n = numbers;
		long max = Long.MIN_VALUE;
		for (int i = 0; i < n.length-(k-1); i++)
			max = Math.max(max, go(i, k)[1]);
		return max;
	}
}
