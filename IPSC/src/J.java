import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class J {
	
	static final int INF = 1 << 28, MAX = 1000;
	static final double EPS = 1E-9;
	
	static void solve() {
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();
		for (int t = 1; t <= T; t++) {
			int N = scan.nextInt();
			if (N == 1) {
				System.out.println(1);
				System.out.println(1);
			} else if (N % 2 == 0)
				System.out.println("Impossible");
			else {
				for (int i = 2; i <= N; i++)
					System.out.print(i + " ");
				System.out.println(1);
				System.out.print(N);
				for (int i = 1; i < N; i++)
					System.out.print(" " + i);
				System.out.println();
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) throws IOException {
		solve();
	}
}
