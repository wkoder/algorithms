import java.util.Arrays;

public class Badgers {
	public int feedMost(int[] hunger, int[] greed, int totalFood) {
		int N = hunger.length;
		for (int S = 1; S <= N; S++) {
			int[] wants = new int[N];
			for (int i = 0; i < N; i++)
				wants[i] = hunger[i] + (S-1)*greed[i];
			Arrays.sort(wants);
			int food = 0;
			for (int i = 0; i < S; i++)
				food += wants[i];
			if (food > totalFood)
				return S-1;
		}
		return N;
	}
}
