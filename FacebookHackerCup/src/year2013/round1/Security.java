package year2013.round1;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class Security {
	
	static final int INF = 1 << 28;
	static final double EPS = 1E-9;
	
	static int MAX = 100;
	static int[] matchL = new int[MAX];
	static int[] matchR = new int[MAX];
	static boolean[] seen = new boolean[MAX];
	static boolean[][] bgraph = new boolean[MAX][MAX];
	
	private static boolean matches(char[] k1, char[] k2, int M) {
		int N = k1.length;
		int L = N / M;
		for (int i = 0; i < M; i++) {
			next:
			for (int j = 0; j < M; j++) {
				bgraph[i][j] = false;
				for (int k = 0; k < L; k++) {
					char ch1 = k1[i*L + k];
					char ch2 = k2[j*L + k];
					if (!(ch1 == ch2 || ch1 == '?' || ch2 == '?'))
						continue next;
				}
				bgraph[i][j] = true;
			}
		}
		
		return bipartiteMatching(M) == M;
	}
	
	static int bipartiteMatching(int N) { // O(m n)
		Arrays.fill(matchL, 0, N, -1);
		Arrays.fill(matchR, 0, N, -1);
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			Arrays.fill(seen, false);
			if (bpm(i, N))
				cnt++;
		}
		return cnt;
	}
	static boolean bpm(int u, int N) {
		for (int v = 0; v < N; v++)
			if (bgraph[u][v] && !seen[v]) {
				seen[v] = true;
				if (matchR[v] < 0 || bpm(matchR[v], N)) {
					matchL[u] = v;
					matchR[v] = u;
					return true;
				}
			}
		return false;
	}
	
	private static void hack() {
		Scanner scan = new Scanner(in);
		int T = scan.nextInt();
		char[] set = {'a', 'b', 'c', 'd', 'e', 'f'};
		for (int t = 1; t <= T; t++) {
			out.print("Case #" + t + ": ");
			int M = scan.nextInt();
			char[] k1 = scan.next().toCharArray();
			char[] k2 = scan.next().toCharArray();
			int N = k1.length;
			if (!matches(k1, k2, M))
				out.println("IMPOSSIBLE");
			else {
				for (int i = 0; i < N; i++)
					if (k1[i] == '?') {
						for (char ch : set) {
							k1[i] = ch;
							if (matches(k1, k2, M))
								break;
						}
					}
				out.println(new String(k1));
			}
		}
	}
	
	static String read() {
		try { return in.readLine(); } catch (IOException e) { return null; }
	}
	public static void main(String[] args) throws IOException {
//		in = new BufferedReader(new InputStreamReader(System.in));
//		out = new PrintWriter(new BufferedOutputStream(System.out));
		String file = "security";
		in = new BufferedReader( new FileReader(file + ".txt") );
		out = new PrintWriter( new FileOutputStream(file + "_" + System.currentTimeMillis() + ".out") );
		hack(); out.flush();
	}
	static BufferedReader in; static PrintWriter out;
}
