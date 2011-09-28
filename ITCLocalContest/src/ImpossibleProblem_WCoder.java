import java.util.Scanner;

public class ImpossibleProblem_WCoder {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n;
		// Use your brain here:
		while ((n = (int)Math.min(scan.nextLong(), 5)) > 0)
			System.out.println(n == 3? 6 : n % 5);
	}
}
