import java.io.*;
import java.util.Random;

public class ArithmeticExpressionsGen {
	public static void main(String[] args) throws IOException {
		final int TESTS = 100;
		final int MAXN = 1000001;
		final Random RAND = new Random();
		
		PrintWriter out = new PrintWriter("arithmeticexpressions.in");
		out.println(TESTS);
		out.println("0+0");
		out.println("0+1000000");
		out.println("1000000+0");
		out.println("1000000+1000000");
		out.println("0-0");
		out.println("0-1000000");
		out.println("1000000-0");
		out.println("1000000-1000000");
		for (int t = 8; t < TESTS; t++) {
			int a = RAND.nextInt(MAXN);
			int b = RAND.nextInt(MAXN);
			if (RAND.nextBoolean())
				out.println(a + "+" + b);
			else
				out.println(a + "-" + b);
		}
		out.flush();
		out.close();
	}
}
