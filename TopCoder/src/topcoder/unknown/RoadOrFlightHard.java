package topcoder.unknown;
public class RoadOrFlightHard {
	
	long[] gen(int N, long first, long mod, long prod, long add) {
		long[] s = new long[N];
		s[0] = first % mod;
		for (int i = 1; i < N; i++)
			s[i] = (s[i-1] * prod + add) % mod;
		return s;
	}
	
	public long minTime(int N, int roadFirst, int roadProd, int roadAdd, int roadMod, int flightFirst, int flightProd, int flightAdd, int flightMod, int K) {
		final long INF = 1L << 62;
		long[] r = gen(N, roadFirst, roadMod, roadProd, roadAdd);
		long[] f = gen(N, flightFirst, flightMod, flightProd, flightAdd);
		
		long[][] prev = new long[K+1][2];
		long[][] cur = new long[K+1][2];
		long[][] temp;
		for (int i = 0; i <= K; i++)
			prev[i][1] = INF;
		for (int i = 0; i < N; i++) {
			for (int k = 0; k <= K; k++) {
				cur[k][0] = cur[k][1] = INF;
				
				cur[k][0] = Math.min(cur[k][0], prev[k][0] + r[i]);
				cur[k][0] = Math.min(cur[k][0], prev[k][1] + r[i]);
				
				cur[k][1] = Math.min(cur[k][1], prev[k][1] + f[i]);
				if (k > 0)
					cur[k][1] = Math.min(cur[k][1], prev[k-1][0] + f[i]);
			}
			temp = prev;
			prev = cur;
			cur = temp;
		}
		long min = INF;
		for (long[] p : prev)
			for (long v : p)
				min = Math.min(min, v);
		return min;
	}
}
