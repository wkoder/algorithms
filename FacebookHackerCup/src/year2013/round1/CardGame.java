package year2013.round1;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class CardGame {
	
	static final int INF = 1 << 28;
	static final double EPS = 1E-9;
	
	static final int MAX = 10000;
	static final long MOD = 1000000007;
	static long[] a = new long[MAX];
	static final long[][] choose = new long[MAX+1][MAX+1];
	
	private static void hack() {
		for (int i = 0; i <= MAX; i++)
			choose[i][0] = choose[i][i] = 1;
		for (int i = 1; i <= MAX; i++)
			for (int j = 1; j < i; j++)
				choose[i][j] = (choose[i - 1][j - 1] + choose[i - 1][j]) % MOD;
		
		Scanner scan = new Scanner(in);
		int T = scan.nextInt();
		for (int t = 1; t <= T; t++) {
			out.print("Case #" + t + ": ");
			int N = scan.nextInt();
			int K = scan.nextInt();
			for (int i = 0; i < N; i++)
				a[i] = scan.nextLong();
			
			long sum = 0;
			Arrays.sort(a, 0, N);
			for (int n = N-1; n >= K-1; n--) {
				sum = (sum + a[n] * choose[n][K-1]) % MOD;
			}
			
			out.println(sum);
		}
	}
	
	static String read() {
		try { return in.readLine(); } catch (IOException e) { return null; }
	}
	public static void main(String[] args) throws IOException {
//		in = new BufferedReader(new InputStreamReader(System.in));
//		out = new PrintWriter(new BufferedOutputStream(System.out));
		String file = "card_game";
		in = new BufferedReader( new FileReader(file + ".txt") );
		out = new PrintWriter( new FileOutputStream(file + "_" + System.currentTimeMillis() + ".out") );
		hack(); out.flush();
	}
	static BufferedReader in; static PrintWriter out;
}