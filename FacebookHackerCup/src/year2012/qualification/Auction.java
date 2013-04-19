package year2012.qualification;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class Auction {
	
	static final int INF = 1 << 28;
	static final double EPS = 1E-9;
	static final int MAX = 10000001;
	static final int[] cycleA = new int[MAX];
	static final int[] cycleB = new int[MAX];
	
	private static int f(int a, int p1, int b, int m) {
		return (int)(((long)a*p1 + b) % m) + 1;
	}
	
	private static int findCycle(int[] cycle, int a, int p1, int b, int m, long n) {
		int len = 1;
		cycle[0] = p1;
		for (len = 2; len < n; len++) {
			cycle[len] = f(a, cycle[len-1], b, m);
			if  (cycle[len] == p1)
				break;
		}
		
		return len;
	}
	
	private static int countBargains(int[] cycleA, int lenA, int[] cycleB, int lenB) {
		for (int i = 0; i < lenA; i++) {
			
		}
		
		return 0;
	}
	
	private static void hack() {
		Scanner scan = new Scanner(in);
		int T = scan.nextInt();
		for (int t = 1; t <= T; t++) {
			out.print("Case #" + t + ": ");
			long N = scan.nextLong();
			int P1 = scan.nextInt();
			int W1 = scan.nextInt();
			int M = scan.nextInt();
			int K = scan.nextInt();
			int A = scan.nextInt();
			int B = scan.nextInt();
			int C = scan.nextInt();
			int D = scan.nextInt();
			
			int lenA = findCycle(cycleA, A, P1, B, M, N);
			int lenB = findCycle(cycleB, C, W1, D, K, N);
			out.print(countBargains(cycleA, lenA, cycleB, lenB));
			for (int i = 0; i < Math.max(lenA, lenB); i++) {
				cycleA[i] *= -1;
				cycleB[i] *= -1;
			}
			out.print(" " + countBargains(cycleA, lenA, cycleB, lenB));
		}
	}
	
	static String read() {
		try { return in.readLine(); } catch (IOException e) { return null; }
	}
	public static void main(String[] args) throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new BufferedOutputStream(System.out));
//		String file = "A-small";
//		in = new BufferedReader( new FileReader(file + ".txt") );
//		out = new PrintWriter( new FileOutputStream(file + "_" + System.currentTimeMillis() + ".out") );
		hack(); out.flush();
	}
	static BufferedReader in; static PrintWriter out;
}