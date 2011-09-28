package topcoder;
public class TheFansAndMeetingsDivTwo {
	public double find(int[] minJ, int[] maxJ, int[] minB, int[] maxB) {
		int N = minJ.length;
		double p = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++) {
				double pj = 1.0 / (maxJ[i] - minJ[i]+1);
				double pb = 1.0 / (maxB[j] - minB[j]+1);
//				int range = Math.max(maxJ[i], maxB[j]) - Math.min(minJ[i], minB[j]);
				int over = Math.min(maxJ[i], maxB[j]) - Math.max(minJ[i], minB[j]) + 1;
				if (over > 0)
					p += pj * pb * over;
			}
		return p / N / N;
	}
}
