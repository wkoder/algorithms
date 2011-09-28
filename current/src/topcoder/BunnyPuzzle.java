package topcoder;
public class BunnyPuzzle {
	public int theCount(int[] bunnies) {
		int w = 0;
		for (int i = 0; i < bunnies.length-1; i++) {
			int p = bunnies[i+1]*2 - bunnies[i];
			if (p > bunnies[i+1] && (i+1 == bunnies.length-1 || p < bunnies[i+2]))
				w++;
			p = bunnies[i]*2 - bunnies[i+1];
			if (p < bunnies[i] && (i == 0 || p > bunnies[i-1]))
				w++;
		}
		return w;
	}
}
