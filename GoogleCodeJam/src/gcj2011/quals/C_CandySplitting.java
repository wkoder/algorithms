package gcj2011.quals;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class C_CandySplitting {
	
	static final int INF = 1 << 30, MAX = 1 << 20;
	static final double EPS = 1E-9;
	
	static void solve() {
		Scanner scan = new Scanner(in);
		int T = scan.nextInt();
		int[][] prev = new int[MAX][2];
		int[][] cur = new int[MAX][2];
		int[] val = new int[MAX];
		for (int t = 1; t <= T; t++) {
			out.print("Case #" + t + ": ");
			for (int i = 0; i < MAX; i++)
				Arrays.fill(cur[i], INF);
			
			int max = 1;
			int sum = 0;
			int xor = 0;
			int n = scan.nextInt();
			for (int i = 0; i < n; i++) {
				val[i] = scan.nextInt();
				sum += val[i];
				xor ^= val[i];
				while (max <= val[i])
					max <<= 1;
			}
			
			cur[0][0] = 0;
			for (int i = 0; i < n; i++) {
				int[][] temp = cur;
				cur = prev;
				prev = temp;
				for (int j = 0; j < max; j++)
					for (int k = 0; k < 2; k++)
						cur[j][k] = prev[j][k];
				
				for (int j = 0; j < max; j++) {
					int w = j ^ val[i];
					for (int x = 0; x < 2; x++) {
						int cost = prev[j][x] + val[i];
						if (cur[w][0] > cost) {
							cur[w][1] = cur[w][0];
							cur[w][0] = cost;
						} else if (cur[w][1] > cost)
							cur[w][1] = cost;
					}
				}
			}
			
			int ans = -INF;
			if (xor == 0)
				ans = sum - cur[0][1];
			for (int i = 1; i < MAX; i++)
				if ((i ^ xor) == i)
					ans = Math.max(ans, sum - cur[i][0]);
			if (ans <= 0)
				out.println("NO");
			else
				out.println(ans);
		}
	}
	
	static String read() {
		try { return in.readLine(); } catch (IOException e) { return null; }
	}
	public static void main(String[] args) throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new BufferedOutputStream(System.out));
//		String file = "C-large";
//		in = new BufferedReader( new FileReader(file + ".in") );
//		out = new PrintWriter( new FileOutputStream(file + "_" + System.currentTimeMillis() + ".out") );
		solve(); out.flush();
	}
	static BufferedReader in; static PrintWriter out;
}
