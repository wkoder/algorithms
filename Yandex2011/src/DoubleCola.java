import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class DoubleCola {
	
	static void solve() {
		String[] names = {"Sheldon", "Leonard", "Penny", "Rajesh", "Howard"};
		long n = Long.parseLong(read()) - 1;
		
		long k = 1;
		while (n >= 5*k) {
			n -= 5*k;
			k *= 2;
		}
		
		System.out.println(names[(int)(n / k)]);
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
