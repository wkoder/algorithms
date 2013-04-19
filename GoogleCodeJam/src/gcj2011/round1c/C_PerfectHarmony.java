package gcj2011.round1c;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class C_PerfectHarmony {
	
	static final int INF = 1 << 28, MAX = 1000;
	static final double EPS = 1E-9;
	
	static long gcd(long a, long b) {
		while (b != 0) {
			long x = a % b;
			a = b;
			b = x;
		}
		
		return a;
	}
	
	static long lcm(long a, long b) {
		long g = gcd(a, b);
		long ag = a / g;
		if (Long.MAX_VALUE / ag < b)
			return Long.MAX_VALUE;
		return ag * b;
	}
	
	static void solve() {
		Scanner scan = new Scanner(in);
		int T = scan.nextInt();
		for (int t = 1; t <= T; t++) {
			out.print("Case #" + t + ": ");
			int N = scan.nextInt();
			long L = scan.nextLong();
			long H = scan.nextLong();
			
			long gcd = 0;
			long lcm = 1;
			long[] K = new long[N];
			for (int i = 0; i < N; i++) {
				long k = scan.nextLong();
				gcd = gcd(k, gcd);
				lcm = lcm(k, lcm);
				
				K[i] = k;
			}
			
			long ans = -1;
			for (long d = L; d <= H; d++) {
				boolean ok = true;
				for (int i = 0; i < N && ok; i++)
					ok &= K[i] % d == 0 || d % K[i] == 0;
				
				if (ok) {
					ans = d;
					break;
				}
			}
			
			long ans2;
			if (gcd >= L) {
				ans2 = L;
				while (gcd % ans2 != 0 && ans2 <= H)
					ans2++;
				
				if (ans2 > H)
					ans2 = -1;
			} else {
				Arrays.sort(K);
				lcm = 1;
				ans2 = -1;
				for (int i = 0; i < N; i++) {
					lcm = lcm(K[i], lcm);
					long d2 = lcm;
					boolean ok = true;
					while (d2 < H) {
						for (int j = i+1; j < N && ok; j++)
							ok &= K[i] % d2 == 0 || d2 % K[i] == 0;
						if (ok)
							if (d2 < L)
								d2 += lcm;
							else
								break;
					}
					
					if (ok) {
						ans2 = d2;
						break;
					}
				}
			}
			
			if (ans != ans2)
				System.out.println(ans + " != " + ans2);
			
			
			if (ans == -1)
				out.println("NO");
			else
				out.println(ans);
//			out.println(L + " " + H + " : " + gcd + " " + lcm);
		}
	}
	
	static String read() {
		try { return in.readLine(); } catch (IOException e) { return null; }
	}
	public static void main(String[] args) throws IOException {
//		in = new BufferedReader(new InputStreamReader(System.in));
//		out = new PrintWriter(new BufferedOutputStream(System.out));
		String file = "C-small";
		in = new BufferedReader( new FileReader(file + ".in") );
		out = new PrintWriter( new FileOutputStream(file + "_" + System.currentTimeMillis() + ".out") );
		solve(); out.flush();
	}
	static BufferedReader in; static PrintWriter out;
}