import java.util.Scanner;


public class Test {

	static long gcd(long a, long b) {
		while (b != 0) {
			long x = a % b;
			a = b;
			b = x;
		}
		
		return a;
	}
	
	static long lcm(long a, long b) {
		long g = gcd(a, b);
		long ag = a / g;
		if (Long.MAX_VALUE / ag < b)
			return Long.MAX_VALUE;
		return ag * b;
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		long[] K = new long[N];
		long gcd = 0;
		long lcm = 1;
		for (int i = 0; i < N; i++) {
			K[i] = scan.nextLong();
			gcd = gcd(K[i], gcd);
			lcm = lcm(K[i], lcm);
		}
		
		
	}
}
