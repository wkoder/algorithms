package codeforces;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class SpecialOffer {
	
	private static int count(long n) {
		int c = 0;
		while (n % 10 == 9) {
			n /= 10;
			c++;
		}
		return c;
	}
	
	public static void solve() {
		StringTokenizer tok = new StringTokenizer(read());
		long P = Long.parseLong(tok.nextToken());
		long D = Long.parseLong(tok.nextToken());
		long best = P;
		int bestCount = count(best);
		long p = P;
		while (p > 0) {
			long pow = 1;
			long l = p;
			long r = 0;
			while (l % 10 == 9) {
				l /= 10;
				r += 9*pow;
				pow *= 10;
			}
			p = (l-1)*pow + r;
			
			if (p < P-D)
				break;
			int c = count(p);
			if (c > bestCount) {
				bestCount = c;
				best = p;
			}
		}
		out.println(best);
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
