package gcj2010.round2;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class C_Bacteria {
	
	static final int INF = 1 << 28, MAX = 300;
	static final double EPS = 1E-9;
	static final int ZERO = MAX/2;
	
	static void solve() {
		Scanner scan = new Scanner(in);
		int T = scan.nextInt();
		for (int t = 1; t <= T; t++) {
			out.print("Case #" + t + ": ");
			boolean[][] map = new boolean[MAX][MAX];
			boolean[][] map2 = new boolean[MAX][MAX];
			boolean[][] tmp;
			int R = scan.nextInt();
			while (R-- > 0) {
				int x1 = scan.nextInt();
				int y1 = scan.nextInt();
				int x2 = scan.nextInt();
				int y2 = scan.nextInt();
				for (int x = x1; x <= x2; x++)
					for (int y = y1; y <= y2; y++)
						map[x+ZERO][y+ZERO] = true;
			}
			int s = 0;
			boolean ok = true;
			while (ok) {
				s++;
				ok = false;
				for (int i = 0; i < MAX; i++)
					Arrays.fill(map2[i], false);
				for (int i = 0; i < MAX; i++)
					for (int j = 0; j < MAX; j++)
						if (map[i][j]) {
							if (map[i-1][j] || map[i][j-1]) {
								ok = true;
								map2[i][j] = true;
							}
						} else if (i > 0 && j > 0) {
							if (map[i-1][j] && map[i][j-1]) {
								ok = true;
								map2[i][j] = true;
							}
						}
				tmp = map;
				map = map2;
				map2 = tmp;
			}
			out.println(s);
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
