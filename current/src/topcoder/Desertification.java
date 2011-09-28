package topcoder;
public class Desertification {
	public int desertArea(String[] island, int T) {
		int R = island.length;
		int C = island[0].length();
		char[][] m = new char[R][];
		char[][] m2 = new char[R][C], temp;
		for (int i = 0; i < R; i++)
			m[i] = island[i].toCharArray();
		int[] DR = { -1, 0, 1, 0 };
		int[] DC = { 0, 1, 0, -1 };
		for (int k = 0; k < Math.min(T, 110); k++) {
			for (int i = 0; i < R; i++)
				for (int j = 0; j < C; j++)
					if (m[i][j] == 'D')
						m2[i][j] = 'D';
					else {
						m2[i][j] = 'F';
						for (int d = 0; d < DC.length; d++) {
							int ii = i + DR[d];
							int jj = j + DC[d];
							if (ii >= 0 && ii < R && jj >= 0 && jj < C
									&& m[ii][jj] == 'D') {
								m2[i][j] = 'D';
								break;
							}
						}
					}
			temp = m;
			m = m2;
			m2 = temp;
		}
		int d = 0;
		for (int i = 0; i < R; i++)
			for (int j = 0; j < C; j++)
				if (m[i][j] == 'D')
					d++;
		return d;
	}
}
