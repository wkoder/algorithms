package itclocal5;
import java.io.File;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class ChocolateBar_WCoder {
	
	// Euclid's Algorithm: O(lg n).
	static int gcd(int a, int b) {
		while (b > 0) {
			int x = a % b;
			a = b;
			b = x;
		}
		return a;
	}

	public static void main(String[] args) throws Exception {
//		Scanner scan = new Scanner(new File("chocolatebar.in"));
//		PrintWriter out = new PrintWriter("chocolatebar.out");
		Scanner scan = new Scanner(System.in);
		PrintStream out = System.out;
		
		while (true) {
			int N = scan.nextInt();
			int M = scan.nextInt();
			if (N == -1)
				break;
			
			// We search for the greatest common divisor of both values.
			int gcd = gcd(N, M);
			// Don't forget to cast to long to prevent overflow.
			out.println(N/gcd * (long)M/gcd);
		}
//		out.flush();
//		out.close();
	}
	
}
