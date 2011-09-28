package topcoder;
import java.util.*;

public class SequenceMerger {
	long[][] S;
	int N;
	long[] f2(long p, int s) {
		long[] r = new long[2];
		if (S[s][0] == 'E') {
			for (int i = 1; i < S[s].length; i++)
				if (S[s][i] < p)
					r[0]++;
				else if (S[s][i] == p)
					r[1]++;
		} else {
			long A = S[s][1];
			long B = S[s][2];
			long C = S[s][3];
			if (S[s][0] == 'A') {
				if (p >= A) {
					long Ci = (p - A) / B;
					r[0] = Ci;
					if (A + B*Ci == p)
						r[1]++;
					else
						r[0]++;
				}
			} else {
				long Ci = Math.round(Math.pow(p / (double)A, 1.0/B));
				r[0] = Ci;
				long C2 = A * Math.round(Math.pow(B, Ci));
				if (C2 == p)
					r[1]++;
				else
					r[0]++;
			}
		}
		return r;
	}
	int f(int p) {
		long l = 1;
		long u = 1000000000;
		while (u -l > 3) {
			long m = (u + l) / 2;
			long lower = 0;
			long same = 0;
			for (int i = 0; i < N; i++) {
				long[] r = f2(m, i);
				lower += r[0];
				same += r[1];
			}
			if (lower + same > p)
				u = m;
			else
				l = m;
		}
		
		for (long m = l; m <= u; m++) {
			long lower = 0;
			long same = 0;
			for (int i = 0; i < N; i++) {
				long[] r = f2(m, i);
				lower += r[0];
				same += r[1];
			}
			if (lower < p && lower+same >= p)
				return (int) m;
		}
		
		return -1;
	}
	public int[] getVal(String[] seqs, int[] positions) {
		long count = 0;
		N = seqs.length;
		S = new long[N][];
		for (int i = 0; i < N; i++) {
			StringTokenizer tok = new StringTokenizer(seqs[i]);
			S[i] = new long[tok.countTokens()];
			S[i][0] = tok.nextToken().charAt(0);
			for (int j = 1; j < S[i].length; j++)
				S[i][j] = Integer.parseInt(tok.nextToken());
			if (S[i][0] == 'E')
				count += S[i].length - 1;
			else
				count += S[i][3];
		}
		int[] res = new int[positions.length];
		for (int i = 0; i < res.length; i++)
			if (positions[i] > count)
				res[i] = -1;
			else
				res[i] = f(positions[i]-1);
		return res;
	}
}
