package topcoder;
import java.math.BigInteger;

public class AgeEncoding {
	final double EPS = 1e-9;
	double f(int[] n, double b) {
		double s = 0;
		double p = 1;
		for (int j = 0; j < n.length; j++) {
			if (n[j] == 1)
				s += p;
			p *= b;
		}
		return s;
	}
	public double getRadix(int age, String candlesLine) {
		if (age == 1 && new BigInteger(candlesLine).equals(BigInteger.ONE))
			return -2;
		double l = 0;
		double u = 100;
		int[] n = new int[candlesLine.length()];
		for (int i = 0; i < n.length; i++)
			n[n.length - 1 - i] = candlesLine.charAt(i) - '0';
		for (int i = 0; i < 200; i++) {
			double b = (l + u) / 2;
			double s = f(n, b);
			if (s < age)
				l = b;
			else
				u = b;
		}
		double s = f(n, u);
		if (Math.abs(age - s) > EPS)
			return -1;
		if (u < 1e-9)
			return -1;
		return u;
	}
}
