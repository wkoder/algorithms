package anpa;
import java.util.*;

public class Divisores {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		long n = scan.nextLong();
		long d = 1;
		for (int i = 2; i*i <= n; i++)
			if (n % i == 0) {
				int p = 1;
				while (n % i == 0) {
					n /= i;
					p++;
				}
				d *= p;
			}
		if (n > 1)
			d *= 2;
		System.out.println(d);
	}
}
