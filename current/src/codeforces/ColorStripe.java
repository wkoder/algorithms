package codeforces;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class ColorStripe {
	
	public static void solve() {
		StringTokenizer tok = new StringTokenizer(read());
		int n = Integer.parseInt(tok.nextToken());
		int k = Integer.parseInt(tok.nextToken());
		char[] str = read().toCharArray();
		int w = 0;
		if (k == 2) {
			int w1 = 0;
			int w2 = 0;
			for (int i = 0; i < n; i++) {
				if (str[i] == 'A')
					if (i % 2 == 0)
						w1++;
					else
						w2++;
				else
					if (i % 2 == 1)
						w1++;
					else
						w2++;
			}
			w = n - Math.max(w1, w2);
			if (w1 >= w2)
				for (int i = 0; i < n; i++)
					str[i] = i % 2 == 0 ? 'A' : 'B';
			else
				for (int i = 0; i < n; i++)
					str[i] = i % 2 == 1 ? 'A' : 'B';
		} else {
			for (int i = 1; i < n; i++) {
				if (str[i] == str[i-1]) {
					w++;
					List<Character> opt = new ArrayList<Character>();
					opt.add('A');
					opt.add('B');
					opt.add('C');
					opt.remove((Character)str[i-1]);
					if (i < n-1)
						opt.remove((Character)str[i+1]);
					str[i] = opt.get(0);
				}
			}
		}
		
		out.println(w);
		out.println(new String(str));
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
