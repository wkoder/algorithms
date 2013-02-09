package topcoder.tco12.qual1;
public class EllysFractions {
	long[][] choose;
	
	public long getCount(int N) {
		long count = 0;
		int primes = 0;
		nChooseKDP(N, N);
		for (int i = 2; i <= N; i++) {
			primes++;
			for (int j = 2; j*j <= i; j++)
				if (i % j == 0) {
					primes--;
					break;
				}
			
			count += 1L << (primes - 1);
		}
		
		return count;
	}
	
	void nChooseKDP(int n, int k) {
		choose = new long[n + 1][k + 1];
		int i, j;
		for (i = 0; i <= n; i++)
			choose[i][0] = choose[i][i] = 1;
		for (i = 1; i <= n; i++)
			for (j = 1; j < i; j++)
				choose[i][j] = choose[i - 1][j - 1] + choose[i - 1][j];
	}
}
