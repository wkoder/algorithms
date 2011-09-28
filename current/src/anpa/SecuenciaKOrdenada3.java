package anpa;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class SecuenciaKOrdenada3 {
	
	static void solve() {
		int[][] ans = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 6, 5, 1, 0, 0, 0, 0, 0, 0, 0}, {0, 24, 23, 7, 1, 0, 0, 0, 0, 0, 0}, {0, 120, 119, 50, 9, 1, 0, 0, 0, 0, 0}, {0, 720, 719, 371, 78, 11, 1, 0, 0, 0, 0}, {0, 5040, 5039, 3023, 714, 112, 13, 1, 0, 0, 0}, {0, 40320, 40319, 26962, 6987, 1176, 152, 15, 1, 0, 0}, {0, 362880, 362879, 263503, 73979, 13104, 1800, 198, 17, 1, 0}, {0, 3628800, 3628799, 2806759, 846718, 155989, 22320, 2610, 250, 19, 1}};
		StringTokenizer tok = new StringTokenizer(read());
		int N = Integer.parseInt(tok.nextToken());
		int K = Integer.parseInt(tok.nextToken());
		out.println(ans[N][K]);
	}
	
	static String read() {
		try { return in.readLine(); } catch (IOException e) { return null; }
	}
	public static void main(String[] args) throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new BufferedOutputStream(System.out));
		solve(); out.flush();
	}
	static BufferedReader in; static PrintWriter out;
}
