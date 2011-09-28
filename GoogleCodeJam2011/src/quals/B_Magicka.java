package quals;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class B_Magicka {
	
	static final int INF = 1 << 28, MAX = 1000;
	static final double EPS = 1E-9;
	
	static void solve() {
		Scanner scan = new Scanner(in);
		int T = scan.nextInt();
		for (int t = 1; t <= T; t++) {
			char[][] comb = new char[256][256];
			boolean[][] oppo = new boolean[256][256];
			out.print("Case #" + t + ": ");
			int C = scan.nextInt();
			while (C-- > 0) {
				String s = scan.next();
				int a = s.charAt(0);
				int b = s.charAt(1);
				char c = s.charAt(2);
				comb[a][b] = comb[b][a] = c;
			}
			
			int D = scan.nextInt();
			while (D-- > 0) {
				String s = scan.next();
				int a = s.charAt(0);
				int b = s.charAt(1);
				oppo[a][b] = oppo[b][a] = true;
			}
			
			String s = "";
			scan.nextInt();
			String k = scan.next();
			next:
			for (char ch : k.toCharArray()) {
				s += ch;
//				out.println("   " + s);
				char[] ss = s.toCharArray();
				if (ss.length >= 2) {
					char rep = comb[ss[ss.length-2]][ss[ss.length-1]];
					if (rep > 0) {
						s = s.substring(0, s.length() - 2) + rep;
						ch = rep;
//						out.println("   REP: " + s);
					}
					
					for (int i = 0; i < ss.length-1; i++)
						if (oppo[ch][ss[i]]) {
							s = "";
//							out.println("   CLEARED");
							continue next;
						}
				}
			}
			
			out.print("[");
			for (int i = 0; i < s.length(); i++) {
				if (i > 0)
					out.print(", ");
				out.print(s.charAt(i));
			}
			out.println("]");
		}
	}
	
	static String read() {
		try { return in.readLine(); } catch (IOException e) { return null; }
	}
	public static void main(String[] args) throws IOException {
//		in = new BufferedReader(new InputStreamReader(System.in));
//		out = new PrintWriter(new BufferedOutputStream(System.out));
		String file = "B-small";
		in = new BufferedReader( new FileReader(file + ".in") );
		out = new PrintWriter( new FileOutputStream(file + "_" + System.currentTimeMillis() + ".out") );
		solve(); out.flush();
	}
	static BufferedReader in; static PrintWriter out;
}
