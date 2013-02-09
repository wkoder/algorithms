package gcj2012.quals;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class SpeakingInTongues {
	
	static final int INF = 1 << 28, MAX = 1000;
	static final double EPS = 1E-9;
	
	static void solve() {
		String[] g = {"ejp mysljylc kd kxveddknmc re jsicpdrysi",
			"rbcpc ypc rtcsra dkh wyfrepkym veddknkmkrkcd",
			"de kr kd eoya kw aej tysr re ujdr lkgc jv"};
		String[] e = {"our language is impossible to understand",
			"there are twenty six factorial possibilities",
			"so it is okay if you want to just give up"};
		final char NULL = '?';
		char[] map = new char[26];
		Arrays.fill(map, NULL);
//		HashSet<Character> found = new HashSet<Character>();
//		for (int i = 0; i < 26; i++)
//			found.add((char)(i + 'a'));
		for (int i = 0; i < g.length; i++)
			for (int j = 0; j < g[i].length(); j++) {
				char ch = g[i].charAt(j);
				if (ch == ' ')
					continue;
//				found.remove(e[i].charAt(j));
				map[ch - 'a'] = e[i].charAt(j);
			}
		
		map['q' - 'a'] = 'z';
		map['z' - 'a'] = 'q';
//		for (int i = 0; i < map.length; i++)
//			if (map[i] == NULL)
//				System.out.println((char)('a' + i));
//		System.out.println(found);
		
		Scanner scan = new Scanner(in);
		int T = Integer.parseInt(scan.nextLine());
		for (int t = 1; t <= T; t++) {
			out.print("Case #" + t + ": ");
			String s = scan.nextLine();
			for (int i = 0; i < s.length(); i++) {
				char ch = s.charAt(i);
				if (ch != ' ')
					ch = map[ch - 'a'];
				out.print(ch);
			}
			out.println();
		}
	}
	
	static String read() {
		try { return in.readLine(); } catch (IOException e) { return null; }
	}
	public static void main(String[] args) throws IOException {
//		in = new BufferedReader(new InputStreamReader(System.in));
//		out = new PrintWriter(new BufferedOutputStream(System.out));
		String file = "A-small";
		in = new BufferedReader( new FileReader(file + ".in") );
		out = new PrintWriter( new FileOutputStream(file + "_" + System.currentTimeMillis() + ".out") );
		solve(); out.flush();
	}
	static BufferedReader in; static PrintWriter out;
}
