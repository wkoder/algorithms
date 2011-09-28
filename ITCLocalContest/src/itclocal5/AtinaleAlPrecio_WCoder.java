package itclocal5;
import java.io.File;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class AtinaleAlPrecio_WCoder {

	public static void main(String[] args) throws Exception {
//		Scanner scan = new Scanner(new File("atinalealprecio.in"));
//		PrintWriter out = new PrintWriter("atinalealprecio.out");
		Scanner scan = new Scanner(System.in);
		PrintStream out = System.out;
		
		final int MAX = 1000;
		int[] p = new int[MAX];
		while (true) {
			int N = scan.nextInt();
			int K = scan.nextInt();
			int M = scan.nextInt();
			if (N == -1)
				break;
			
			for (int i = 0; i < N; i++)
				p[i] = scan.nextInt();
			// We take the K smallest values and that's our only chance to win.
			Arrays.sort(p, 0, N);
			int sum = 0;
			for (int i = 0; i < K; i++)
				sum += p[i];
			out.println(sum <= M? "Yes" : "No");
		}
//		out.flush();
//		out.close();
	}
	
}
