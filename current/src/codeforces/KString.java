package codeforces;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class KString {
	
	public static void solve() {
		int k = Integer.parseInt(read());
		char[] s = read().toCharArray();
		if (s.length % k != 0) {
			out.print(-1);
			return;
		}
		
		int[] c = new int[26];
		for (char ch : s)
			c[ch-'a']++;
		String r = "";
		for (int i = 0; i < 26; i++) {
			int cc = c[i];
			if (cc % k != 0) {
				out.print(-1);
				return;
			} else {
				cc /= k;
				while (cc-- > 0) {
					r += (char)(i+'a');
				}
			}
		}
		while (k-- > 0)
			out.print(r);
		out.println();
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
