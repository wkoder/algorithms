package topcoder.unknown;
public class TrappingRabbit {
	public int findMinimumTime(int[] trapX, int[] trapY) {
		int time = 1 << 28;
		for (int i = 0; i < trapX.length; i++)
			time = Math.min(time, trapX[i]+trapY[i]-2);
		return time;
	}
}