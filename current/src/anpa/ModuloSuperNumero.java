package anpa;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class ModuloSuperNumero {
	
	static void solve() {
		int N = Integer.parseInt(read());
		StringTokenizer tok = new StringTokenizer(read());
		String s = "";
		while (N-- > 0)
			s += tok.nextToken();
		BigInteger b = new BigInteger(s);
		out.println(b.mod(new BigInteger(read())));
	}
	
	static String read() {
		try { return in.readLine(); } catch (IOException e) { return null; }
	}
	public static void main(String[] args) throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new BufferedOutputStream(System.out));
		solve(); out.flush();
	}
	static BufferedReader in; static PrintWriter out;
}
