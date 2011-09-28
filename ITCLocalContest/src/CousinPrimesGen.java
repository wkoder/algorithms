import java.io.*;
import java.util.Random;

public class CousinPrimesGen {
	public static void main(String[] args) throws IOException {
		final int TESTS = 100;
		final int MAXLEN = 1000;
		final Random RAND = new Random();
		
		PrintWriter out = new PrintWriter("cousinprimes.in");
		out.println(TESTS);
		for (int t = 0; t < TESTS; t++) {
			int len = RAND.nextInt(MAXLEN) + 1;
			while (len-- > 0)
				out.print(RAND.nextInt(10));
			out.println();
		}
		out.flush();
		out.close();
	}
}
