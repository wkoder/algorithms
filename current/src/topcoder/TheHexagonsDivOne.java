package topcoder;
import java.util.Arrays;

public class TheHexagonsDivOne {
	long[][] memo = new long[150][1 << 6];
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
	public long count(int n) {
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
		K = n;
		m = new int[n];
		for (int x = 1; x <= n*2; x++) {
			Arrays.fill(m, 0);
			for (int y = 1; y <= n*2; y++)
				m[y % n]++;
			m[x % n] = 0;
			for (int i = 0; i < n; i++)
				Arrays.fill(memo[i], -1);
			w += go(0, 0) / 6;
		}
		return w;
	}
}
