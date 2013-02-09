package topcoder.srm538;
import java.util.ArrayList;
import java.util.List;

public class SkewedPerspectives {
	boolean isPossible(int[] cubes, int B, int w, char[] view) {
		int[] cost = new int[3];
		int bCost = 0;
		List<Integer> gCost = new ArrayList<Integer>();
		int stacks = 0;
		for (int from = 0; from < view.length; from++, stacks++) {
			int to;
			for (to = from; to < view.length; to++) {
				if (view[to] == 'b') {
					int countB = 0;
					for (int j = to; j < view.length && view[j] == 'b'; j++)
						countB++;
					
					bCost++;
					if (to == 0 && countB == 1)
						return false;
					if (countB % 2 == 1 && to != 0) {
						gCost.add(to-1);
						break;
					} else
						to++; 
				} else
					cost[view[to] - '0']++;
			}
			
			from = to;
		}
		
		if (stacks > w)
			return false;
		
		bCost = B - bCost;
		if (bCost < 0)
			return false;
		
		for (int i = 0; i < 3; i++) {
			cost[i] = cubes[i] - cost[i];
			if (cost[i] < 0)
				return false;
		}
		
		int have = cost[0] + cost[1] + cost[2];
		for (int g : gCost) {
			if (g % 2 == 1) {
				g--;
				have--;
			}
			
			int e = Math.min(g, 2*bCost);
			g -= e;
			bCost -= e/2;
			have -= g;
			if (have < 0)
				return false;
		}
		
		return true;
	}
	
	public String[] areTheyPossible(int[] cubes, int B, int w, String[] views) {
		String[] ans = new String[views.length];
		for (int i = 0; i < views.length; i++)
			ans[i] = isPossible(cubes, B, w, views[i].toCharArray()) ? "valid" : "invalid";
		return ans;
	}
}
