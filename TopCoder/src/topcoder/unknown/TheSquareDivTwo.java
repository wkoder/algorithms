package topcoder.unknown;
public class TheSquareDivTwo {
	public String[] solve(String[] board) {
		int N = board.length;
		int[] r = new int[N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				if (board[i].charAt(j) == 'C')
					r[i]++;
			board[i] = "";
		}
		for (int j = 0; j < N; j++) {
			for (int i = N-1; i >= 0; i--)
				if (r[j] == 0)
					board[i] += ".";
				else {
					r[j]--;
					board[i] += "C";
				}
		}
		return board;
	}
}
