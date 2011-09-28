package itclocal5;
import java.io.File;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Scanner;

public class BuyingAFullHDTV_WCoder {

	public static void main(String[] args) throws Exception {
//		Scanner scan = new Scanner(new File("buyingafullhdtv.in"));
//		PrintWriter out = new PrintWriter("buyingafullhdtv.out");
		Scanner scan = new Scanner(System.in);
		PrintStream out = System.out;
		
		final double EPS = 1e-9;
		final DecimalFormat format = new DecimalFormat("0.00");
		int[] x = new int[3];
		int[] y = new int[3];
		double max = 0;
		while (true) {
			for (int i = 0; i < 6; i++)
				if ((i & 1) == 0)
					x[i>>1] = scan.nextInt();
				else
					y[i>>1] = scan.nextInt();
			int P = scan.nextInt();
			if (P == -1)
				break;
			
			// Get the side lenghts.
			double a = Math.hypot(x[0]-x[1], y[0]-y[1]);
			double b = Math.hypot(x[0]-x[2], y[0]-y[2]);
			double c = Math.hypot(x[1]-x[2], y[1]-y[2]);
			
			// Get the base and hypot of the rectangle triangle that divides this isosceles triangle.
			double hypot = a; // The case when A=B
			double base = c / 2;
			if (Math.abs(a - c) < EPS) { // The case when A=C
				base = b / 2;
			} else if (Math.abs(b - c) < EPS) { // The case when B=C
				base = a / 2;
				hypot = b;
			}
			// Get the value of the triangle high.
			double h = Math.sqrt(hypot*hypot - base*base);
			out.println(format.format(base*h * P));
			max = Math.max(max, base*h * P);
		}
//		out.flush();
//		out.close();
	}
	
}
