import java.text.DecimalFormat;
import java.util.Scanner;

public class FlyingWero_WCoder {
	public static void main(String[] args) {
		final DecimalFormat FORMAT = new DecimalFormat("0.00");
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();
		while (T-- > 0)
			System.out.println(FORMAT.format(Math.PI * scan.nextDouble()));
	}
}
