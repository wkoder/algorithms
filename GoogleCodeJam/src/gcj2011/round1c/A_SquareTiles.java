package gcj2011.round1c;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class A_SquareTiles {
	
	static final int INF = 1 << 28, MAX = 1000;
	static final double EPS = 1E-9;
	
	static void solve() {
		Scanner scan = new Scanner(in);
		int T = scan.nextInt();
		for (int t = 1; t <= T; t++) {
			out.print("Case #" + t + ":");
			int R = scan.nextInt();
			int C = scan.nextInt();
			char[][] b = new char[R][];
			for (int i = 0; i < R; i++)
				b[i] = scan.next().toCharArray();
			
			boolean ok = true;
			next:
			for (int i = 0; i < R-1; i++)
				for (int j = 0; j < C-1; j++)
					if (b[i][j] == '#') {
						if (b[i+1][j] != '#' || b[i][j+1] != '#' || b[i+1][j+1] != '#' ) {
							ok = false;
							break next;
						}
						
						b[i][j] = '/';
						b[i+1][j+1] = '/';
						b[i+1][j] = '\\';
						b[i][j+1] = '\\';
					}
			
			if (ok)
				for (int i = 0; i < R; i++)
					for (int j = 0; j < C; j++)
						if (b[i][j] == '#')
							ok = false;
			
			out.println();
			if (!ok)
				out.println("Impossible");
			else {
				for (int i = 0; i < R; i++)
					out.println(new String(b[i]));
			}
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
