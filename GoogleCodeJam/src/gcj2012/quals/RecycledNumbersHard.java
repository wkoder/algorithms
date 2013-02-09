package gcj2012.quals;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class RecycledNumbersHard {
	
	static final int INF = 1 << 28, MAX = 1000;
	static final double EPS = 1E-9;
	
	static int digits(int x) {
		int d;
		for (d = 0; x > 0; d++)
			x /= 10;
		return d;
	}
	
	static int hash(int m, int d) {
		int pow = 1;
		for (int i = 0; i < d-1; i++)
			pow *= 10;
		
		int hash = m;
		while (d-- > 0) {
			m = m / 10 + pow * (m % 10);
			if (m < hash)
				hash = m;
		}
		
		return hash;
	}
	
	static long count(Map<Integer, Integer> counter) {
		long count = 0;
		for (int k : counter.values())
			count += k * (k-1L) / 2;
		
		return count;
	}
	
	static void solve() {
		Scanner scan = new Scanner(in);
		int T = scan.nextInt();
		for (int t = 1; t <= T; t++) {
			out.print("Case #" + t + ": ");
			int A = scan.nextInt();
			int B = scan.nextInt();
			
			long r = 0;
			int lastD = 0;
			Map<Integer, Integer> counter = new HashMap<Integer, Integer>();
			for (int x = A; x <= B; x++) {
				int d = digits(x);
				if (d != lastD) {
					lastD = d;
					r += count(counter);
					counter.clear();
				}
				
				int hash = hash(x, d);
				Integer c = counter.get(hash);
				if (c == null)
					c = 0;
				counter.put(hash, c + 1);
			}
			
			r += count(counter);
			out.println(r);
		}
	}
	
	static String read() {
		try { return in.readLine(); } catch (IOException e) { return null; }
	}
	public static void main(String[] args) throws IOException {
//		in = new BufferedReader(new InputStreamReader(System.in));
//		out = new PrintWriter(new BufferedOutputStream(System.out));
		String file = "C-large";
		in = new BufferedReader( new FileReader(file + ".in") );
		out = new PrintWriter( new FileOutputStream(file + "_" + System.currentTimeMillis() + ".out") );
		solve(); out.flush();
	}
	static BufferedReader in; static PrintWriter out;
}
