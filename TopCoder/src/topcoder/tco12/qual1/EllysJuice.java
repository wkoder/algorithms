package topcoder.tco12.qual1;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

public class EllysJuice {
	
	double[] drank;
	final double EPS = 1e-9;
	
	String winner(String[] players) {
		Map<String, Double> score = new HashMap<String, Double>();
		for (int i = 0; i < players.length; i++) {
			Double val = score.get(players[i]);
			if (val == null)
				val = 0.0;
			val += drank[i];
			score.put(players[i], val);
		}
		
		String best = null;
		double bestVal = 0;
		for (String player : score.keySet()) {
			double val = score.get(player);
			if (val > bestVal+EPS) {
				best = player;
				bestVal = score.get(player);
			} else if (Math.abs(bestVal - val) < EPS)
				best = null;
		}
		
		return best;
	}
	
	public String[] getWinners(String[] players) {
		int N = players.length;
		drank = new double[N];
		for (int i = 0; i < N; i++) {
			if (i < 2)
				drank[i] = 0.5;
			else
				drank[i] = drank[i - 2] / 2;
		}
		
		int[] perm = new int[N];
		for (int i = 0; i < N; i++)
			perm[i] = i;
		
		TreeSet<String> winners = new TreeSet<String>();
		String[] order = new String[N];
		do {
			for (int i = 0; i < N; i++)
				order[i] = players[perm[i]];
			String winner = winner(order);
			if (winner != null)
				winners.add(winner);
		} while (nextPermutation(perm, 0, N));
		
		String[] ans = new String[winners.size()];
		int i = 0;
		for (Iterator<String> iter = winners.iterator(); iter.hasNext(); )
			ans[i++] = iter.next();
		return ans;
	}
	
	static boolean nextPermutation(int[] arr, int first, int last) {
        int i, ii, j;
        int t;
        if (first == last || first + 1 == last)
            return false;
        i = last - 1;
        for(;;) {
            ii = i--;
            if (arr[i] < arr[ii]) {
                j = last;
                while (arr[i] >= arr[--j]) ;
                t = arr[i]; // swap
                arr[i] = arr[j];
                arr[j] = t;
                last--; // reverse
                for (;;) {
                    if (ii >= last)
                        break;
                    t = arr[ii];
                    arr[ii] = arr[last];
                    arr[last] = t;
                    ii++;
                    last--;
                }
                return true;
            }
            if (i == first) {
                last--; // reverse
                for (;;) {
                    if (first >= last)
                        break;
                    t = arr[first];
                    arr[first] = arr[last];
                    arr[last] = t;
                    first++;
                    last--;
                }
                return false;
            }
        }
	}
}
