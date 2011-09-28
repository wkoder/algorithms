import java.io.File;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.*;

public class PegGame {
	
	static int MAX = 101;
	
	public static void main(String[] args) throws Exception {
		final String file = "5";
		//Scanner scan = new Scanner(System.in);
		Scanner scan = new Scanner(new File(file + ".in"));
		//PrintWriter out = new PrintWriter(System.out);
		PrintWriter out = new PrintWriter(new File(file + "_" + System.currentTimeMillis() + ".out"));
		
		double[][] prob = new double[MAX][MAX*2];
		boolean[][] peg = new boolean[MAX][MAX*2];
		DecimalFormat f = new DecimalFormat("0.000000");
		
		int N = scan.nextInt();
		while (N-- > 0) {
			int R = scan.nextInt();
			int C = scan.nextInt();
			int G = scan.nextInt();
			int M = scan.nextInt();
			for (int i = 0; i < R; i++)
				Arrays.fill(peg[i], true);
			for (int i = 0; i < R; i++)
				for (int j = 0; j < C; j++)
					peg[i][j*2 + i%2 + 1] = false;
			while (M-- > 0) {
				int r = scan.nextInt();
				int c = scan.nextInt();
				peg[r][c*2 + r%2] = false;
			}
			
			int best = -1;
			double bestprob = -1;
			for (int k = 0; k < C; k++) {
				for (int i = 0; i < R; i++)
					Arrays.fill(prob[i], 0);
				
				prob[0][k*2+1] = 1;
				for (int r = 0; r < R-1; r++)
					for (int c = 0; c < C*2; c++)
						if (prob[r][c] > 0) {
							if (!peg[r+1][c])
								prob[r+1][c] = prob[r][c];
							else if (!peg[r+1][c-1])
								if (!peg[r+1][c+1]) {
									prob[r+1][c-1] += prob[r][c] / 2;
									prob[r+1][c+1] += prob[r][c] / 2;
								} else
									prob[r+1][c-1] += prob[r][c];
							else if (!peg[r+1][c+1])
								prob[r+1][c+1] += prob[r][c];
							else
								System.err.println("WTF?! " + r + " " + c);
						}
				
				if (prob[R-1][G*2+1] > bestprob) {
					best = k;
					bestprob = prob[R-1][G*2+1];
				}
			}
			
			out.println(best + " " + f.format(bestprob));
		}
		
		out.flush();
		out.close();
	}
}
