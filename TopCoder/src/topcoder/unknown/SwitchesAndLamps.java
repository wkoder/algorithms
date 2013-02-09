package topcoder.unknown;
import java.util.Arrays;

public class SwitchesAndLamps {
	
	void print(boolean[][] same) {
		for (int i = 0; i < same.length; i++) {
			for (int j = 0; j < same.length; j++)
				System.out.print(same[i][j] ? "X" : ".");
			System.out.println();
		}
		System.out.println();
	}
	
	public int theMin(String[] switches, String[] lamps) {
		int E = switches.length;
		int N = switches[0].length();
		boolean[][] S = new boolean[E][N];
		boolean[][] L = new boolean[E][N];
		for (int i = 0; i < E; i++)
			for (int j = 0; j < N; j++) {
				S[i][j] = switches[i].charAt(j) == 'Y';
				L[i][j] = lamps[i].charAt(j) == 'Y';
			}
		
		boolean[][] same = new boolean[N][N];
		for (int i = 0; i < N; i++)
			Arrays.fill(same[i], true);
		for (int e = 0; e < E; e++)
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++)
					if (S[e][i] != L[e][j])
						same[i][j] = false;
		
		print(same);
		boolean change = true;
		boolean[] reduced = new boolean[N];
		while (change) {
			change = false;
			for (int i = 0; i < N && !change; i++) {
				if (reduced[i])
					continue;
				
				int unique = -1;
				for (int j = 0; j < N; j++)
					if (same[i][j])
						if (unique == -1)
							unique = j;
						else
							unique = -2;
				if (unique == -1)
					return -1;
				if (unique != -2) {
					change = true;
					reduced[i] = true;
					for (int j = 0; j < N; j++)
						if (j != i)
							same[j][unique] = false;
				}
			}
		}
		
		print(same);
		change = true;
		Arrays.fill(reduced, false);
		while (change) {
			change = false;
			for (int i = 0; i < N && !change; i++) {
				if (reduced[i])
					continue;
				
				int unique = -1;
				for (int j = 0; j < N; j++)
					if (same[j][i])
						if (unique == -1)
							unique = j;
						else
							unique = -2;
				if (unique == -1)
					return -1;
				if (unique != -2) {
					change = true;
					reduced[i] = true;
					for (int j = 0; j < N; j++)
						if (j != i)
							same[unique][j] = false;
				}
			}
		}
		
		print(same);
		int max = 0;
		for (int i = 0; i < N; i++) {
			int c = 0;
			for (int j = 0; j < N; j++)
				if (same[i][j])
					c++;
			max = Math.max(max, c-1);
		}
		return max;
	}
}
