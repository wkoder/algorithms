package topcoder.unknown;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LotteryPyaterochka {
	double P;
//	Map<List<Integer>, Double> memo = new HashMap<List<Integer>, Double>();
	double go(List<Integer> state, int n, int r) {
		if (r == 0) {
			for (int x : state)
				if (x >= 3)
					return 1;
			return 0;
		}
//		if (memo.containsKey(state))
//			return memo.get(state);
		
		double prob = 0;
		
		if (state.size() < n) {
			List<Integer> state2 = new ArrayList<Integer>(state);
			state2.add(1);
			prob += (n - state.size()) * P * go(state2, n, r-1);
		}
		for (int i = 0; i < state.size(); i++) {
			ArrayList<Integer> state2 = new ArrayList<Integer>(state);
			state2.set(i, state2.get(i)+1);
			prob += P * go(state2, n, r-1);
		}
		
//		memo.put(state, prob);
		return prob;
	}
	
	public double chanceToWin(int N) {
		P = 1.0 / N;
		List<Integer> state = new ArrayList<Integer>();
		return go(state, N, 5);
	}
	public static void main(String[] args) {
		System.out.println(new LotteryPyaterochka().chanceToWin(1));
		System.out.println(new LotteryPyaterochka().chanceToWin(2));
		System.out.println(new LotteryPyaterochka().chanceToWin(3));
		System.out.println(new LotteryPyaterochka().chanceToWin(6));
	}
}
