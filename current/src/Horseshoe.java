import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class Horseshoe {
	
	public static void solve() {
		StringTokenizer tok = new StringTokenizer(read());
		Set<Integer> set = new HashSet<Integer>();
		while (tok.hasMoreTokens()) {
			set.add(Integer.parseInt(tok.nextToken()));
		}
		out.println(4 - set.size());
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
