import java.io.*;
import java.util.*;

public class CousinPrimes_WCoder {
	
	private static int MAXP = 100000;
	
	public static void main(String[] args) throws IOException {
		boolean[] isPrime = new boolean[MAXP];
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		for (int i = 2; i < MAXP; i++)
			if (isPrime[i])
				for (int j = i+i; j < MAXP; j += i)
					isPrime[j] = false;
		
		//Scanner scan = new Scanner(new File("cousinprimes.in"));
		//PrintWriter out = new PrintWriter("cousinprimes.out");
		Scanner scan = new Scanner(System.in);
		PrintStream out = System.out;
		int T = scan.nextInt();
		while (T-- > 0) {
			String dd = scan.next();
			int N = dd.length();
			int[] d = new int[N];
			for (int i = 0; i < N; i++)
				d[i] = dd.charAt(i) - '0';
			int[] dp = new int[N+1];
			for (int i = 1; i <= N; i++) {
				dp[i] = dp[i-1];
				for (int l = 1; l <= 5 && i-l >= 0; l++) {
					int n = 0;
					for (int j = i-l; j < i; j++)
						n = n*10 + d[j];
					if (isPrime[n])
						dp[i] = Math.max(dp[i], dp[i-l] + 1);
				}
			}
			out.println(dp[N]);
		}
		//out.flush();
		//out.close();
	}
}
