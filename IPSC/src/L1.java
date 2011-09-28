import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class L1 {
	
	static final int INF = 1 << 28, MAX = 1000000;
	static final double EPS = 1E-9;
	
	static long[] inverse = new long[MAX];
	
	static long choose(long n, long k, long m) {
		if (n < k)
			return 0;
		if (k > n-k)
			k = n-k;
		long ans = 1;
		for (int i = 1; i <= k; i++) {
			ans *= (n - i + 1);
			ans %= m;
			ans *= inverse[i];
			ans %= m;
		}
		return ans;
	}
	
	static long chooseLucas(long n, long k, long m) {
		if (n < k || k < 0)
			return 0;
		if (n == k)
			return 1;
		long ans = (choose(n%m, k%m, m) * chooseLucas(n/m, k/m, m)) % m;
		return ans;
	}
	
	static void solve() {
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();
		for (int t = 1; t <= T; t++) {
			int N = scan.nextInt();
			int M = scan.nextInt();
			int K = scan.nextInt();
			int P = scan.nextInt();
			BigInteger mod = new BigInteger(P+"");
			for (int i = 1; i <= P/2; i++)
				inverse[i] = new BigInteger(i+"").modInverse(mod).longValue();
			long ways = 0;
			for (int part = 0; part <= K; part+=2) {
				ways += chooseLucas(part/2+M, M, P) * chooseLucas(K-part+N, N, P);
				ways %= P;
			}
			System.out.println(ways);
		}
	}
	public static void main(String[] args) throws IOException {
		solve();
	}
}
