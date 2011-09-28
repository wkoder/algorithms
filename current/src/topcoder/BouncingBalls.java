package topcoder;
import java.util.Arrays;

public class BouncingBalls {
	public double expectedBounces(int[] x, int T) {
		Arrays.sort(x);
		int b = 0;
		for (int i = 0; i < x.length; i++)
			for (int j = i+1; j < x.length; j++)
				if (x[j] - x[i] <= 2*T)
					b++;
		return b / 4.0;
	}
}
