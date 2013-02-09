package topcoder.unknown;
public class Archery {
	public double expectedPoints(int N, int[] ringPoints) {
		double A = Math.PI * (N+1) * (N+1);
		double p = 0;
		for (int i = 0; i <= N; i++) {
			double a = Math.PI * (i+1) * (i+1) - Math.PI * i * i;
			p += ringPoints[i] * a / A;
		}
		return p;
	}
}
