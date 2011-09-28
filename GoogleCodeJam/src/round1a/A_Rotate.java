package round1a;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class A_Rotate {
	
	static final int INF = 1 << 28, MAX = 1000;
	static final double EPS = 1E-9;
	
	static int count(char[][] b, int N, char L) {
		int max = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (b[i][j] == L)
					for (int di = -1; di <= 1; di++)
						for (int dj = -1; dj <= 1; dj++)
							if (di != 0 || dj != 0) {
								for (int k = 0; k < N; k++) {
									int ii = i + di*k;
									int jj = j + dj*k;
									if (ii >= 0 && ii < N && jj >= 0 && jj < N && b[ii][jj] == L)
										max = Math.max(max, k+1);
									else
										break;
								}
							}
		return max;
	}
	
	static void solve() {
		Scanner scan = new Scanner(in);
		int T = scan.nextInt();
		for (int t = 1; t <= T; t++) {
			out.print("Case #" + t + ": ");
			int N = scan.nextInt();
			int K = scan.nextInt();
			char[][] b = new char[N][];
			for (int i = 0; i < N; i++)
				b[i] = scan.next().toCharArray();
			char[][] b2 = new char[N][N];
			for  (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++)
					b2[j][N-1-i] = b[i][j];
			for (int i = N-1; i >= 0; i--)
				for  (int j = 0; j < N; j++)
					if (b2[i][j] != '.')
						for (int k = N-1; k > i; k--)
							if (b2[k][j] == '.') {
								b2[k][j] = b2[i][j];
								b2[i][j] = '.';
								break;
							}
			
			int red = count(b2, N, 'R');
			int blue = count(b2, N, 'B');
			if (red >= K)
				if (blue >= K)
					out.println("Both");
				else
					out.println("Red");
			else if (blue >= K)
				out.println("Blue");
			else
				out.println("Neither");
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
