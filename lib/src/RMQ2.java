import java.util.*;

public class RMQ2 {
	int N;
	int[][] tmin;
	int[] data;
	
	public RMQ2(int size) {
		data = new int[size];
		tmin = new int[size][log2(size) + 1];
		clear(size);
	}
	
	private int log2(int n) {
		return Integer.numberOfTrailingZeros(Integer.highestOneBit(n));
	}
	
	public void clear(int size) {
		N = size;
		Arrays.fill(data, 0, N, Integer.MAX_VALUE);
		int log = log2(N) + 1;
		for (int i = 0; i < N; i++)
			Arrays.fill(tmin[i], 0, log, Integer.MAX_VALUE);
	}
	
	public int getMin(int i, int j) {
		return data[getMinIndex(i, j)];
	}
	
	public int getMinIndex(int i, int j) {
		int k = log2(j - i + 1);
		if (data[tmin[i][k]] <= data[tmin[j - (1 << k) + 1][k]])
			return tmin[i][k];
		else
			return tmin[j - (1 << k) + 1][k];
	}

	public void process() {
		for (int i = 0; i < N; i++)
			tmin[i][0] = i;
		for (int j = 1; 1 << j <= N; j++) {
			for (int i = 0; i + (1 << j) - 1 < N; i++) {
				if (data[tmin[i][j - 1]] < data[tmin[i + (1 << (j - 1))][j - 1]])
					tmin[i][j] = tmin[i][j - 1];
				else
					tmin[i][j] = tmin[i + (1 << (j - 1))][j - 1];
			}
		}
	}
}
