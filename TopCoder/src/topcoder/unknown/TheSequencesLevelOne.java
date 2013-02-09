package topcoder.unknown;
public class TheSequencesLevelOne {
	public int[] find(int[] sequence) {
		for (int i = 0; i < sequence.length; i++) {
			int a = sequence[i];
			while (a % 7 != 0 && a % 4 != 0)
				a--;
			int b = sequence[i];
			while (b % 7 != 0 && b % 4 != 0)
				b++;
			int da = sequence[i] - Math.abs(a);
			int db = b - sequence[i];
			if (da <= db)
				sequence[i] = a;
			else
				sequence[i] = b;
		}
		return sequence;
	}
}
