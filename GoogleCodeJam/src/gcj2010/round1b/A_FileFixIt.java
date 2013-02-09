package gcj2010.round1b;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class A_FileFixIt {
	
	static final int INF = 1 << 28, MAX = 1000;
	static final double EPS = 1E-9;
	
	static void solve() {
		Scanner scan = new Scanner(in);
		int T = scan.nextInt();
		for (int t = 1; t <= T; t++) {
			out.print("Case #" + t + ": ");
			int N = scan.nextInt();
			int M = scan.nextInt();
			Dir root = new Dir("");
			while (N-- > 0) {
				StringTokenizer tok = new StringTokenizer(scan.next(), "/");
				Dir cur = root;
				while (tok.hasMoreTokens()) {
					String name = tok.nextToken();
					Dir next = cur.children.get(name);
					if (next == null) {
						next = new Dir(name);
						cur.children.put(name, next);
					}
					cur = next;
				}
			}
			String[] dirs = new String[M];
			for (int i = 0; i < M; i++)
				dirs[i] = scan.next();
			Arrays.sort(dirs);
			int cost = 0;
			for (String dir : dirs) {
				StringTokenizer tok = new StringTokenizer(dir, "/");
				Dir cur = root;
				while (tok.hasMoreTokens()) {
					String name = tok.nextToken();
					Dir next = cur.children.get(name);
					if (next == null) {
						cost++;
						next = new Dir(name);
						cur.children.put(name, next);
					}
					cur = next;
				}
			}
			out.println(cost);
		}
	}
	
	static String read() {
		try { return in.readLine(); } catch (IOException e) { return null; }
	}
	public static void main(String[] args) throws IOException {
//		in = new BufferedReader(new InputStreamReader(System.in));
//		out = new PrintWriter(new BufferedOutputStream(System.out));
		String file = "A-large";
		in = new BufferedReader( new FileReader(file + ".in") );
		out = new PrintWriter( new FileOutputStream(file + "_" + System.currentTimeMillis() + ".out") );
		solve(); out.flush();
	}
	static BufferedReader in; static PrintWriter out;
	
	static class Dir {
		String name;
		Map<String, Dir> children;
		public Dir(String name) {
			this.name = name;
			children = new HashMap<String, Dir>();
		}
		@Override
		public int hashCode() {
			return name.hashCode();
		}
		@Override
		public boolean equals(Object obj) {
			Dir d = (Dir) obj;
			return name.equals(d.name);
		}
	}
}
