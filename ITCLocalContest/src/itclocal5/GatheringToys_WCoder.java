package itclocal5;
import java.util.Scanner;

public class GatheringToys_WCoder {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while (true) {
			int N = scan.nextInt();
			if (N == -1)
				break;
			int V = scan.nextInt();
			int C = scan.nextInt();
			
			// Number of toys you can put in a box.
			int t = C / V;
			// Number of full boxes.
			int b = N / t;
			// Check if we need to use an non-full box.
			if (N % t > 0)
				b++;
			System.out.println(b);
		}
	}
	
}
