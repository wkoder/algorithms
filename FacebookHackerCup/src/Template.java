import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class Template {
	
	static final int INF = 1 << 28;
	static final double EPS = 1E-9;
	
	private static void hack() {
		Scanner scan = new Scanner(in);
		int T = scan.nextInt();
		for (int t = 1; t <= T; t++) {
			out.print("Case #" + t + ": ");
			
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
