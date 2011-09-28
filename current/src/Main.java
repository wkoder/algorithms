import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class Main {
	
	static double fd(double v, double f, double L) {
		return v*3600 / (v*v/(f+f) + L);
//		return 7200*f*v / (v*v + 2*L*f);
	}
	
	static void solve() {
		DecimalFormat format = new DecimalFormat("0.00000");
		while (true) {
			StringTokenizer tok = new StringTokenizer(read());
			int L = Integer.parseInt(tok.nextToken());
			int f = Integer.parseInt(tok.nextToken());
			if (L+f == 0)
				break;
			double l = 0;
			double u = 10000;
			for (int i = 0; i < 500; i++) {
				double ll = (l*2 + u) / 3;
				double uu = (l + u*2) / 3;
				if (fd(ll, f, L) > fd(uu, f, L))
					u = uu;
				else
					l = ll;
			}
			out.println(format.format(l) + " " + format.format(fd(l, f, L)));
		}
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
