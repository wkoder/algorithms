package gcj2010.round2;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class A_ElegantDiamond {
	
	static final int INF = 1 << 28, MAX = 300;
	static final double EPS = 1E-9;
	static final int ZERO = 100;
	
	static boolean fill(int[][] d) {
		int K = d.length;
		for (int i = 0; i < K; i++)
			for (int j = 0; j < K; j++)
				if (d[i][j] >= 0) {
					if (d[j][i] != d[i][j] && d[j][i] >= 0)
						return false;
					d[j][i] = d[i][j];
					
					if (d[K-1-j][K-1-i] != d[i][j] && d[K-1-j][K-1-i] >= 0)
						return false;
					d[K-1-j][K-1-i] = d[i][j];
				}
		return true;
	}
	
	static boolean can(int[][] d) {
		return fill(d) && fill(d);
	}
	
	static void solve() {
		Scanner scan = new Scanner(in);
		int T = scan.nextInt();
		for (int t = 1; t <= T; t++) {
			out.print("Case #" + t + ": ");
			int K = scan.nextInt();
			int[][] d = new int[K][K];
			for (int i = 0; i < 2*K-1; i++) {
				int s = i+1;
				int x = i;
				int y = 0;
				if (i >= K) {
					s = 2*K - i-1;
					y = i - K + 1;
					x = K-1;
				}
				while (s-- > 0) {
//					System.out.println(x +" "+ y);
					d[x][y] = scan.nextInt();
					x--;
					y++;
				}
			}
//			System.out.println();
//			for (int i = 0; i < K; i++)
//				System.out.println(Arrays.toString(d[i]));
//			System.out.println();
			int min = 1 << 28;
			for (int k = K; k <= 5*K; k++) {
				int[][] d2 = new int[k][k];
				for (int i = 0; i <= k-K; i++)
					for (int j = 0; j <= k-K; j++) {
						int cost = k*k - K*K;
						if (cost >= min)
							continue;
						
						for (int x = 0; x < k; x++)
							Arrays.fill(d2[x], -1);
						for (int x = 0; x < K; x++)
							for (int y = 0; y < K; y++)
								d2[i+x][j+y] = d[x][y];
						if(can(d2))
							min = cost;
					}
			}
			out.println(min);
		}
	}
	
	static String read() {
		try { return in.readLine(); } catch (IOException e) { return null; }
	}
	public static void main(String[] args) throws IOException {
//		in = new BufferedReader(new InputStreamReader(System.in));
//		out = new PrintWriter(new BufferedOutputStream(System.out));
		String file = "A-large";
		in = new BufferedReader( new FileReader(file + ".in") );
		out = new PrintWriter( new FileOutputStream(file + "_" + System.currentTimeMillis() + ".out") );
		solve(); out.flush();
	}
	static BufferedReader in; static PrintWriter out;
}
