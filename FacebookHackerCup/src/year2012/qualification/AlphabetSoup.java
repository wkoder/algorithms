package year2012.qualification;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class AlphabetSoup {
	
	static final int INF = 1 << 28;
	static final double EPS = 1E-9;
	
	private static int[] getCount(String s) {
		int[] count = new int[256];
		for (char ch : s.toCharArray())
			count[ch]++;
		return count;
	}
	
	private static void hack() throws Exception {
		int T = Integer.parseInt(in.readLine());
		int[] base = getCount("HACKERCUP");
		for (int t = 1; t <= T; t++) {
			out.print("Case #" + t + ": ");
			int[] count = getCount(in.readLine());
			int w = INF;
			for (int i = 0; i < base.length; i++)
				if (base[i] > 0)
					w = Math.min(w, count[i] / base[i]);
			out.println(w);
		}
	}
	
	static String read() {
		try { return in.readLine(); } catch (IOException e) { return null; }
	}
	public static void main(String[] args) throws Exception {
//		in = new BufferedReader(new InputStreamReader(System.in));
//		out = new PrintWriter(new BufferedOutputStream(System.out));
		String file = "alphabet_soup";
		in = new BufferedReader( new FileReader(file + ".txt") );
		out = new PrintWriter( new FileOutputStream(file + "_" + System.currentTimeMillis() + ".out") );
		hack(); out.flush();
	}
	static BufferedReader in; static PrintWriter out;
}
