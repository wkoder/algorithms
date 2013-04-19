package unknown;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class TwoTables {
	
	static final int MAX = 50;
	static int[][] a = new int[MAX][MAX];
	static int[][] b = new int[MAX][MAX];
	
	public static void solve() {
		StringTokenizer tok = new StringTokenizer(read());
		int ra = Integer.parseInt(tok.nextToken());
		int ca = Integer.parseInt(tok.nextToken());
		for (int i = 0; i < ra; i++) {
			char[] s = read().toCharArray();
			for (int j = 0; j < ca; j++)
				a[i][j] = s[j] == '0' ? 0 : 1;
		}
		tok = new StringTokenizer(read());
		int rb = Integer.parseInt(tok.nextToken());
		int cb = Integer.parseInt(tok.nextToken());
		for (int i = 0; i < rb; i++) {
			char[] s = read().toCharArray();
			for (int j = 0; j < cb; j++)
				b[i][j] = s[j] == '0' ? 0 : 1;
		}
		
		int R = Math.max(ra, rb);
		int C = Math.max(ca, cb);
		int bestx = 0;
		int besty = 0;
		int max = -1;
		for (int r = -R; r < R; r++)
			for (int c = -C; c < C; c++) {
				int p = 0;
				for (int i = 0; i < ra; i++)
					if (i+r >= 0 && i+r < rb)
						for (int j = 0; j < ca; j++)
							if (j+c >= 0 && j+c < cb)
								p += a[i][j] & b[i+r][j+c];
//				System.out.println(r + " " + c + " = " + p);
				if (p > max) {
					max = p;
					bestx = r;
					besty = c;
				}
			}
		out.println(bestx + " " + besty);
	}
	
	public static String read() {
		try { return in.readLine(); } catch (IOException e) { return null; }
	}
	public static void main(String[] args) throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new BufferedOutputStream(System.out));
		solve(); out.flush();
	}
	static BufferedReader in; static PrintWriter out;
}
