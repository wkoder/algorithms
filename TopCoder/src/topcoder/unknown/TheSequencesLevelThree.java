package topcoder.unknown;
import java.util.Arrays;

public class TheSequencesLevelThree {
	final long MOD = 1234567891;
	long[][] memo = new long[100][100];
	int[] s;
	int K;
	long f(int l, int r) {
		if (l == 0 || r == 0)
			return l == s.length-1 || r == s.length-1? 0 : 1;
		if (memo[l][r] >= 0)
			return memo[l][r];
		
		long w = 0;
		int next = Math.min(l, r) - 1;
		if (s[l]-s[next] <= K)
			w = (w + f(next, r)) % MOD;
		if (s[r]-s[next] <= K)
			w = (w + f(l, next)) % MOD;
		return memo[l][r] = w;
	}
	public int find(int[] seq, int k) {
		K = k;
		s = seq;
		Arrays.sort(s);
		for (int i = 0; i <= s.length; i++)
			Arrays.fill(memo[i], -1);
		long w = f(s.length-1, s.length-1);
		return (int) w;
	}
}
