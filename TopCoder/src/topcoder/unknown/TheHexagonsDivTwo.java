package topcoder.unknown;
import java.util.Arrays;

public class TheHexagonsDivTwo {
	long[][] memo = new long[9][1 << 6];
	long go(int pos, int used) {
		if (pos == K)
			return used == (1 << 6)-1? 1 : 0;
		if (memo[pos][used] >= 0)
			return memo[pos][used];
		
		long w = 0;
		for (int i = 0; i < S; i++) {
			int s = sets[i];
			if ((s & used) == 0 && Integer.bitCount(s) <= m[pos]) {
				long w2 = go(pos+1, s | used);
				for (int j = 0; j < Integer.bitCount(s); j++)
					w2 *= m[pos] - j;
				w += w2;
			}
		}
		return memo[pos][used] = w;
	}
	int[] m;
	int K;
	int[] sets = new int[64];
	int S;
	public long count(int n, int k) {
		S = 0;
		loop:
		for (int s = 0; s < 1 << 6; s++) {
			for (int i = 0; i < 5; i++)
				if (((s >> i) & 1) == 1 && ((s >> (i+1)) & 1) == 1)
					continue loop;
			if ((s & 1) == 1 && ((s >> 5) & 1) == 1)
				continue;
			sets[S++] = s;
		}
		long w = 0;
		K = k;
		m = new int[k];
		for (int x = 1; x <= n; x++) {
			Arrays.fill(m, 0);
			for (int y = 1; y <= n; y++)
				m[y % k]++;
			m[x % k] = 0;
			for (int i = 0; i < k; i++)
				Arrays.fill(memo[i], -1);
			w += go(0, 0) / 6;
		}
		return w;
	}
}
