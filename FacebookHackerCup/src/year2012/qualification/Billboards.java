package year2012.qualification;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Billboards {
	private static boolean solve(int s, int[] words, int N, int W, int H) {
		if (s == 0)
			return true;
		
		int lines = H / s;
		int currWord = 0;
		while (lines > 0 && currWord < N) {
			int chars = W / s;
			boolean first = true;
			while (chars > 0 && currWord < N) {
				chars -= words[currWord] + (first? 0 : 1);
				if (chars >= 0)
					currWord++;
				first = false;
			}
			lines--;
		}
		
		return currWord >= N;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int T = Integer.parseInt(scanner.nextLine());
		for (int t = 1; t <= T; t++) {
			System.out.print("Case #" + t + ": ");
			StringTokenizer tok = new StringTokenizer(scanner.nextLine());
			int W = Integer.parseInt(tok.nextToken());
			int H = Integer.parseInt(tok.nextToken());
			int N = tok.countTokens();
			int[] words = new int[N];
			for (int i = 0; i < N; i++)
				words[i] = tok.nextToken().length();
			int low = 0;
			int up = Math.min(W, H);
			while (up-low > 5) {
				int mid = (up + low) / 2;
				if (solve(mid, words, N, W, H))
					low = mid;
				else
					up = mid;
			}
			
			while (solve(low, words, N, W, H))
				low++;
			System.out.println(low-1);
		}
	}
}
