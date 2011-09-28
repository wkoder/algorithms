package itclocal5;
import java.io.PrintWriter;
import java.util.Random;

public class AtinaleAlPrecio_Generator {

	public static void main(String[] args) throws Exception {
		PrintWriter out = new PrintWriter("atinalealprecio.in");
		final int TESTS = 100;
		final int MAX_N = 1000;
		final int MAX_M = 1000000;
		final int MAX_P = 1000;
		
		Random r = new Random();
		for (int test = 0; test < TESTS; test++) {
			int N = r.nextInt(MAX_N) + 1;
			int K = r.nextInt(N) + 1;
			int M = r.nextInt(MAX_M) + 1;
			out.println(N + " " + K + " " + M);
			for (int i = 0; i < N; i++) {
				if (i > 0)
					out.print(" ");
				out.print(r.nextInt(MAX_P) + 1);
			}
			out.println();
		}
		out.println("-1 -1 -1");
		out.flush();
		out.close();
	}
	
}
