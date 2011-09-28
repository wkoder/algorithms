package itclocal5;
import java.util.Arrays;
import java.util.Scanner;

public class HouseMD {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int INF = Integer.MAX_VALUE;
		int N, T;
		while ((N = scan.nextInt()) > 0) {
			T = scan.nextInt();
			int[][] g = new int[N][N];
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++) {
					g[i][j] = scan.nextInt();
					if (g[i][j] == -1)
						g[i][j] = INF;
				}
			int[] time = new int[N];
			Arrays.fill(time, INF);
			time[0] = 0;
			for (int k = 0; k < N; k++)
				for (int i = 0; i < N; i++)
					for (int j = 0; j < N; j++)
						if (time[i] <= g[i][j])
							time[j] = Math.min(time[j], g[i][j]);
			int safe = N;
			for (int i = 0; i < N; i++)
				if (time[i] < T)
					safe--;
			System.out.println(safe);
		}
	}
	
}
