package topcoder.unknown;
public class MinimalDifference {
	public int findNumber(int A, int B, int C) {
		int best = -1;
		int bestDiff = 10000;
		int sumC = sum(C);
		for (int x = A; x <= B; x++) {
			int diff = Math.abs(sum(x) - sumC);
			if (diff < bestDiff) {
				bestDiff = diff;
				best = x;
			}
		}
		return best;
	}
	int sum(int n) {
		int s = 0;
		while (n > 0) {
			s += n % 10;
			n /= 10;
		}
		return s;
	}
}
