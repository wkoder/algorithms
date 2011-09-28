package topcoder;
import java.util.Arrays;

public class RabbitNumbering {
	final long MOD = 1000000007;
	public int theCount(int[] maxNumber) {
		Arrays.sort(maxNumber);
		long w = 1;
		for (int i = 0; i < maxNumber.length; i++) {
			if (maxNumber[i] <= i)
				return 0;
			w = (w * (maxNumber[i]-i)) % MOD;
		}
		return (int) w;
	}
}
