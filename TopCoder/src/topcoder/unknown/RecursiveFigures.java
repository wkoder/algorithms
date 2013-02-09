package topcoder.unknown;
public class RecursiveFigures {
	public double getArea(int sideLength, int K) {
		double a = 0;
		double L = sideLength;
		while (K-- > 0) {
			double c = Math.PI * L/2 * L/2;
			double l = L/2 * Math.sqrt(2);
			if (K == 0)
				l = 0;
			a += c - l*l;
			L = l;
		}
		return a;
	}
}
