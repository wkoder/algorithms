package itclocal5;
import java.io.PrintWriter;
import java.util.Random;

public class ChocolateBar_Generator {

	public static void main(String[] args) throws Exception {
		PrintWriter out = new PrintWriter("chocolatebar.in");
		final int TESTS = 1000;
		final int MAX_N = 1 << 30;
		final int MAX_M = 1 << 30;
		
		Random r = new Random();
		out.println("1 1");
		out.println("1 9");
		out.println("121 1");
		out.println(MAX_N + " " + MAX_M);
		out.println("1 " + MAX_M);
		out.println(MAX_N + " 1");
		out.println((1<<28) + " " + (1<<29));
		out.println((1<<28) + " 2");
		out.println((1<<28) + " 1");
		for (int test = 0; test < TESTS; test++) {
			int N = r.nextInt(MAX_N) + 1;
			int M = r.nextInt(MAX_M) + 1;
			out.println(N + " " + M);
		}
		out.println("-1 -1");
		out.flush();
		out.close();
	}
	
}
