package old;
import java.io.*;
import java.util.*;

public class DoubleSquares {
	
	public static void main(String[] args) throws Exception {
		final String file = "1";
		Scanner scan = new Scanner(System.in);
		//Scanner scan = new Scanner(new File(file + ".in"));
		PrintWriter out = new PrintWriter(System.out);
		//PrintWriter out = new PrintWriter(new File(file + "_" + System.currentTimeMillis() + ".out"));
		
		int N = scan.nextInt();
		while (N-- > 0) {
			long k = scan.nextInt();
			int w = 0;
			for (long a = 0; a*a <= k/2; a++) {
				long bb = k - a*a;
				long b = Math.round(Math.sqrt(bb));
				if (a*a + b*b == k)
					w++;
			}
			out.println(w);
		}
		
		out.flush();
		out.close();
	}
}
