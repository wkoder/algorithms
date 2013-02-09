package topcoder.unknown;
public class TheTournamentDivTwo {
	public int find(int[] points) {
		int sum = 0;
		for (int p : points)
			sum += p;
		if (sum % 2 == 1)
			return -1;
		return sum / 2;
	}
}
