import java.text.DecimalFormat;
import java.util.Scanner;

public class ElWeroStrikesBack_WCoder {
	public static void main(String[] args) {
		final DecimalFormat FORMAT = new DecimalFormat("0.00");
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();
		while (T-- > 0) {
			double loan = scan.nextDouble();
			double perc = scan.nextDouble() / 100;
			double payment = scan.nextDouble();
			
			double debt = loan;
			double paid = 0;
			while (debt > 0) {
				double thisPayment = Math.min(payment, debt);
				debt -= thisPayment;
				paid += thisPayment;
				debt *= 1+perc;
			}
			System.out.println(FORMAT.format(paid - loan));
		}
	}
}
