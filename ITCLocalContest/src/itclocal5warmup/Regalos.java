package itclocal5warmup;
import java.util.*;
import java.io.*;

/**
 * @author Moises Osorio - WCoder
 */
public class Regalos {
	
	static int INF = 1 << 28;
	static double EPS = 1e-9;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(new BufferedInputStream(System.in));
		int T = scan.nextInt();
		while (T-- > 0) {
			int N = scan.nextInt();
			
			int max = - INF; // at least one choice
			int sum  = 0;
			for (int i = 0; i < N; i++) { // Akane's algorithm
				sum += scan.nextInt();
				if (sum > max) // niiice!
					max = sum;
				if (sum < 0) // inconvenience!
					sum = 0;
			}
			System.out.println(max);
		}
	}
	
}
