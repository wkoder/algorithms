package topcoder.unknown;
import java.util.Arrays;

public class TheSequencesLevelTwo {
	public int[] find(int[] sequence) {
		Arrays.sort(sequence);
		for (int i = 1; i < sequence.length-1; i+=2) {
			int b = sequence[i];
			sequence[i] = sequence[i+1];
			sequence[i+1] = b;
		}
		return sequence;
	}
}
