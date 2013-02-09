package topcoder.unknown;
import java.util.Arrays;

public class CutSticks {
	public double maxKth(int[] sticks, int C, int K) {
		Arrays.sort(sticks);
		double u = 1e9+1;
		double l = 0;
		for (int t = 0; t < 200; t++) {
			double x = (u + l) / 2;
			long pos = 0;
			for (int i = sticks.length-1; i >= 0; i--)
				if (sticks[i] > x)
					pos++;
			int cuts = C;
			for (int i = sticks.length-1; i >= 0; i--)
				if (sticks[i] > x && cuts > 0) {
					long can = Math.min(Math.round(Math.floor(sticks[i] / x)) - 1, cuts);
					cuts -= can;
					pos += can;
				}
			if (pos >= K)
				l = x;
			else
				u = x;
		}
		return u;
	}
}
