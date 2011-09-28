package topcoder;
import java.util.Arrays;

public class Nisoku {
	public double theMax(double[] cards) {
		Arrays.sort(cards);
		double max = 0;
		for (int p = 0; p <= cards.length; p += 2) {
			double v = 1;
			for (int i = 0; i*2 < p; i++)
				v *= cards[i] + cards[p-1-i];
			for (int i = p; i < cards.length; i++)
				v *= cards[i];
			max = Math.max(max, v);
		}
		return max;
	}
}
