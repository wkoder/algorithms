package topcoder.srm538;
public class LeftOrRight {
	public int maxDistance(String program) {
		int best = 0;
		for (int k = -1; k <= 1; k += 2) {
			int min = 0;
			int max = 0;
			int i = 0;
			for (char ch : program.toCharArray()) {
				if (ch == 'L')
					i--;
				else if (ch == 'R')
					i++;
				else
					i += k;
				min = Math.min(min, i);
				max = Math.max(max, i);
			}
			
			best = Math.max(best, Math.max(-min, max));
		}
		
		return best;
	}
}
