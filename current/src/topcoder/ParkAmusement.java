package topcoder;
import java.util.Arrays;

public class ParkAmusement {
	int N;
	double[][] memo;
	String[] m;
	double go(int r, int k) {
		if (m[r].contains("E"))
			return k == 0? 1 : 0;
		if (k == 0 || m[r].contains("P"))
			return 0;
		if (memo[r][k] >= 0)
			return memo[r][k];
		
		double p = 0;
		int c = 0;
		for (int i = 0; i < N; i++)
			if (m[r].charAt(i) == '1') {
				c++;
				p += go(i, k-1);
			}
		return memo[r][k] = p / c;
	}
	public double getProbability(String[] landings, int startLanding, int K) {
		m = landings;
		N = m.length;
		memo = new double[N][K+1];
		for (int i = 0; i < N; i++)
			Arrays.fill(memo[i], -1);
		double sum = 0;
		for (int i = 0; i < N; i++)
			sum += go(i, K);
		return go(startLanding, K) / sum;
	}
}
