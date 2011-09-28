package anpa;
import java.util.*;

public class Ceros {
	
	static long f(int n, int k) {
		int d = 0;
		do
			d += n/= k;
		while (n > 0);
		return d;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		System.out.println(Math.min(f(n, 2), f(n, 5)));
	}
	
}
