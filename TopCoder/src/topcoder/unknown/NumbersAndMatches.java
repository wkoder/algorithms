package topcoder.unknown;
import java.util.Arrays;

public class NumbersAndMatches {
	int MAX = 128;
	int[] S = new int[10];
	int[] number;
	long[][][] memo = new long[20][MAX][MAX*2];
	long go(int pos, int k, int move) {
		if (k < 0)
			return 0;
		if (pos == number.length)
			return move == MAX? 1 : 0;
		if (memo[pos][k][move] >= 0)
			return memo[pos][k][move];
		long w = 0;
		for (int i = 0; i < S.length; i++) {
			int n = number[pos];
			int k2 = k;
			int move2 = move;
			int np = Integer.bitCount(S[n]) - Integer.bitCount(S[n] & S[i]);
			int ip = Integer.bitCount(S[i]) - Integer.bitCount(S[n] & S[i]);
			
			move2 -= np - ip;
			k2 -= np;
			w += go(pos+1, k2, move2);
		}
		return memo[pos][k][move] = w;
	}
	public long differentNumbers(long N, int K) {
		S[0] = Integer.parseInt("1110111", 2);
		S[1] = Integer.parseInt("0010011", 2);
		S[2] = Integer.parseInt("1011101", 2);
		S[3] = Integer.parseInt("1011011", 2);
		S[4] = Integer.parseInt("0111010", 2);
		S[5] = Integer.parseInt("1101011", 2);
		S[6] = Integer.parseInt("1101111", 2);
		S[7] = Integer.parseInt("1010010", 2);
		S[8] = Integer.parseInt("1111111", 2);
		S[9] = Integer.parseInt("1111011", 2);
		String s = N + "";
		number = new int[s.length()];
		for (int i = 0; i < s.length(); i++)
			number[i] = s.charAt(i) - '0';
		for (int i = 0; i < memo.length; i++)
			for (int j = 0; j < memo[i].length; j++)
				Arrays.fill(memo[i][j], -1);
		return go(0, K, MAX);
	}
}
