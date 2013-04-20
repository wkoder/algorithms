package solved;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * http://projecteuler.net/problem=81
 * 
 * @author Moises Osorio
 *
 */
public class P81_PathSumTwoWays {
	
	static final int MAX = 80;

	public static void solve() throws Exception {
		int[][] data = new int[MAX][MAX];
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("matrix.txt")));
		for (int i = 0; i < MAX; i++) {
			StringTokenizer tok = new StringTokenizer(reader.readLine(), ",");
			for (int j = 0; j < MAX; j++)
				data[i][j] = Integer.parseInt(tok.nextToken());
		}
		reader.close();
		
		int[][] dp = new int[MAX][MAX];
		for (int i = 0; i < MAX; i++)
			for (int j = 0; j < MAX; j++) {
				if (i == 0 && j == 0)
					dp[i][j] = data[i][j];
				else {
					dp[i][j] = 1 << 30;
					if (i > 0)
						dp[i][j] = Math.min(dp[i][j], data[i][j] + dp[i-1][j]);
					if (j > 0)
						dp[i][j] = Math.min(dp[i][j], data[i][j] + dp[i][j-1]);
				}
			}
		System.out.println(dp[MAX-1][MAX-1]);
	}

	public static void main(String[] args) throws Exception {
		final long START = System.currentTimeMillis();
		solve();
		System.err.println("It took " + (System.currentTimeMillis() - START) + " ms to solve");
	}
	
}
